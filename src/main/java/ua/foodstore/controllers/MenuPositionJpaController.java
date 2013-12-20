package ua.foodstore.controllers;

import ua.foodstore.entities.MenuPosition;
import java.math.BigDecimal;
import javax.persistence.*;

/** 
* This JPA controller class (to follow DAO design pattern)
* Provides necessary operations with MenuPosition entity.
*
* @author <a href="mailto:igor_aviant@ukr.net">Igor Yushchenko</a>
* @version $ $Date: July 20, 2013 $
*/
public class MenuPositionJpaController 
{
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public MenuPositionJpaController(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	
	public MenuPositionJpaController() {
		super();
	}
	
	public EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
	
	/**
	* Returns position of menu by given id number
	* @param positionId - id of position to be returned
	* @return the MenuPosition
	*/
	public MenuPosition getMenuPositionById(int positionId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			MenuPosition mp = em.find(MenuPosition.class, positionId);
			return mp;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Change price of menu position by given id
	* @param positionId - id of position to be updated
	* @return void
	*/
	public void changePriceMenuPosition(int positionId, BigDecimal newPrice)
	{
		EntityManager em = null;
		MenuPosition mp = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			mp = em.find(MenuPosition.class, positionId);
			mp.setPrice(newPrice);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
}
