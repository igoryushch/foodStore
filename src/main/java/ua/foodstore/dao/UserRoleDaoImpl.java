/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import ua.foodstore.entities.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao{

    @Override
    public List<UserRole> findAll() {
        return em.createNamedQuery("UserRole.findAll",UserRole.class).getResultList();
    }
    
}
