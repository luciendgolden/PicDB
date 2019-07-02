package at.technikum.swei.business;

import at.technikum.swei.dal.dao.EXIFDAO;
import at.technikum.swei.dal.dao.IPTCDAO;
import at.technikum.swei.dal.dao.PhotographerDAO;
import at.technikum.swei.dal.dao.PictureDAO;
import at.technikum.swei.dal.factory.DALFactory;
import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.IPTC;
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

  public Picture getPictureByName(String name) {
    return ((PictureDAO) DALFactory.getDAL(PictureDAO.class)).getPictureByName(name);
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
  * EXIF
  * */

  public EXIF getEXIF(long id) {
    return (EXIF) DALFactory.getDAL(EXIFDAO.class).findById(id);
  }

  public List<EXIF> getAllEXIFs() {
    return (List<EXIF>) DALFactory.getDAL(EXIFDAO.class).getEntities();
  }

  public void deleteEXIF(EXIF exif) {
    DALFactory.getDAL(EXIFDAO.class).remove(exif);
  }

  public void updateEXIF(EXIF exif) {
    DALFactory.getDAL(EXIFDAO.class).update(exif);
  }

  public void saveEXIF(EXIF exif) {
    DALFactory.getDAL(EXIFDAO.class).save(exif);
  }

  /**
   * IPTC
   */
  public IPTC getIPTC(long id) {
    return (IPTC) DALFactory.getDAL(IPTCDAO.class).findById(id);
  }

  public List<IPTC> getAllIPTCs() {
    return (List<IPTC>) DALFactory.getDAL(IPTCDAO.class).getEntities();
  }

  public void deleteIPTC(IPTC iptc) {
    DALFactory.getDAL(IPTCDAO.class).remove(iptc);
  }

  public void updateIPTC(IPTC iptc) {
    DALFactory.getDAL(IPTCDAO.class).update(iptc);
  }

  public void saveIPTC(IPTC iptc) {
    DALFactory.getDAL(IPTCDAO.class).save(iptc);
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
