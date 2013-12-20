/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean(name = "mainMenu", eager = true)
@SessionScoped
public class MainMenuBean
{
    ArrayList<MainMenuItemBean> mainmenu = new ArrayList<MainMenuItemBean>();
    
    public MainMenuBean()
    {
        
    }

    public MainMenuBean( ArrayList<MainMenuItemBean> mainmenu )
    {
        this.mainmenu = mainmenu;
    }

    public ArrayList<MainMenuItemBean> getMainmenu()
    {
        return mainmenu;
    }

    public void setMainmenu( ArrayList<MainMenuItemBean> mainmenu )
    {
        this.mainmenu = mainmenu;
    }
    
    @PostConstruct
    public void init() {
        mainmenu.add( new MainMenuItemBean("HOME","base.xhtml"));
        mainmenu.add( new MainMenuItemBean("NEW PRODUCTS","base.xhtml"));
        mainmenu.add( new MainMenuItemBean("SPECIALS","base.xhtml"));
        mainmenu.add( new MainMenuItemBean("CONTACTS","base.xhtml"));
    }
    
}
