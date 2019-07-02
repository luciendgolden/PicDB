package at.technikum.swei.dal.factory;

import at.technikum.swei.dal.dao.DAO;
import at.technikum.swei.dal.dao.DAODatabase;
import at.technikum.swei.dal.dao.DAOMock;
import at.technikum.swei.dal.dao.EXIFDAO;
import at.technikum.swei.dal.dao.IPTCDAO;
import at.technikum.swei.dal.dao.PhotographerDAO;
import at.technikum.swei.dal.dao.PictureDAO;
import at.technikum.swei.domain.EXIF;
import java.util.Optional;

public class DALFactory {

  private final static PhotographerDAO photographerDAO = new PhotographerDAO();
  private final static PictureDAO pictureDAO = new PictureDAO();
  private final static EXIFDAO EXIF_DAO = new EXIFDAO();
  private final static IPTCDAO IPTC_DAO = new IPTCDAO();

  public static <T extends DAO> DAO getDAL(Class<T> clazz) {
    if (clazz != null) {
      if (clazz.equals(PhotographerDAO.class)) {
        return photographerDAO;
      } else if (clazz.equals(PictureDAO.class)) {
        return pictureDAO;
      } else if (clazz.equals(EXIFDAO.class)) {
        return EXIF_DAO;
      } else if (clazz.equals(IPTCDAO.class)) {
        return IPTC_DAO;
      }
    } else {
      return new DAOMock();
    }

    throw new IllegalArgumentException("Can't return any DAO");
  }

}
