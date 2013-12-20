package ua.foodstore.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USERROLE")
@XmlRootElement
@NamedQueries(
        {
    @NamedQuery(name = "UserRole.findAll", query = "SELECT r FROM UserRole r"),
    @NamedQuery(name = "UserRole.findById", query = "SELECT r FROM UserRole r WHERE r.id = :id"),
    @NamedQuery(name = "UserRole.findByName", query = "SELECT r FROM UserRole r WHERE r.name = :name")
})
public class UserRole implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ElementCollection(targetClass = String.class)
    @CollectionTable(
            name = "userpage",
            joinColumns =
            @JoinColumn(name = "roleid"))
    private Set<String> pagename;

    /**
     * @param id
     * @param name
     */
    public UserRole( int id, String name, Set<String> pagename )
    {
        this.id = id;
        this.name = name;
        this.pagename = pagename;
    }

    /**
     *
     */
    public UserRole()
    {
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
    public Set<String> getPagename() {
        return pagename;
    }

    public void setPagename(Set<String> pagename) {
        this.pagename = pagename;
    }
    
    @Override
    public String toString()
    {
        return name;
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
        if ( !( obj instanceof UserRole ) )
        {
            return false;
        }
        UserRole other = (UserRole) obj;
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
