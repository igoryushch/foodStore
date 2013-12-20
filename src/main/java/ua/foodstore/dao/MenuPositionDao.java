/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import ua.foodstore.entities.MenuPosition;

import java.util.List;

public interface MenuPositionDao extends GenericDao<MenuPosition>{
    
    public List<MenuPosition> findAll();
    public List<MenuPosition> findActive();
    public List<MenuPosition> findActiveByCategory( int mcId );
    
}
