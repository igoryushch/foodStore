/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.entities.OrderItem;
import ua.foodstore.entities.PurchaseOrder;
import ua.foodstore.managedbeans.MenuItemBean;

import java.util.GregorianCalendar;
import java.util.List;

public interface PurchaseOrderService {
    
    @Transactional
    void addOrder(List<MenuItemBean> orderItems,
			             GregorianCalendar orderDate,
			             String customerName,
			             String customerEmail,
			             String customerAdress);
    
    @Transactional
    void saveUpdateOrder(PurchaseOrder po);
    
    @Transactional(readOnly = true)
    public List<PurchaseOrder> findAll();
    
    @Transactional(readOnly = true)
    public List<OrderItem> findNotReady();
    
    @Transactional
    public void setUpdateReadyItems(List<Integer> ids);
    
}
