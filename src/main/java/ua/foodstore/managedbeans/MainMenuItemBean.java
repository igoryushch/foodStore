/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "mainMenuItem", eager = true)
@SessionScoped
public class MainMenuItemBean
{
    private String name;
    private String href;

    public MainMenuItemBean( String name, String href )
    {
        this.name = name;
        this.href = href;
    }
       
    public MainMenuItemBean()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref( String href )
    {
        this.href = href;
    }
}
