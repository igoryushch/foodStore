package ua.foodstore.managedbeans;

import ua.foodstore.entities.MenuCategory;
import ua.foodstore.services.MenuCategoryService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "categoryList")
@SessionScoped
public class CategoryListBean
{

    private List<MenuCategoryBean> categories;
    @ManagedProperty( value = "#{menuCategoryService}" )
    private MenuCategoryService menuCategoryService;

    public CategoryListBean()
    {

    }

    public List<MenuCategoryBean> getCategories()
    {
        return categories;
    }

    public void setCategories( List<MenuCategoryBean> categories )
    {
        this.categories = categories;
    }

    public void setMenuCategoryService( MenuCategoryService menuCategoryService )
    {
        this.menuCategoryService = menuCategoryService;
        System.out.println("Injected in menu category bean!");
    }

    public MenuCategoryService getMenuCategoryService()
    {
        return menuCategoryService;
    }

    public MenuCategoryBean returnMenuCategoryByName(String catname)
    {
        for ( MenuCategoryBean mcb : categories)
        {
            if ( mcb.getName().equals( catname) )
            {
                return mcb;
            }
        }
        
        return null;
    }
    
    public MenuCategoryBean returnMenuCategoryById(String catId)
    {
        for ( MenuCategoryBean mcb : categories)
        {
            if ( mcb.getId().equals(catId) )
            {
                return mcb;
            }
        }

        return null;
    }

    public MenuCategoryBean getCurrentMenuCategory()
    {
        return categories.get( 0 );
    }

    @PostConstruct
    public void init() {

        List<MenuCategory> categoriesList = menuCategoryService.findAll();

        categories = new ArrayList<MenuCategoryBean>();

        if (categoriesList != null) {
            for (MenuCategory mc : categoriesList) {
                categories.add(new MenuCategoryBean(String.valueOf(mc.getId()),
                        mc.getName(), "categmenu"));
            }
        }
    }
}
