/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

/**
 *
 * @author Cold Warrioir
 */
public interface GenericDao <T>{
   
    T create(T t);

    void delete(Object id);

    T read(Object id);

    T update(T t);

}
