
package ua.foodstore.managedbeans;

import org.primefaces.event.RowEditEvent;
import ua.foodstore.entities.MenuPosition;
import ua.foodstore.services.MenuPositionService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "cmh", eager = true)
@RequestScoped
public class CurrentMenuHolder
{
    @ManagedProperty( value = "#{menuPositionService}" )
    MenuPositionService menuService;
    @ManagedProperty( value = "#{categoryList}" )
    private CategoryListBean categoryList;
    private List<MenuItemBean> menu;
    
    public CurrentMenuHolder()
    {
        
    }

    public CategoryListBean getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList( CategoryListBean categoryList )
    {
        this.categoryList = categoryList;
    }

    public List<MenuItemBean> getMenu()
    {
        return menu;
    }

    public void setMenu( List<MenuItemBean> menu )
    {
        this.menu = menu;
    }

    public MenuPositionService getMenuService()
    {
        return menuService;
    }

    public void setMenuService( MenuPositionService menuService )
    {
        this.menuService = menuService;
    }

    public String addMenuItem( MenuItemBean mbItemBean )
    {
        
        menuService.addNewPosition(mbItemBean.getName(), 
                                   mbItemBean.getDescription(), 
                                   mbItemBean.getCategoryName(),
                                   mbItemBean.getWeight(), 
                                   mbItemBean.getPrice(), 
                                   (mbItemBean.getKitchenmade() == true ? 1 : 0), 
                                   (mbItemBean.getActive() == true ? 1 : 0)
                                   ,mbItemBean.getImagePath());
        
       //initMenu();
        return "menuadmin?faces-redirect=true";
    }

    public void rowEditListener(RowEditEvent ev)
    {
        MenuItemBean menuItemBean = (MenuItemBean) ev.getObject();
        MenuPosition mp = menuItemBean.getMenuPosition();
        mp.setCategory(menuService.findCategoryByName(menuItemBean.getCategoryName()));
        menuService.saveUpdateMenuPosition(mp);
            
    }

