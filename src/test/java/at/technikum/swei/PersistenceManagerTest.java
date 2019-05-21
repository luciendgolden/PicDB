package at.technikum.swei;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.IPTC;
import at.technikum.swei.domain.Photographer;
import at.technikum.swei.domain.Picture;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersistenceManagerTest {

  private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
  private EntityManager entityManager;

  @BeforeClass
  public static void init() {
    ENTITY_MANAGER_FACTORY = Persistence
        .createEntityManagerFactory("picdb");
  }

  @Before
  public void before(){
    entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
  }

  @After
  public void after(){
    entityManager.close();
  }

  @AfterClass
  public static void close() {
    ENTITY_MANAGER_FACTORY.close();
  }

  @Test
  public void test_create_persistence_photographer() {
    LocalDate date = LocalDate.of(2000, 7, 7);
    Photographer photographer = new Photographer();
    photographer.setFirstName("Alex");
    photographer.setLastName("da Boy");
    photographer.setBirthDate(date);

    entityManager.getTransaction().begin();
    entityManager.persist(photographer);
    entityManager.getTransaction().commit();

    Query query = entityManager.createQuery("Select p from Photographer p");
    List<Photographer> list = query.getResultList();

    list.forEach(System.out::println);
  }

  @Test
  public void test_create_persistence_picture_photographer() {
    Photographer p = entityManager.find(Photographer.class, 1L);

    Picture pic = new Picture();
    pic.setName("TEST-PICTURE1");

    p.addPicture(pic);

    entityManager.getTransaction().begin();
    entityManager.persist(pic);
    entityManager.getTransaction().commit();

    Query querypic = entityManager.createQuery("Select p from Picture p");
    List<Picture> listpic = querypic.getResultList();
    Query queryPhotographer = entityManager.createQuery("Select p from Photographer p");
    List<Photographer> listphotographer = queryPhotographer.getResultList();

    listpic.forEach(System.out::println);
    listphotographer.forEach(System.out::println);
  }

  @Test
  public void bidirectional_one_to_one_picture_exif_iptc_relation(){
    Photographer p = entityManager.find(Photographer.class, 1L);
    Picture picture = new Picture();
    picture.setName("Pexel-93849");
    EXIF exif = new EXIF();
    IPTC iptc = new IPTC();

    p.addPicture(picture);
    picture.addExif(exif);
    picture.addIptc(iptc);

    entityManager.getTransaction().begin();
    entityManager.persist(picture);
    entityManager.getTransaction().commit();

  }
}