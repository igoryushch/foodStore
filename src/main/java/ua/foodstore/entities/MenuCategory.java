package ua.foodstore.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MENUCATEGORY")
@XmlRootElement
@NamedQueries(
        {
    @NamedQuery(name = "MenuCategory.findAll", query = "SELECT m FROM MenuCategory m"),
    @NamedQuery(name = "MenuCategory.findById", query = "SELECT m FROM MenuCategory m WHERE m.id = :id"),
    @NamedQuery(name = "MenuCategory.findByName", query = "SELECT m FROM MenuCategory m WHERE m.name = :name")
})
public class MenuCategory implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public MenuCategory()
    {
    }

    /**
     * @param id
     * @param name
     */
    public MenuCategory( int id, String name )
    {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Category [" + name + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( !( obj instanceof MenuCategory ) )
        {
            return false;
        }
        MenuCategory other = (MenuCategory) obj;
        if ( id != other.id )
        {
            return false;
        }
        if ( name == null )
        {
            if ( other.name != null )
            {
                return false;
            }
        }
        else if ( !name.equals( other.name ) )
        {
            return false;
        }
        return true;
    }
}
