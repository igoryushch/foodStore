/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foodstore.dao.MenuCategoryDao;
import ua.foodstore.entities.MenuCategory;

@Service("menuCategoryService")
public class MenuCategoryServiceImpl implements MenuCategoryService
{

    @Autowired
    private MenuCategoryDao dao;
    
    @Override
    public List<MenuCategory> findAll() {
        return dao.findAll();
    }
    
}
