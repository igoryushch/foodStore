package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean(name = "order", eager = true)
@SessionScoped
public class OrderBean {

    private GregorianCalendar orderDate;
    private String customerName;
    private String customerEmail;
    private String customerAdress;
    private List<MenuItemBean> orderItems = new ArrayList<MenuItemBean>();
    
    public OrderBean() {
    }

    public GregorianCalendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(GregorianCalendar orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAdress() {
        return customerAdress;
    }

    public void setCustomerAdress(String customerAdress) {
        this.customerAdress = customerAdress;
    }

    public List<MenuItemBean> getOrderItems()
    {
        return orderItems;
    }

    public void setOrderItems( List<MenuItemBean> orderItems )
    {
        this.orderItems = orderItems;
    }
    
}
