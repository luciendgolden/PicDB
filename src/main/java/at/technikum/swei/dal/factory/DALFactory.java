package at.technikum.swei.dal.factory;

import at.technikum.swei.dal.dao.DAO;
import at.technikum.swei.dal.dao.DAODatabase;
import at.technikum.swei.dal.dao.DAOMock;
import at.technikum.swei.dal.dao.PhotographerDAO;
import at.technikum.swei.dal.dao.PictureDAO;
import java.util.Optional;

public class DALFactory {

  private final static PhotographerDAO photographerDAO = new PhotographerDAO();
  private final static PictureDAO pictureDAO = new PictureDAO();

  public static <T extends DAO> DAO getDAL(Class<T> clazz) {
    if(clazz!=null){
    if(clazz.equals(PhotographerDAO.class)){
      return photographerDAO;
    }else if(clazz.equals(PictureDAO.class)) {
      return pictureDAO;
    }
    }else{
      return new DAOMock();
    }

    throw new IllegalArgumentException("Can't return any DAO");
  }

}
