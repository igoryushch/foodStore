package ua.foodstore.controllers;

import ua.foodstore.entities.UserRole;
import ua.foodstore.entities.Dbuser;
import javax.persistence.*;
import java.util.*;

/** 
* This JPA controller class (to follow DAO design pattern)
* Provides necessary operations with Dbuser entity.
*
* @author <a href="mailto:igor_aviant@ukr.net">Igor Yushchenko</a>
* @version $ $Date: July 21, 2013 $
*/
public class UsersJpaController 
{
	private EntityManagerFactory emf;

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * @param emf the emf to set
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * @param emf
	 */
	public UsersJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public UsersJpaController() {
		
	}
	
	public EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}

	/**
	* Returns the list of users
	* 
	* @return List<User>  list of users
	*/
	public List<Dbuser> getUsers()
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			TypedQuery<Dbuser> query = em.createQuery("SELECT us FROM User us",Dbuser.class);
			List<Dbuser> users = null;
			users = query.getResultList();
			return users;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Returns role of user by given user id
	* @param userId - id of user
	* @return the UserRole of user
	*/
	public UserRole getUsersRole(int userId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			TypedQuery<UserRole> query = em.createQuery("SELECT us.role FROM User us Where us.id=" + userId,UserRole.class);
			UserRole resRole = null;
			resRole = query.getSingleResult();
			return resRole;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Authenticate user in system by given login and password.
	* Returns Dbuser if login and password was matched with database record.
	* @param login - login typed by user
	* @param password - password typed by user
	* @return the Dbuser according entered data or null
	*/
	public Dbuser authenticate(String login, String password)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			TypedQuery<Dbuser> query = em.createQuery("SELECT us FROM User us Where us.login=:log",Dbuser.class);
			query.setParameter("log", login);
			Dbuser resUser = query.getSingleResult();
			if (resUser != null)
			{
				if (resUser.getPassword().equals(password))
				{
					return resUser;
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
		} catch(NoResultException e) {
	        return null;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Add new user to the system.
	* Also method check that no other user with "Security officer" role can be added
	* @param login - user's login
	* @param password - user's password
	* @param fullName - user's name
	* @param roleId - id of given role 
	* @return true - if user added successfully, false - otherwise
	*/
	public boolean addUser(String login, String password, String fullName, int roleId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			UserRole usersRole = em.find(UserRole.class, roleId);
			UserRole securityRole = em.find(UserRole.class, 4);
			if (usersRole.equals(securityRole))
			{
				// user with role "Security officer" must be only one
				return false;
			}
			
			em.getTransaction().begin();
			Dbuser newUser = new Dbuser();
			newUser.setFullName(fullName);
			newUser.setLogin(login);
			newUser.setPassword(password);
			newUser.setRole(usersRole);
			em.persist(newUser);
			em.getTransaction().commit();
			return true;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Change user`s password by given user id
	* @param userId - id of user
	* @param newPassword - new password
	* @return void
	*/
	public void setNewPassword (int userId, String newPassword)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			Dbuser user = em.find(Dbuser.class, userId);
			user.setPassword(newPassword);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Change user`s password by given user id
	* @param userId - id of user
	* @param newPassword - new password
	* @return void
	*/
	public void setNewLogin (int userId, String newLogin)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			Dbuser user = em.find(Dbuser.class, userId);
			user.setLogin(newLogin);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Change user`s role by given user id and role id
	* Also method check that no other user with "Security officer" role can be added
	* @param userId - id of user
	* @param roleId - id of new role
	* @return true - if role was changed successfully, false - otherwise
	*/
	public boolean setNewRole(int userId,int roleId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			UserRole usersRole = em.find(UserRole.class, roleId);
			UserRole securityRole = em.find(UserRole.class, 4);
			if (usersRole.equals(securityRole))
			{
				// user with role "Security officer" must be only one
				return false;
			}
			
			em.getTransaction().begin();
			Dbuser user = em.find(Dbuser.class, userId);
			user.setRole(usersRole);
			em.getTransaction().commit();
			return true;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Remove user from the system by given user id
	* Also method check that user don`t has "Security officer" role.
	* @param userId - id of user
	* @return true - if user was removed successfully, false - otherwise
	*/
	public boolean removeUser(int userId)
	{
		EntityManager em = null;
		UserRole usersRole = getUsersRole(userId);
		try 
		{
			em = getEntityManager();
			UserRole securityRole = em.find(UserRole.class, 4);
			if (usersRole.equals(securityRole))
			{
				// user with role "Security officer" can`t be removed
				return false;
			}
			
			em.getTransaction().begin();
			Dbuser toRemove = em.find(Dbuser.class, userId);
			toRemove.setRole(null);
			em.remove(toRemove);
			em.getTransaction().commit();
			return true;
		} finally 
		{
			em.close();
		}
	}
}
