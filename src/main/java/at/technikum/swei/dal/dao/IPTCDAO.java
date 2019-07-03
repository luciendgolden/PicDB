package at.technikum.swei.dal.dao;

import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.IPTC;
import at.technikum.swei.domain.Picture;
import java.util.List;
import javax.persistence.Query;

public class IPTCDAO extends DAODatabase<Long, IPTC>{
  @Override
  public List<IPTC> getEntities() {
    Query query = entityManager.createQuery("Select i from IPTC i");
    return query.getResultList();
  }

  public IPTC findByPicId(final Picture picId) {
    //Query query = entityManager.createQuery("SELECT p FROM Picture p WHERE p.name='"+search+"'");

    Query query = entityManager.createQuery("SELECT p FROM IPTC p WHERE p.picture=:param1");
    query.setParameter("param1", picId);

    return (IPTC) query.getSingleResult();
  }
}
