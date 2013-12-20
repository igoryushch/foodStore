package ua.foodstore.controllers;

import ua.foodstore.entities.MenuPosition;
import java.util.List;

import javax.persistence.*;


/** 
* This JPA controller class (to follow DAO design pattern)
* Provides necessary operations with Menu entity.
*
* @author <a href="mailto:igor_aviant@ukr.net">Igor Yushchenko</a>
* @version $ $Date: July 20, 2013 $
*/
public class MenuJpaController 
{
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public MenuJpaController(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	
	public MenuJpaController() {
		super();
	}
	
	public EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
	
	/**
	* Returns the current Menu
	* 
	* @return the Menu
	*/
	public List<MenuPosition> getCurrentMenu()
	{
		EntityManager em = null;
		List<MenuPosition> menu = null;
		try 
		{
			em = getEntityManager();
			TypedQuery<MenuPosition> query = em.createQuery("SELECT mp from MenuPosition mp Where mp.active=1",MenuPosition.class); 
			menu = query.getResultList();
			return menu;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Add new course(meal) to the current Menu
	* @param positionID - position to add
	* @param menuID - menu in which position need to be added 
	* @return void
	*/
	public void addMenuPosition(int positionID)
	{
		EntityManager em = null;
		MenuPosition position = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			position = em.getReference(MenuPosition.class, positionID);
			position.setActive(1);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}

	/**
	* Remove position (course,meal) from the current Menu
	* @param positionID - position to remove
	* @return void
	*/
	public void removeMenuPosition(int positionID)
	{
		EntityManager em = null;
		MenuPosition position = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			position = em.getReference(MenuPosition.class, positionID);
			position.setActive(0);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
}
