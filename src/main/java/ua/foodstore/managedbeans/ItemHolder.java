package ua.foodstore.managedbeans;

//~--- JDK imports ------------------------------------------------------------

import ua.foodstore.entities.MenuPosition;
import ua.foodstore.services.MenuCategoryService;
import ua.foodstore.services.MenuPositionService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name  = "ih")
@SessionScoped
public class ItemHolder{
    @ManagedProperty( value = "#{shopCart}" )
    private ShopCartBean cart;
    @ManagedProperty( value = "#{menuCategoryService}" )
    private MenuCategoryService menuCategoryService;
    @ManagedProperty( value = "#{categoryList}" )
    private CategoryListBean categoryList;
    private String total;
    private List<MenuItemBean> menu;
    private MenuItemBean                        currentItem;
    private String                              menuItemId;
    private MenuCategoryBean                    selectedCategory;
    @ManagedProperty( value = "#{menuPositionService}" )
    private MenuPositionService menuPositionService;

    public List<MenuItemBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItemBean> menu) {
        this.menu = menu;
    }

    public MenuCategoryBean getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(MenuCategoryBean selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public MenuItemBean getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(MenuItemBean currentItem) {
        this.currentItem = currentItem;
    }

    public ShopCartBean getCart() {
        return cart;
    }

    public void setCart(ShopCartBean cart) {
        this.cart = cart;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public MenuPositionService getMenuPositionService()
    {
        return menuPositionService;
    }

    public void setMenuPositionService( MenuPositionService menuPositionService )
    {
        this.menuPositionService = menuPositionService;
    }

    public CategoryListBean getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList( CategoryListBean categoryList )
    {
        this.categoryList = categoryList;
    }

    public MenuCategoryService getMenuCategoryService()
    {
        return menuCategoryService;
    }

    public void setMenuCategoryService( MenuCategoryService menuCategoryService )
    {
        this.menuCategoryService = menuCategoryService;
    }

    public String selectCategory(String categoryId) {
        if (this.selectedCategory != null) {
            this.selectedCategory.setStyleClass("categmenu");
        }

        this.selectedCategory = categoryList.returnMenuCategoryById( categoryId );
        this.selectedCategory.setStyleClass("selectedcategmenu");

        fillMenu();

        return "base";
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
        currentItem     = findMenuItemById(menuItemId);
    }

    public MenuItemBean findMenuItem(String menuItemName) {

        for (MenuItemBean mi : menu) {
                if (mi.getName().equals(menuItemName)) {
                    return mi;
                }
            }

        return null;
    }

    public MenuItemBean findMenuItemById(String menuItemId) {
        for (MenuItemBean mi : menu) {
                if (mi.getId().equals(menuItemId)) {
                    return mi;
                }
            }

        return null;
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String addToCart(String id) {
        setMenuItemId(id);
        cart.addItem(currentItem);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, currentItem.getName(),  "Added to your shop cart!");           
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        return null;
    }

    @PostConstruct
    public void init() {

        assignSelectedCategory();
        menu         = new ArrayList<MenuItemBean>();
        total        = "$ 0.00";

        fillMenu();

    }

    public void assignSelectedCategory()
    {
        selectedCategory = categoryList.getCurrentMenuCategory();
        selectedCategory.setStyleClass("selectedcategmenu");
    }

    public void fillMenu(){

        menu.clear();

        List<MenuPosition> currentMenu = menuPositionService.findActiveByCategoryId(Integer.parseInt( selectedCategory.getId() ));

        if (currentMenu != null) {
            for (MenuPosition mp : currentMenu) {

                menu.add(new MenuItemBean(Integer.toString(mp.getId()),
                        mp.getName(),
                        mp.getDescription(),
                        selectedCategory,
                        mp.getWeight(),
                        mp.getPrice().toString(),
                        mp.getKitchenmade(), mp.getActive(), mp.getImagePath(), mp));
            }
        }

    }
}
