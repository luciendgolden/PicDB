package at.technikum.swei.dal.dao;

import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.Photographer;
import at.technikum.swei.domain.Picture;
import java.util.List;
import javax.persistence.Query;

public class EXIFDAO extends DAODatabase<Long, EXIF>{
  @Override
  public List<EXIF> getEntities() {
    Query query = entityManager.createQuery("Select e from EXIF e");
    return query.getResultList();
  }
}
