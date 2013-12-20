/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import ua.foodstore.entities.OrderItem;
import ua.foodstore.entities.PurchaseOrder;

@Repository("purchaseOrderDao")
public class PurchaseOrderDaoImpl extends GenericDaoImpl<PurchaseOrder> implements PurchaseOrderDao {

    @Override
    public List<PurchaseOrder> findAll() {
        return em.createNamedQuery("PurchaseOrder.findAll",PurchaseOrder.class).getResultList();
    }

    @Override
    public List<OrderItem> findNotReady() {
        
        return em.createNamedQuery("OrderItem.findNotReadyOrdered",OrderItem.class).getResultList();
    }

    @Override
    public void updateReadyItems(List<Integer> ids) {
        em.createQuery("UPDATE OrderItem oi SET oi.ready = 1 WHERE oi.id IN :idList").setParameter("idList", ids).executeUpdate();
    }
    
}
