package ua.foodstore.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PurchaseOrder.findAll", query = "SELECT po FROM PurchaseOrder po"),
    @NamedQuery(name = "PurchaseOrder.findById", query = "SELECT po FROM PurchaseOrder po WHERE po.id = :id"),
    @NamedQuery(name = "PurchaseOrder.findByOrderdate", query = "SELECT po FROM PurchaseOrder po WHERE po.orderDate = :orderDate"),
    @NamedQuery(name = "PurchaseOrder.findByCustomername", query = "SELECT po FROM PurchaseOrder po WHERE po.customerName = :customerName"),
    @NamedQuery(name = "PurchaseOrder.findByCustomeremail", query = "SELECT po FROM PurchaseOrder po WHERE po.customerEmail = :customerEmail"),
    @NamedQuery(name = "PurchaseOrder.findByCustomeradress", query = "SELECT po FROM PurchaseOrder po WHERE po.customerAdress = :customerAdress")
})
public class PurchaseOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar orderDate;
	private String customerName;
	private String customerEmail;
	private String customerAdress;
	@OneToMany(mappedBy="order",
			   cascade=CascadeType.ALL)
	private List<OrderItem> orderList;
	
	
	
	/**
	 * @param id
	 * @param orderDate
	 * @param customerName
	 * @param customerEmail
	 * @param customerAdress
	 * @param orderList
	 */
	public PurchaseOrder(int id, Calendar orderDate, String customerName,
			String customerEmail, String customerAdress,
			List<OrderItem> orderList) {
		this.id = id;
		this.orderDate = orderDate;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAdress = customerAdress;
		this.orderList = orderList;
	}
	/**
	 * 
	 */
	public PurchaseOrder() {
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the orderDate
	 */
	public Calendar getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	/**
	 * @return the customerAdress
	 */
	public String getCustomerAdress() {
		return customerAdress;
	}
	/**
	 * @param customerAdress the customerAdress to set
	 */
	public void setCustomerAdress(String customerAdress) {
		this.customerAdress = customerAdress;
	}
	/**
	 * @return the orderList
	 */
	public List<OrderItem> getOrderList() {
		return orderList;
	}
	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(List<OrderItem> orderList) {
		this.orderList = orderList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		return "Order [id=" + id + ", orderDate=" + dtFrm.format(orderDate.getTime())
				+ ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerAdress=" + customerAdress
				+ "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) 
	{
		SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PurchaseOrder)) {
			return false;
		}
		PurchaseOrder other = (PurchaseOrder) obj;
		if (customerAdress == null) {
			if (other.customerAdress != null) {
				return false;
			}
		} else if (!customerAdress.equals(other.customerAdress)) {
			return false;
		}
		if (customerEmail == null) {
			if (other.customerEmail != null) {
				return false;
			}
		} else if (!customerEmail.equals(other.customerEmail)) {
			return false;
		}
		if (customerName == null) {
			if (other.customerName != null) {
				return false;
			}
		} else if (!customerName.equals(other.customerName)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (orderDate == null) {
			if (other.orderDate != null) {
				return false;
			}
		} else if (!dtFrm.format(orderDate.getTime()).equals(dtFrm.format(other.orderDate.getTime()))) {
			return false;
		}
		if (orderList == null) {
			if (other.orderList != null) {
				return false;
			}
		}
//		
//		for(int i = 0;i<orderList.size();i++)
//		{
//			if (!orderList.get(i).equals(other.orderList.get(i)))
//				return false;
//		}
		
		return true;
	}

	
	
}
