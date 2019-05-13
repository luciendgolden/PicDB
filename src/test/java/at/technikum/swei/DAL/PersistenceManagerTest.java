package at.technikum.swei.DAL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import at.technikum.swei.domain.Photographer;
import at.technikum.swei.domain.Student;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersistenceManagerTest {

  private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

  @BeforeClass
  public static void init(){
    ENTITY_MANAGER_FACTORY = Persistence
        .createEntityManagerFactory("picdb");
  }

  @AfterClass
  public static void close(){
    ENTITY_MANAGER_FACTORY.close();
  }

  @Test
  public void test_create_persistence_photographer(){
    LocalDate date = LocalDate.of(2000, 7, 7);
    Photographer photographer = new Photographer();
    photographer.setFirstName("Alex");
    photographer.setLastName("da Boy");
    photographer.setBirthDate(date);

    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    em.getTransaction().begin();
    em.persist(photographer);
    em.getTransaction().commit();

    em.close();



  }
}