    @PostConstruct
    public void initMenu()
    {
        menu = new ArrayList<MenuItemBean>();
        menu.clear();
        List<MenuPosition> currentMenu = menuService.findActive();
        for (MenuPosition mp : currentMenu)
        {
            menu.add(new MenuItemBean(Integer.toString(mp.getId()), 
                    mp.getName(), 
                    mp.getDescription(), 
                    categoryList.returnMenuCategoryByName(mp.getCategory().getName()), 
                    mp.getWeight(), 
                    mp.getPrice().toString(),                  
                    mp.getKitchenmade(), mp.getActive(), mp.getImagePath(),mp));
        }
        
//        initSalads();
//        initAppetizers();
//        initPizzas();
//        initDesserts();
//        initDrinks();
    }
    
//    public void initSalads() {
//        MenuCategoryBean   category = categoryList.returnMenuCategoryByName("Salads");
////        List<MenuItemBean> mList    = new ArrayList<MenuItemBean>();
//
//        menu.add(new MenuItemBean("1", "Summer Salad", "cucumber, tomato, potato, olive oil", category, 150, "35.50",
//                                   1, 1, "images/dishes/salad/summersalad.jpg"));
//        menu.add(new MenuItemBean("2", "Caeser Salad", "cucumber, tomato, lettuce, olive, parmesan, chicken", category,
//                                   150, "35.50", 1, 1, "images/dishes/salad/CaeserSalad.jpg"));
//        menu.add(new MenuItemBean("3", "Avocado Salad", "avocado, lettuce, cream sauce", category, 150, "35.50", 1, 1,
//                                   "images/dishes/salad/saladavocado.jpg"));
//        menu.add(new MenuItemBean("4", "Prawn Salad", "prawn, lettuce, tomato", category, 150, "35.50", 1, 1,
//                                   "images/dishes/salad/Saladprawn.jpg"));
////        menu.put(category.getName(), mList);
//    }
//
//    public void initAppetizers() {
//        MenuCategoryBean   category = categoryList.returnMenuCategoryByName("Appetizers");
////        List<MenuItemBean> mList    = new ArrayList<MenuItemBean>();
//
//        menu.add(new MenuItemBean("5", "Appetizer mozzarella", " ", category, 40, "10.50", 1, 1,
//                                   "images/dishes/apetaizer/appetizermozarella.jpg"));
//        menu.add(new MenuItemBean("6", "Appetizer salmon", " ", category, 40, "10.50", 1, 1,
//                                   "images/dishes/apetaizer/appetizersalmon.jpg"));
//        menu.add(new MenuItemBean("7", "Appetizer cheese", " ", category, 40, "10.50", 1, 1,
//                                   "images/dishes/apetaizer/appetizerchisse.jpeg"));
//        menu.add(new MenuItemBean("8", "Appetizer cucumber", " ", category, 40, "10.50", 1, 1,
//                                   "images/dishes/apetaizer/appetizercucumber.jpg"));
//        menu.add(new MenuItemBean("9", "Appetizer prawn", " ", category, 40, "10.50", 1, 1,
//                                   "images/dishes/apetaizer/appetizerprawn.jpg"));
////        menu.put(category.getName(), mList);
//    }
//
//    public void initPizzas() {
//        MenuCategoryBean   category = categoryList.returnMenuCategoryByName("Pizzas");
////        List<MenuItemBean> mList    = new ArrayList<MenuItemBean>();
//
//        menu.add(new MenuItemBean("10", "Pizza 4 season", " ", category, 3500, "75.50", 1, 1,
//                                   "images/dishes/pizza/pizza4season.jpg"));
//        menu.add(new MenuItemBean("11", "Pizza Seafood", " ", category, 3500, "75.50", 1, 1,
//                                   "images/dishes/pizza/pizzaseafood.jpg"));
//        menu.add(new MenuItemBean("12", "Pizza Margherita", " ", category, 3500, "5.99", 1, 1,
//                                   "images/dishes/pizza/pizzamargherita.jpeg"));
//        menu.add(new MenuItemBean("13", "Pizza Salami", " ", category, 3500, "75.50", 1, 1,
//                                   "images/dishes/pizza/pizzasalami.jpg"));
//        menu.add(new MenuItemBean("14", "Pizza quattro formaggi", " ", category, 3500, "75.50", 1, 1,
//                                   "images/dishes/pizza/pizzaquattroformaggi.jpg"));
////        menu.put(category.getName(), mList);
//    }
//
//    public void initDesserts() {
//        MenuCategoryBean   category = categoryList.returnMenuCategoryByName("Desserts");
////        List<MenuItemBean> mList    = new ArrayList<MenuItemBean>();
//
//        menu.add(new MenuItemBean("15", "Tiramisu",
//                                   "An Italian dessert consisting of layers of sponge cake soaked in coffee", category,
//                                   120, "20.50", 1, 1, "images/dishes/desert/tiramisu.jpg"));
//        menu.add(new MenuItemBean("16", "Ice Cream", "Chocolate ice cream", category, 100, "10.50", 1, 1,
//                                   "images/dishes/desert/Ice_Cream.jpg"));
//        menu.add(new MenuItemBean("17", "Milk Jelly", "Milk-flavoured dessert", category, 150, "28.50", 1, 1,
//                                   "images/dishes/desert/milkjelly.jpeg"));
//        menu.add(new MenuItemBean("18", "Fruit Salad", "Consist of apple, pineapple, kiwi, oranges e.c.", category, 150,
//                                   "20.50", 1, 1, "images/dishes/desert/fruitsalad.jpg"));
//        menu.add(new MenuItemBean("19", "Cheesecake", "Dessert cake made with cream and soft cheese", category, 150,
//                                   "25.50", 1, 1, "images/dishes/desert/cheedecake.jpeg"));
////        menu.put(category.getName(), mList);
//    }
//
//    public void initDrinks() {
//        MenuCategoryBean   category = categoryList.returnMenuCategoryByName("Drinks");
////        List<MenuItemBean> mList    = new ArrayList<MenuItemBean>();
//
//        menu.add(new MenuItemBean("20", "Coffee", "Espresso Arabica", category, 60, "15.50", 0, 1,
//                                   "images/dishes/drinks/kofe.jpg"));
//        menu.add(new MenuItemBean("21", "Tea", "Black tea", category, 300, "12.50", 0, 1,
//                                   "images/dishes/drinks/tea.jpg"));
//        menu.add(new MenuItemBean("22", "Orange Juice", "Natural fresh orange juice", category, 200, "10.50", 0, 1,
//                                   "images/dishes/drinks/juice.jpeg"));
//        menu.add(new MenuItemBean("23", "Coca Cola", "Coca Cola", category, 300, "8.50", 0, 1,
//                                   "images/dishes/drinks/latte.jpeg"));
//        menu.add(new MenuItemBean("24", "Mineral Water", "Mineral water without gas", category, 200, "7.50", 0, 1,
//                                   "images/dishes/drinks/beers.jpeg"));
////        menu.put(category.getName(), mList);
//    }
}
