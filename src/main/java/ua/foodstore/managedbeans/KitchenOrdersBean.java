package ua.foodstore.managedbeans;

import ua.foodstore.entities.MenuPosition;
import ua.foodstore.entities.OrderItem;
import ua.foodstore.services.PurchaseOrderService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "kitchenOrders", eager = true)
@RequestScoped
public class KitchenOrdersBean
{
    @ManagedProperty( value = "#{orderService}" )
    PurchaseOrderService orderService;
    private List<OrderedItemsBean> kitchenList;
    private OrderedItemsBean[] selectedItems;
    @ManagedProperty( value = "#{categoryList}" )
    private CategoryListBean categoryList;

    public KitchenOrdersBean()
    {
    }

    public List<OrderedItemsBean> getKitchenList()
    {
        return kitchenList;
    }

    public void setKitchenList( List<OrderedItemsBean> kitchenList )
    {
        this.kitchenList = kitchenList;
    }

    public OrderedItemsBean[] getSelectedItems()
    {
        return selectedItems;
    }

    public void setSelectedItems( OrderedItemsBean[] selectedItems )
    {
        this.selectedItems = selectedItems;
    }

    public PurchaseOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(PurchaseOrderService orderService) {
        this.orderService = orderService;
    }

    public CategoryListBean getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryListBean categoryList) {
        this.categoryList = categoryList;
    }
    
    public String updateKitchenList()
    {
        if (selectedItems.length > 0) {
            List<Integer> idIs = new ArrayList<Integer>(selectedItems.length);
            for (int i = 0; i < selectedItems.length; i++) {
                idIs.add(Integer.valueOf(selectedItems[i].getIndex()));
            }
            orderService.setUpdateReadyItems(idIs);
            //initOrderItems();
        }
 
        
        return "kitchen?faces-redirect=true";
    }

    @PostConstruct
    public void initOrderItems()
    {

        kitchenList = new ArrayList<OrderedItemsBean>();

        List<OrderItem> ordersForKitchen = orderService.findNotReady();
        
        if (ordersForKitchen != null)
        {
            for(OrderItem oi : ordersForKitchen)
            {
                MenuPosition mi = oi.getPosition();
                kitchenList.add( 
                        new OrderedItemsBean( Integer.toString(oi.getId()),
                        oi.getOrder().getOrderDate(), 
                        new MenuItemBean(Integer.toString(mi.getId()), 
                                        mi.getName(), 
                                        mi.getDescription(), 
                                        categoryList.returnMenuCategoryByName(mi.getCategory().getName()),
                                   mi.getWeight(), mi.getPrice().toString(), mi.getKitchenmade(), mi.getActive(), 
                                   mi.getImagePath(), oi.getPosition()), 
                         false) );
            }
        }
        
    }
}
