package at.technikum.swei.dal;

import at.technikum.swei.domain.Picture;
import java.util.List;
import javax.persistence.Query;

public class JpaPictureDAO extends JpaDAO<Long, Picture> {

  @Override
  public List<Picture> getEntities() {
    Query query = entityManager.createQuery("SELECT p FROM Picture p");
    return query.getResultList();
  }
}
