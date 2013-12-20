/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.managedbeans;

//import java.util.ArrayList;
import ua.foodstore.entities.UserRole;
import ua.foodstore.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "rolesList", eager = true)
@RequestScoped
public class RolesListBean
{
    @ManagedProperty( value = "#{userService}" )
    private UserService userService; 
    private List<UserRole> rolesList;
    
    public RolesListBean()
    {}

    public List<UserRole> getRolesList()
    {
        return rolesList;
    }

    public void setRolesList( List<UserRole> rolesList )
    {
        this.rolesList = rolesList;
    }

    public UserService getUserService()
    {
        return userService;
    }

    public void setUserService( UserService userService )
    {
        this.userService = userService;
    }

    public UserRole returnRoleByName(String roleName)
    {
        for ( UserRole role : rolesList)
        {
            if ( role.getName().equals( roleName) )
            {
                return role;
            }
        }
        
        return null;
    }
    
    public UserRole returnRoleById(int roleId)
    {
        for ( UserRole role : rolesList)
        {
            if ( role.getId() == roleId )
            {
                return role;
            }
        }
        
        return null;
    }
    
    @PostConstruct
    public void init() {
        rolesList = userService.getRoles();
    }
}
