package ua.foodstore.managedbeans;

import ua.foodstore.entities.Dbuser;
import ua.foodstore.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "usersManager", eager = true)
@RequestScoped
public class UsersManagerBean
{
    @ManagedProperty( value = "#{userService}" )
    private UserService userService;
    @ManagedProperty( value = "#{rolesList}" )
    private RolesListBean rolesListHolder;
    private List<UserBean> curentUsers;
    
    public UsersManagerBean()
    {
    }

    public RolesListBean getRolesListHolder()
    {
        return rolesListHolder;
    }

    public void setRolesListHolder( RolesListBean rolesListHolder )
    {
        this.rolesListHolder = rolesListHolder;
    }
    

    public List<UserBean> getCurentUsers()
    {
        return curentUsers;
        //return userService.findAll();
    }

    public void setCurentUsers( List<UserBean> curentUsers )
    {
        this.curentUsers = curentUsers;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String addUser( UserBean newUser )
    {
        //curentUsers.add(newUser);
        userService.createUser(newUser.getLogin(), newUser.getPassword(), newUser.getFullName(), newUser.getRoleName());
        //init();
        return "secure?faces-redirect=true";
    }
    
    public String deleteUser( int userId )
    {
        //curentUsers.add(newUser);
        userService.deleteUser(userId);
        //init();
        return "secure?faces-redirect=true";
    }
    
    public void rowEditListener(RowEditEvent ev)
    {
        UserBean ub = (UserBean) ev.getObject();
        userService.saveUpdateUser(ub.getDbUser());
    }
    
    @PostConstruct
    public void init() {       
      
        List<Dbuser> dbusers = userService.findAll();
        curentUsers = new ArrayList<UserBean>();
        for (Dbuser dbuser : dbusers) {
            curentUsers.add( new UserBean( dbuser.getLogin(), 
                                           dbuser.getPassword(),
                                           dbuser.getFullName(),
                                           dbuser.getRole().getName(), dbuser ));
        }
    }
}
