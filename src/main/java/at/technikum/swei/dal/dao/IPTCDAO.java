package at.technikum.swei.dal.dao;

import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.IPTC;
import java.util.List;
import javax.persistence.Query;

public class IPTCDAO extends DAODatabase<Long, IPTC>{
  @Override
  public List<IPTC> getEntities() {
    Query query = entityManager.createQuery("Select i from IPTC i");
    return query.getResultList();
  }
}
