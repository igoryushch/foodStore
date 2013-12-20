package ua.foodstore.dao;

import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import ua.foodstore.entities.Dbuser;
import ua.foodstore.entities.UserRole;

/** 
* DbuserDao implementation
*
* @author <a href="mailto:igor_aviant@ukr.net">Igor Yushchenko</a>
* @version $ $Date: September 11, 2013 $
*/

@Repository("dbuserDao")
public class DbuserDaoImpl extends GenericDaoImpl<Dbuser> implements DbuserDao{

    
    @Override
    public List<Dbuser> findAll() {
        return em.createNamedQuery("Dbuser.findAll", Dbuser.class).getResultList();
    }
    
    @Override
    public Dbuser loginUser(String login, String password) {       
        Dbuser resUser = em.createNamedQuery("Dbuser.findByLogin", Dbuser.class).setParameter("login", login).getSingleResult();
        
        for (Iterator it = resUser.getRole().getPagename().iterator(); it.hasNext();) {
            System.out.println(it.next().toString());           
        }
 
        if (resUser != null) {
            if (resUser.getPassword().equals(password)) {
                return resUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    } 
    
    @Override
    public UserRole findRoleByName(String name)
    {
        return em.createNamedQuery("UserRole.findByName",UserRole.class).setParameter("name", name).getSingleResult();
    }
    
}
