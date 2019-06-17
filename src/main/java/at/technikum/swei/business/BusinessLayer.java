package at.technikum.swei.business;

import at.technikum.swei.dal.dao.PhotographerDAO;
import at.technikum.swei.dal.dao.PictureDAO;
import at.technikum.swei.dal.factory.DALFactory;
import at.technikum.swei.domain.Photographer;
import at.technikum.swei.domain.Picture;
import java.util.List;

public class BusinessLayer {

  public BusinessLayer() {
  }

  /*
  * PICTURE STUFF
  * */
  public Picture getPicture(long id) {
    return (Picture) DALFactory.getDAL(PictureDAO.class).findById(id);
  }

  public List<Picture> getAllPictures() {
    return (List<Picture>) DALFactory.getDAL(PictureDAO.class).getEntities();
  }

  public void deletePicture(Picture picture) {
    DALFactory.getDAL(PictureDAO.class).remove(picture);
  }

  public void updatePicture(Picture picture) {
    DALFactory.getDAL(PictureDAO.class).update(picture);
  }

  public void savePicture(Picture picture) {
    DALFactory.getDAL(PictureDAO.class).save(picture);
  }

  /*
  * PHOTOGRAPHER STUFF
  * */
  public Photographer getPhotographer(long id) {
    return (Photographer) DALFactory.getDAL(PhotographerDAO.class).findById(id);
  }

  public List<Photographer> getAllPhotographers() {
    return (List<Photographer>) DALFactory.getDAL(PhotographerDAO.class).getEntities();
  }

  public void deletePhotographer(Photographer photographer) {
    DALFactory.getDAL(PhotographerDAO.class).remove(photographer);
  }

  public void updatePhotographer(Photographer photographer) {
    DALFactory.getDAL(PhotographerDAO.class).update(photographer);
  }

  public void savePhotographer(Photographer photographer) {
    DALFactory.getDAL(PhotographerDAO.class).save(photographer);
  }

}
