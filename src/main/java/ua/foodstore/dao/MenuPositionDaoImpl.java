/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import org.springframework.stereotype.Repository;
import ua.foodstore.entities.MenuCategory;
import ua.foodstore.entities.MenuPosition;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("menuPositionDao")
public class MenuPositionDaoImpl extends GenericDaoImpl<MenuPosition> implements MenuPositionDao{

    @Override
    public List<MenuPosition> findAll() {
        return em.createNamedQuery("MenuPosition.findAll", MenuPosition.class).getResultList();
    }
    
    @Override
    public List<MenuPosition> findActive() {
        return em.createNamedQuery("MenuPosition.findByActive", MenuPosition.class).setParameter("active", 1).getResultList();
    }

    @Override
    public List<MenuPosition> findActiveByCategory( int mcId )
    {
        TypedQuery<MenuPosition> query = em.createQuery( "SELECT mp from MenuPosition mp Where mp.active=1 and mp.category.id = :catId", MenuPosition.class );
        query.setParameter( "catId", mcId );
        List<MenuPosition> menu = query.getResultList();
        return menu;
    }
}
