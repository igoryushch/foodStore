/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.entities.OrderItem;
import ua.foodstore.entities.PurchaseOrder; 

public interface PurchaseOrderDao extends GenericDao<PurchaseOrder> {
    
    @Transactional(readOnly = true)
    public List<PurchaseOrder> findAll();
    
    @Transactional(readOnly = true)
    public List<OrderItem> findNotReady();
    
    @Transactional
    public void updateReadyItems(List<Integer> ids);
}
