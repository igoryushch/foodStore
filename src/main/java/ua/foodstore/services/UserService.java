
package ua.foodstore.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import ua.foodstore.entities.Dbuser;
import ua.foodstore.entities.UserRole;

public interface UserService
{
    @Transactional
    void createUser(String login, String pswd, String fullName, String roleName);
   
    @Transactional
    void saveUpdateUser(Dbuser dbUser);
    
    @Transactional(readOnly = true)
    Dbuser loginUser(String login, String password);
    
    @Transactional(readOnly = true)
    public List<Dbuser> findAll();
    
    @Transactional(readOnly = true)
    public List<UserRole> getRoles();
    
    @Transactional
    public void deleteUser(int dbuserId);
       
}
