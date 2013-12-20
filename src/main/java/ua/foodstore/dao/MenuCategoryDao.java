/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import ua.foodstore.entities.MenuCategory;

public interface MenuCategoryDao extends GenericDao<MenuCategory>{
    
    public List<MenuCategory> findAll();
    public MenuCategory findByName(String catName);
    
}
