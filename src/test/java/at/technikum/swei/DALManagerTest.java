package at.technikum.swei;

import at.technikum.swei.configuration.Configuration;
import at.technikum.swei.dal.dao.PhotographerDAO;
import at.technikum.swei.dal.dao.PictureDAO;
import at.technikum.swei.dal.factory.DALFactory;
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
    PhotographerDAO jpaPhotographerDAO = new PhotographerDAO();
    List<Photographer> photographers = jpaPhotographerDAO.getEntities();
    for (Photographer alex : photographers) {
      logger.info(alex);
    }

    jpaPhotographerDAO.closeConnection();
  }

  @Test
  public void find_by_id_photographer_through_DAL_from_DB() {
    PhotographerDAO jpaPhotographerDAO = new PhotographerDAO();

    Photographer photographer = jpaPhotographerDAO.findById(1L);

    logger.info(photographer);

    jpaPhotographerDAO.closeConnection();
  }

  @Test
  public void save_Photographers_through_DAL_from_DB() {
    PhotographerDAO jpaPhotographerDAO = new PhotographerDAO();

    Photographer photographer = new Photographer();
    photographer.setFirstName("Alexander");
    photographer.setLastName("Tampier");
    photographer.setBirthDate(LocalDate.of(1993,7,7));
    photographer.setNotes("Errare humanum est");

    jpaPhotographerDAO.save(photographer);

    jpaPhotographerDAO.closeConnection();
  }

  @Test
  public void update_Photographers_through_DAL_from_DB() {
    PhotographerDAO jpaPhotographerDAO = new PhotographerDAO();

    Photographer photographer = jpaPhotographerDAO.findById(1L);
    photographer.setFirstName("Benjamin");
    photographer.setLastName("Czihak");
    photographer.setBirthDate(LocalDate.of(1993,02,05));
    photographer.setNotes("Veni vidi vici");

    jpaPhotographerDAO.update(photographer);

    jpaPhotographerDAO.closeConnection();
  }

  @Test
  public void remove_Photographers_through_DAL_from_DB() {
    PhotographerDAO jpaPhotographerDAO = new PhotographerDAO();

    Photographer photographer = jpaPhotographerDAO.findById(2L);
    jpaPhotographerDAO.remove(photographer);

    jpaPhotographerDAO.closeConnection();
  }

  @Test
  public void myOptional(){
    DALFactory.getDAL(PictureDAO.class);
    DALFactory.getDAL(null);

  }
}
