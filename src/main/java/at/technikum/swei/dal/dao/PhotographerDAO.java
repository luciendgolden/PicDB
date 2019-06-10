package at.technikum.swei.dal.dao;

import at.technikum.swei.domain.Photographer;
import java.util.List;
import javax.persistence.Query;

public class PhotographerDAO extends DAODatabase<Long, Photographer> {

  @Override
  public List<Photographer> getEntities() {
    Query query = entityManager.createQuery("Select p from Photographer p");
    return query.getResultList();
  }
}
