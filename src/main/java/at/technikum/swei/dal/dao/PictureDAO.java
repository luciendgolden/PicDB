package at.technikum.swei.dal.dao;

import at.technikum.swei.domain.Picture;
import java.util.List;
import javax.persistence.Query;

public class PictureDAO extends DAODatabase<Long, Picture> {

  @Override
  public List<Picture> getEntities() {
    Query query = entityManager.createQuery("SELECT p FROM Picture p");
    return query.getResultList();
  }
}
