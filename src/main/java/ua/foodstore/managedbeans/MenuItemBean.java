
package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ua.foodstore.entities.MenuPosition;

@ManagedBean(name = "menuItem", eager = true)
@RequestScoped
public class MenuItemBean
{

    private String id;
    private String name;
    private String description;
    private MenuCategoryBean category;
    private String categoryName;
    private int weight;
    private String price;
    private int quantity = 1;
    private int kitchenmade;
    private int active;
    private String imagePath;
    private MenuPosition menuPosition;
    
    public MenuItemBean()
    {
    }

    public MenuItemBean( String id, String name, String description, MenuCategoryBean category, int weight, String price, int kitchenmade, int active, String imagePath, MenuPosition menuPosition)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.categoryName = category.getName();
        this.weight = weight;
        this.price = price;
        this.kitchenmade = kitchenmade;
        this.active = active;
        this.imagePath = imagePath;
        this.menuPosition = menuPosition;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public MenuCategoryBean getCategory()
    {
        return category;
    }

    public void setCategory( MenuCategoryBean category )
    {
        this.category = category;
    }
    
    public int getWeight()
    {
        return weight;
    }

    public void setWeight( int weight )
    {
        this.weight = weight;
    }

    public String getPrice()
    {
        
        return price;
    }

    public void setPrice( String price )
    {
        
        this.price = price;
    }

    public boolean getKitchenmade()
    {
        return kitchenmade > 0 ? true : false;
    }

    public void setKitchenmade( boolean kitchenmade )
    {
        this.kitchenmade = (kitchenmade == true) ? 1 : 0;
       // this.menuPosition.setKitchenmade(this.kitchenmade);
    }

    public boolean getActive()
    {
        return active > 0 ? true : false;
    }

    public void setActive( boolean active )
    {
        this.active = (active == true) ? 1 : 0;
        //this.menuPosition.setActive(this.active);
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath( String imagePath )
    {
        this.imagePath = imagePath;
    } 

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    public double getSum()
    {
        return this.quantity * Double.parseDouble(this.price);
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName( String categoryName )
    {
        this.categoryName = categoryName;
    }

    public void setKitchenmade( int kitchenmade )
    {
        this.kitchenmade = kitchenmade;
    }

    public void setActive( int active )
    {
        this.active = active;
    }

    public MenuPosition getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(MenuPosition menuPosition) {
        this.menuPosition = menuPosition;
    }
    
}
