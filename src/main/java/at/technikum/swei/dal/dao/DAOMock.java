package at.technikum.swei.dal.dao;

import java.util.List;

public class DAOMock<K,E> implements DAO<K,E>{

  @Override
  public List<E> getEntities() {
    return null;
  }

  @Override
  public void save(E entity) {

  }

  @Override
  public void update(E entity) {

  }

  @Override
  public void remove(E entity) {

  }

  @Override
  public E findById(K id) {
    return null;
  }
}
