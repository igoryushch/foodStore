package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import java.util.Objects;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "menuCategory", eager = true)
@RequestScoped
public class MenuCategoryBean{

    private String id;
    private String name;
    private String styleClass;

    public MenuCategoryBean( String id, String name,  String styleClass)
    {
        this.id = id;
        this.name = name;
        this.styleClass = styleClass;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }
    
    public MenuCategoryBean() {
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getStyleClass()
    {
        return styleClass;
    }

    public void setStyleClass( String styleClass )
    {
        this.styleClass = styleClass;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode( this.id );
        hash = 71 * hash + Objects.hashCode( this.name );
        hash = 71 * hash + Objects.hashCode( this.styleClass );
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final MenuCategoryBean other = (MenuCategoryBean) obj;
        if ( !Objects.equals( this.id, other.id ) )
        {
            return false;
        }
        if ( !Objects.equals( this.name, other.name ) )
        {
            return false;
        }
        if ( !Objects.equals( this.styleClass, other.styleClass ) )
        {
            return false;
        }
        return true;
    }
  
    
}
