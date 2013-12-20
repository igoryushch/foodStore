package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import java.util.Calendar;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "orderedItem", eager = true)
@RequestScoped
public class OrderedItemsBean
{

    private String index;
    private Calendar orderDate;
    private MenuItemBean orderedItem;
    private boolean ready;
    
    public OrderedItemsBean()
    {
    }

    public OrderedItemsBean( String index, Calendar orderDate, MenuItemBean orderedItem, boolean ready )
    {
        this.index = index;
        this.orderDate = orderDate;
        this.orderedItem = orderedItem;
        this.ready = ready;
    }
            
    public Calendar getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate( Calendar orderDate )
    {
        this.orderDate = orderDate;
    }

    public MenuItemBean getOrderedItem()
    {
        return orderedItem;
    }

    public void setOrderedItem( MenuItemBean orderedItem )
    {
        this.orderedItem = orderedItem;
    }

    public boolean isReady()
    {
        return ready;
    }

    public void setReady( boolean ready )
    {
        this.ready = ready;
    }

    public String getIndex()
    {
        return index;
    }

    public void setIndex( String index )
    {
        this.index = index;
    }
      
    
}
