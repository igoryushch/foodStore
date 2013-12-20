
package ua.foodstore.services;

import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.entities.MenuPosition;

import java.util.List;
import ua.foodstore.entities.MenuCategory;


public interface MenuPositionService {
    
    @Transactional
    public void addNewPosition(String name, 
                               String description,
			       String category, 
                               int weight, 
                               String price,
			       int kitchenmade,
                               int active,
                               String imagePath);
    
    @Transactional
    public void saveUpdateMenuPosition(MenuPosition mp);
    
    @Transactional
    public List<MenuPosition> findAll();
    
    @Transactional(readOnly = true)
    public List<MenuPosition> findActive();

    @Transactional(readOnly = true)
    public List<MenuPosition> findActiveByCategoryId(int mcId);
    
    @Transactional(readOnly = true)
    public MenuCategory findCategoryByName(String categoryName);
    
}
