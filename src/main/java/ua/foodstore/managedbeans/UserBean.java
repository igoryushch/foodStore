package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ua.foodstore.entities.Dbuser;

@ManagedBean(name = "userBean", eager = true)
@RequestScoped
public class UserBean
{
    private String login;
    private String password;
    private String roleName;
    private String fullName;
    private Dbuser dbUser;
        
    public UserBean()
    {
        
    }

    public UserBean( String login, String password, String roleName, String fullName, Dbuser dbUser)
    {
        this.login = login;
        this.password = password;
        this.roleName = roleName;
        this.fullName = fullName;
        this.dbUser   = dbUser;
    }
    
    public String getLogin()
    {
        return login;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Dbuser getDbUser() {
        return dbUser;
    }

    public void setDbUser(Dbuser dbUser) {
        this.dbUser = dbUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
