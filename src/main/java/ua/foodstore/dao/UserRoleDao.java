/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import ua.foodstore.entities.UserRole;

public interface UserRoleDao extends GenericDao<UserRole>{
    public List<UserRole> findAll();
}
