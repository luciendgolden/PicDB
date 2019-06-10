package at.technikum.swei.dal;

import at.technikum.swei.domain.Photographer;
import at.technikum.swei.domain.Picture;
import java.util.List;

public interface DAO<K,E>{
  List<E> getEntities();

  void save(E entity);
  void update(E entity);
  void remove(E entity);
  E findById(K id);
}
