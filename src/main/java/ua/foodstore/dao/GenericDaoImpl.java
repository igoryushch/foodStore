package ua.foodstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;
    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(final T t) {
        em.persist(t);
        return t;
    }

    @Override
    public void delete(final Object id) {
        em.remove(em.getReference(type, id));
    }

    @Override
    public T read(final Object id) {
        return (T) em.find(type, id);
    }

    @Override
    public T update(final T t) {
        return this.em.merge(t);
    }
}
