/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import ua.foodstore.entities.MenuCategory;
 
@Repository("menuCategoryDao")
public class MenuCategoryDaoImpl extends GenericDaoImpl<MenuCategory> implements MenuCategoryDao{

    @Override
    public List<MenuCategory> findAll() {
        return em.createNamedQuery("MenuCategory.findAll",MenuCategory.class).getResultList();
    }
    
    @Override
    public MenuCategory findByName(String catName) {
        return em.createNamedQuery("MenuCategory.findByName",MenuCategory.class).setParameter("name", catName).getSingleResult();
    }
    
}
