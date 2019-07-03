package at.technikum.swei.dal.dao;

import at.technikum.swei.Main;
import at.technikum.swei.domain.Picture;
import java.util.List;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class PictureDAO extends DAODatabase<Long, Picture> {

  private static final Logger logger = Logger.getLogger(PictureDAO.class);

  @Override
  public List<Picture> getEntities() {
    Query query = entityManager.createQuery("SELECT p FROM Picture p");
    return query.getResultList();
  }

  public Picture getPictureByName(final String search) {
    //Query query = entityManager.createQuery("SELECT p FROM Picture p WHERE p.name='"+search+"'");

    Query query = entityManager.createQuery("SELECT p FROM Picture p WHERE p.name=:param1");
    query.setParameter("param1", search);

    return (Picture) query.getResultList().get(0);
  }
}
