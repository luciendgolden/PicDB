package at.technikum.swei;

import at.technikum.swei.configuration.Configuration;
import at.technikum.swei.dal.JpaPhotographerDAO;
import at.technikum.swei.domain.Photographer;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;

public class DALManagerTest {

  private Configuration configuration = Configuration.getINSTANCE();
  private static final Logger logger = Logger.getLogger(PersistenceManagerTest.class);

  @Test
  public void getAll_Photographers_through_DAL_from_DB() {
    JpaPhotographerDAO jpaPhotographerDAO = new JpaPhotographerDAO();
    List<Photographer> photographers = jpaPhotographerDAO.getEntities();
    for (Photographer alex : photographers) {
      logger.info(alex);
    }

    jpaPhotographerDAO.closeConnection();
  }

  @Test
  public void save_Photographers_through_DAL_from_DB() {
    JpaPhotographerDAO jpaPhotographerDAO = new JpaPhotographerDAO();

    Photographer photographer = new Photographer();
    photographer.setFirstName("Alexander");
    photographer.setLastName("Tampier");
    photographer.setBirthDate(LocalDate.of(1993,7,7));
    photographer.setNotes("Errare humanum est");

    jpaPhotographerDAO.save(photographer);

    jpaPhotographerDAO.closeConnection();
  }

}
