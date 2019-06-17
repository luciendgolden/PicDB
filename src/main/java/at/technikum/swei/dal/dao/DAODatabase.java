package at.technikum.swei.dal.dao;

import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class DAODatabase<K, E> implements DAO<K, E> {

  protected Class<E> entityClass;

  private EntityManagerFactory ENTITY_MANAGER_FACTORY;
  protected EntityManager entityManager;

  public DAODatabase() {
    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
    this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];

    ENTITY_MANAGER_FACTORY = Persistence
        .createEntityManagerFactory("picdb");
    entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
  }

  @Override
  public E findById(K id) {
    E entity = entityManager.find(entityClass, id);

    if (entity == null) {
      throw new EntityNotFoundException("Can't find Entity for ID "+id);
    }

    return entity;
  }

  @Override
  public void save(E entity) {
    executeInsideTransaction(em -> em.persist(entity));
  }

  @Override
  public void update(E entity) {
    executeInsideTransaction(em -> em.merge(entity));
  }

  @Override
  public void remove(E entity) {
    executeInsideTransaction(em -> em.remove(em.contains(entity) ? entity : em.merge(entity)));
  }

  private void executeInsideTransaction(Consumer<EntityManager> action) {
    EntityTransaction tx = entityManager.getTransaction();
    try {
      tx.begin();
      action.accept(entityManager);
      tx.commit();
    }
    catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
  }

  public void closeConnection() {
    entityManager.close();
  }
}
