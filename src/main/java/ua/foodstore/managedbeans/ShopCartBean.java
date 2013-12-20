package ua.foodstore.managedbeans;

import org.springframework.stereotype.Component;
import ua.foodstore.services.PurchaseOrderService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component
@ManagedBean(name = "shopCart", eager = true)
@SessionScoped
public class ShopCartBean
{
    @ManagedProperty( value = "#{orderService}" )
    PurchaseOrderService orderService;
    @ManagedProperty( value = "#{order}" )
    private OrderBean order;
    private List<MenuItemBean> items = new ArrayList<MenuItemBean>();
    
    public ShopCartBean()
    {
    }

    public List<MenuItemBean> getItems()
    {
        return items;
    }

    public void setItems( List<MenuItemBean> items )
    {
        this.items = items;
    }

    public OrderBean getOrder()
    {
        return order;
    }

    public void setOrder( OrderBean order )
    {
        this.order = order;
    }

    public PurchaseOrderService getOrderService()
    {
        return orderService;
    }

    public void setOrderService( PurchaseOrderService orderService )
    {
        this.orderService = orderService;
    }

    public void addItem(MenuItemBean mib)
    {
        int index = items.indexOf( mib );
        if(index < 0)
        {
            mib.setQuantity( 1 );
            items.add( mib );
            
        }
        else
        {
            mib.setQuantity( mib.getQuantity() + 1 );
        }
    }
    
    public String remove(MenuItemBean mib)
    {
        items.remove( mib );
        return "cart";
    }
    
    public double getTotal()
    {
        double restotal = 0;

        for (MenuItemBean mib : items) {
            restotal += mib.getSum();
        }

        return restotal;
    }
    
    public String saveOrder()
    {
        order.setOrderItems( items );
        order.setOrderDate( new GregorianCalendar(  ) );
        orderService.addOrder( items, order.getOrderDate(),
                                      order.getCustomerName(),
                                      order.getCustomerEmail(),
                                      order.getCustomerAdress());

        return "accepted";
    }
    
}
