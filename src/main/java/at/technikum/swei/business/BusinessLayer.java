package at.technikum.swei.business;

import at.technikum.swei.dal.dao.PhotographerDAO;
import at.technikum.swei.dal.dao.PictureDAO;
import at.technikum.swei.dal.factory.DALFactory;
import java.util.List;

public class BusinessLayer {

  public BusinessLayer() {
  }

  /*
  * PICTURE STUFF
  * */
  public PictureDAO getPicture(long id) {
    return (PictureDAO) DALFactory.getDAL(PictureDAO.class).findById(id);
  }

  public List<PictureDAO> getAllPictures() {
    return (List<PictureDAO>) DALFactory.getDAL(PictureDAO.class).getEntities();
  }

  public void deletePicture(PictureDAO pictureDAO) {
    DALFactory.getDAL(PictureDAO.class).remove(pictureDAO);
  }

  public void updatePicture(PictureDAO pictureDAO) {
    DALFactory.getDAL(PictureDAO.class).update(pictureDAO);
  }

  public void savePicture(PictureDAO pictureDAO) {
    DALFactory.getDAL(PictureDAO.class).save(pictureDAO);
  }

  /*
  * PHOTOGRAPHER STUFF
  * */
  public PhotographerDAO getPhotographer(long id) {
    return (PhotographerDAO) DALFactory.getDAL(PhotographerDAO.class).findById(id);
  }

  public List<PhotographerDAO> getAllPhotographers() {
    return (List<PhotographerDAO>) DALFactory.getDAL(PhotographerDAO.class).getEntities();
  }

  public void deletePhotographer(PhotographerDAO photographerDAO) {
    DALFactory.getDAL(PhotographerDAO.class).remove(photographerDAO);
  }

  public void updatePhotographer(PhotographerDAO photographerDAO) {
    DALFactory.getDAL(PhotographerDAO.class).update(photographerDAO);
  }

  public void savePhotographer(PhotographerDAO photographerDAO) {
    DALFactory.getDAL(PhotographerDAO.class).save(photographerDAO);
  }

}
