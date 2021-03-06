package railroad.DAO;

import railroad.Exceptions.UserAlreadyExistsException;
import railroad.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class GenericDAOImpl<T extends BaseEntity<U>, U> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        this.entityClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments())[0];
    }

    public void save(T entity) {
        try {
            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
        } catch (PersistenceException e) {
            throw new UserAlreadyExistsException();
        }
    }

    public List<T> list() {
        return entityManager.createQuery("from " + entityClass.getSimpleName() + " u", entityClass).getResultList();
    }

    public T find(U id) {
        return entityManager.find(entityClass, id);
    }

    public void removeModel(T entity) {
        entityManager.remove(entity);
    }

    public void remove(U id) {
        T entity = find(id);
        removeModel(entity);
    }

    protected void executeNamedQuery(String name, Map<String, Object> params) {
        Query query = entityManager.createNamedQuery(name);
        params.forEach(query::setParameter);
        query.executeUpdate();
        entityManager.flush();
        entityManager.clear();
    }

    protected List<T> executeLoginQuery(String name, Map<String, Object> params) {
        Query query = entityManager.createNamedQuery(name);
        params.forEach(query::setParameter);
        List<T> users = query.getResultList();
        entityManager.flush();
        entityManager.clear();
        return users;
    }
}
