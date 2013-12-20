/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import ua.foodstore.entities.Dbuser;
import ua.foodstore.entities.UserRole;

public interface DbuserDao extends GenericDao<Dbuser>{
    
    public List<Dbuser> findAll();
    public Dbuser loginUser(String login, String password);
    public UserRole findRoleByName(String name);
}
