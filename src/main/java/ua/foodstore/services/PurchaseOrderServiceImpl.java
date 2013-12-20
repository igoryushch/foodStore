/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.dao.MenuPositionDao;
import ua.foodstore.dao.PurchaseOrderDao;
import ua.foodstore.entities.MenuPosition;
import ua.foodstore.entities.OrderItem;
import ua.foodstore.entities.PurchaseOrder;
import ua.foodstore.managedbeans.MenuItemBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Service("orderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    @Autowired
    private PurchaseOrderDao dao;
    @Autowired
    private MenuPositionDao mpDao;
          
    @Transactional
    @Override
    public void addOrder(List<MenuItemBean> orderItems, GregorianCalendar orderDate, String customerName, String customerEmail, String customerAdress) {

        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setOrderDate(orderDate);
        newOrder.setCustomerName(customerName);
        newOrder.setCustomerEmail(customerEmail);
        newOrder.setCustomerAdress(customerAdress);
        List<OrderItem> orderList = new ArrayList<OrderItem>();
        for (MenuItemBean mib : orderItems) {
            MenuPosition mp = mpDao.read( Integer.parseInt( mib.getId()) );
            int quantity = mib.getQuantity();
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
        dao.create(newOrder);
        
    }

    @Transactional
    @Override
    public void saveUpdateOrder(PurchaseOrder po) {
        dao.update(po);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseOrder> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderItem> findNotReady() {
        return dao.findNotReady();
    }

    @Transactional
    @Override
    public void setUpdateReadyItems(List<Integer> ids) {
        dao.updateReadyItems(ids);
    }
    
}
