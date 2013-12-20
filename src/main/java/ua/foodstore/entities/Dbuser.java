package ua.foodstore.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DBUSER")
@XmlRootElement
@NamedQueries(
        {
    @NamedQuery(name = "Dbuser.findAll", query = "SELECT d FROM Dbuser d"),
    @NamedQuery(name = "Dbuser.findById", query = "SELECT d FROM Dbuser d WHERE d.id = :id"),
    @NamedQuery(name = "Dbuser.findByLogin", query = "SELECT d FROM Dbuser d WHERE d.login = :login"),
    @NamedQuery(name = "Dbuser.findByRole", query = "SELECT d FROM Dbuser d WHERE d.role = :role")
})
public class Dbuser implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    private String login;
    private String password;
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private UserRole role;

    /**
     * @param id
     * @param login
     * @param password
     * @param fullName
     * @param role
     */
    public Dbuser( int id, String login, String password, String fullName,
                   UserRole role )
    {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    /**
     *
     */
    public Dbuser()
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
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin( String login )
    {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName( String fullName )
    {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public UserRole getRole()
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole( UserRole role )
    {
        this.role = role;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "User [id=" );
        builder.append( id );
        builder.append( ", login=" );
        builder.append( login );
        builder.append( ", password=" );
        builder.append( password );
        builder.append( ", fullName=" );
        builder.append( fullName );
        builder.append( ", role=" );
        builder.append( role );
        builder.append( "]" );
        return builder.toString();
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ( ( fullName == null ) ? 0 : fullName.hashCode() );
        result = prime * result + id;
        result = prime * result + ( ( login == null ) ? 0 : login.hashCode() );
        result = prime * result
                + ( ( password == null ) ? 0 : password.hashCode() );
        result = prime * result + ( ( role == null ) ? 0 : role.hashCode() );
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
        if ( !( obj instanceof Dbuser ) )
        {
            return false;
        }
        Dbuser other = (Dbuser) obj;
        if ( fullName == null )
        {
            if ( other.fullName != null )
            {
                return false;
            }
        }
        else if ( !fullName.equals( other.fullName ) )
        {
            return false;
        }
        if ( id != other.id )
        {
            return false;
        }
        if ( login == null )
        {
            if ( other.login != null )
            {
                return false;
            }
        }
        else if ( !login.equals( other.login ) )
        {
            return false;
        }
        if ( password == null )
        {
            if ( other.password != null )
            {
                return false;
            }
        }
        else if ( !password.equals( other.password ) )
        {
            return false;
        }
        if ( role == null )
        {
            if ( other.role != null )
            {
                return false;
            }
        }
        else if ( !role.equals( other.role ) )
        {
            return false;
        }
        return true;
    }
}
