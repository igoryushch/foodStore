/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.dao.MenuPositionDao;
import ua.foodstore.entities.MenuCategory;
import ua.foodstore.entities.MenuPosition;

import java.math.BigDecimal;
import java.util.List;
import ua.foodstore.dao.MenuCategoryDao;

@Transactional
@Service("menuPositionService")
public class MenuPositionServiceImpl implements MenuPositionService{

    @Autowired
    private MenuPositionDao dao;
    @Autowired
    private MenuCategoryDao categoryDao;
    
    @Transactional
    @Override
    public void addNewPosition(String name, 
                               String description,
			       String category, 
                               int weight, 
                               String price,
			       int kitchenmade,
                               int active,
                               String imagePath)
    {
        MenuPosition mp = new MenuPosition();
        mp.setName(name);
        mp.setDescription(description);
        mp.setCategory(categoryDao.findByName(category));
        mp.setWeight(weight);
        mp.setPrice(new BigDecimal(price));
        mp.setKitchenmade(kitchenmade);
        mp.setActive(active);
        
        dao.create(mp);
    }
    
    @Transactional
    @Override
    public void saveUpdateMenuPosition(MenuPosition mp)
    {
        dao.update(mp);
    }  
    
    @Transactional(readOnly = true)
    @Override
    public List<MenuPosition> findAll() {
        return dao.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<MenuPosition> findActive() {
        return dao.findActive();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuPosition> findActiveByCategoryId(int mcId) {
        return dao.findActiveByCategory( mcId );
    }
    
    @Transactional(readOnly = true)
    @Override
    public MenuCategory findCategoryByName(String categoryName)
    {
        return categoryDao.findByName(categoryName);
    }
    
}
