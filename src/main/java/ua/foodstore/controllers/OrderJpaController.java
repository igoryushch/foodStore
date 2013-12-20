package ua.foodstore.controllers;

import ua.foodstore.dao.ReportEntryCategory;
import ua.foodstore.dao.ReportEntry;
import ua.foodstore.entities.OrderItem;
import ua.foodstore.entities.PurchaseOrder;
import ua.foodstore.entities.MenuPosition;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;


/** 
* This JPA controller class (to follow DAO design pattern)
* Provides necessary operations with PurchaseOrder entity.
*
* @author <a href="mailto:igor_aviant@ukr.net">Igor Yushchenko</a>
* @version $ $Date: July 21, 2013 $
*/
public class OrderJpaController 
{

	private EntityManagerFactory emf;

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() 
	{
		return emf;
	}

	/**
	 * @param emf the emf to set
	 */
	public void setEmf(EntityManagerFactory emf) 
	{
		this.emf = emf;
	}

	/**
	 * @param emf
	 */
	public OrderJpaController(EntityManagerFactory emf) 
	{
		this.emf = emf;
	}
	
	public OrderJpaController() 
	{
		
	}
	
	public EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
	
	/**
	* Add new order into database.
	*
	* @param orderItems - HashMap<MenuPosition, Integer> - structure consist of ordered items and quantity
	* @param customerName - name of the customer
	* @param customerEmail - email of the customer
	* @param customerAdress - delivery address of the customer
	* @return void
	*/
	public void addOrder(HashMap<MenuPosition, Integer> orderItems, 
			             GregorianCalendar orderDate,
			             String customerName,
			             String customerEmail,
			             String customerAdress)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			PurchaseOrder newOrder = new PurchaseOrder();
			newOrder.setOrderDate(orderDate);
			newOrder.setCustomerName(customerName);
			newOrder.setCustomerEmail(customerEmail);
			newOrder.setCustomerAdress(customerAdress);
			List<OrderItem> orderList = new ArrayList<OrderItem>();
			for (Map.Entry<MenuPosition, Integer> entry : orderItems.entrySet()) 
			{
				MenuPosition mp = entry.getKey();
				int quantity    = entry.getValue();
				OrderItem orderPosition = new OrderItem();
				orderPosition.setOrder(newOrder);
				orderPosition.setPosition(mp);
				orderPosition.setQuantity(quantity);
				orderPosition.setPrice(mp.getPrice());
				orderPosition.setAmount(mp.getPrice().multiply(new BigDecimal(quantity)));
				orderPosition.setReady(mp.getKitchenmade() == 0 ? 1 : 0);
				orderList.add(orderPosition);
			}
			newOrder.setOrderList(orderList);
			em.persist(newOrder);
			//em.persist(orderList);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Sets ready sign in order item by given order item id
	* @param orderItemId - id of order item
	* @return void
	*/
	public void setReadyOrderItem(int orderItemId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			OrderItem item = em.find(OrderItem.class, orderItemId);
			item.setReady(1);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Sets not ready sign in order item by given order item id
	* @param orderItemId - id of order item
	* @return void
	*/
	public void setNotReadyOrderItem(int orderItemId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			OrderItem item = em.find(OrderItem.class, orderItemId);
			item.setReady(0);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Method finds all ordered positions, which have sign - kitchen-made
	* and which are not ready yet
	* @return List<OrderItem> - Array of menu items sorted in ascending order by the time of an order 
	*/
	public List<OrderItem> getOrdersForKitchen()
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			TypedQuery<OrderItem> query = 
					em.createQuery("SELECT oitem FROM OrderItem oitem Where oitem.ready=0  ORDER BY oitem.order.orderDate, oitem.id",OrderItem.class);
			List<OrderItem> resList = query.getResultList();
			return resList;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Retrieve data for generating business report
	* Data selecting between two dates given via parameters
	* @param startDate
	* @param endDate
	* @return List<ReportEntry> - Array list of data 
	*/
	public List<ReportEntry> getReportData(GregorianCalendar startDate, GregorianCalendar endDate)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();		
			TypedQuery<ReportEntry> query = em.createQuery(
					"SELECT new ua.erest.controllers.ReportEntry(oi.position.name, oi.position.category.name,SUM(oi.quantity),SUM(oi.amount)) from OrderItem oi Where oi.order.orderDate BETWEEN :sDate AND :eDate GROUP BY oi.position.name,oi.position.category",
					ReportEntry.class);
			
			query.setParameter("sDate", startDate);
			query.setParameter("eDate", endDate);
			List<ReportEntry> resList = query.getResultList();
			return resList;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Retrieve data for generating business report
	* Data selecting between two dates given via parameters
	* @param startDate
	* @param endDate
	* @return List<ReportEntry> - Array list of data 
	*/
	public List<ReportEntryCategory> getReportDataByCategory(GregorianCalendar startDate, GregorianCalendar endDate)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();			
			TypedQuery<ReportEntryCategory> query = em.createQuery(
					"SELECT new ua.erest.controllers.ReportEntryCategory(oi.position.category.name,SUM(oi.quantity),SUM(oi.amount)) from OrderItem oi Where oi.order.orderDate BETWEEN :sDate AND :eDate GROUP BY oi.position.category",
					ReportEntryCategory.class);
			
			query.setParameter("sDate", startDate);
			query.setParameter("eDate", endDate);
			List<ReportEntryCategory> resList = query.getResultList();
			return resList;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Return PurchaseOrder reference by given order id
	* @param orederId - id of an order
	* @return PurchaseOrder 
	*/
	public PurchaseOrder getOrderById(int orederId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			PurchaseOrder res = em.find(PurchaseOrder.class, orederId);
			return res;
		} finally 
		{
			em.close();
		}
	}
	
	/**
	* Remove PurchaseOrder from DB by given order id
	* @param orederId - id of an order
	* @return void 
	*/
	public void removeOrder(int orederId)
	{
		EntityManager em = null;
		try 
		{
			em = getEntityManager();
			em.getTransaction().begin();
			PurchaseOrder res = em.find(PurchaseOrder.class, orederId);
			if (res != null) em.remove(res);
			em.getTransaction().commit();
		} finally 
		{
			em.close();
		}
	}
	
}
