package ua.foodstore.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ua.foodstore.entities.MenuCategory;
        
public interface MenuCategoryService {
    
    @Transactional(readOnly = true)
    public List<MenuCategory> findAll();
    
}
