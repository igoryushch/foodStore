/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.managedbeans;

import javax.faces.application.Application;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import ua.foodstore.entities.Dbuser;
import ua.foodstore.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@SessionScoped
public class UserHolder {
  
    @ManagedProperty( value = "#{userService}")
    private UserService userService;
    private UserBean currentUser;
    
    public UserHolder() {
    }

    public UserBean getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserBean currentUser) {
        this.currentUser = currentUser;
    }

    public UserService getUserService()
    {
        return userService;
    }

    public void setUserService( UserService userService )
    {
        this.userService = userService;
    }

    public String logUser( UserBean user)
    {
        Dbuser loggedUser = userService.loginUser(user.getLogin(), user.getPassword());
        if (loggedUser != null) {
            currentUser = user;
            user.setFullName(loggedUser.getFullName());
            user.setRoleName(loggedUser.getRole().getName());
            user.setDbUser(loggedUser);
            if (loggedUser.getRole().getName().equals("Restaurant Administrator")) {
                return "menuadmin?faces-redirect=true";
            } else if (loggedUser.getRole().getName().equals("Kitchen Stuff")) {
                return "kitchen?faces-redirect=true";
            } else if (loggedUser.getRole().getName().equals("Business analytic")) {
                return "reports?faces-redirect=true";
            } else if (loggedUser.getRole().getName().equals("Security officer")) {
                return "secure?faces-redirect=true";
            }
        }
        return "login";
    }
    
    public void checkLogin(ComponentSystemEvent event)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        if (currentUser == null) {
            
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
            handler.performNavigation("login?faces-redirect=true");
        }else{
            String viewId = context.getViewRoot().getViewId();
            Application app = context.getApplication();
            if (!currentUser.getDbUser().getRole().getPagename().contains(viewId)) {
                ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Access denied!" ,"You need to be authorized!");
                context.addMessage("messages", message);
                handler.performNavigation("login?faces-redirect=true");
            }
//            System.out.println(currentUser.getRoleName());
//            System.out.println(viewId);   //  /menuadmin.xhtml
        }
    }
}
