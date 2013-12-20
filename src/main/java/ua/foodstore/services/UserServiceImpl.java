/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.foodstore.dao.DbuserDao;
import ua.foodstore.dao.UserRoleDao;
import ua.foodstore.entities.Dbuser;
import ua.foodstore.entities.UserRole;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private DbuserDao dao;
   
    @Autowired
    private UserRoleDao roleDao;

    @Transactional
    @Override
    public void createUser(String login, String pswd, String fullName, String roleName) {
        Dbuser u = new Dbuser();
        u.setLogin(login);
        u.setPassword(pswd);
        u.setFullName(fullName);
        u.setRole(dao.findRoleByName(roleName));
        dao.create(u);
    }
    
    @Transactional
    @Override
    public void saveUpdateUser(Dbuser dbUser) {
        dao.update(dbUser);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Dbuser loginUser(String login, String password)
    {
        return dao.loginUser(login, password);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Dbuser> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserRole> getRoles() {
        return roleDao.findAll();
    }
    
    @Transactional
    @Override
    public void deleteUser(int dbuserId)
    {
        dao.delete(dbuserId);
    }        
    
}
