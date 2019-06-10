package at.technikum.swei.dal;

import at.technikum.swei.domain.Photographer;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;

public class JpaPhotographerDAO extends JpaDAO<Long, Photographer> {

  @Override
  public List<Photographer> getEntities() {
    Query query = entityManager.createQuery("Select p from Photographer p");
    return query.getResultList();
  }
}
