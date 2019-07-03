package at.technikum.swei.controller;

import at.technikum.swei.business.BusinessLayer;
import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.IPTC;
import at.technikum.swei.domain.Picture;
import at.technikum.swei.mock.MockEXIF;
import at.technikum.swei.mock.MockIPTC;
import at.technikum.swei.presentationmodel.PicturePresentationModel;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

public class PictureListController {

  @FXML
  private ListView listView;

  private BusinessLayer businessLayer;

  private ObservableList<Path> imageFiles;

  private PictureShowController pictureShowController;
  private Controller mainController;

  private static final Logger logger = Logger.getLogger(PictureListController.class);

  // File representing the folder that you select using a FileChooser
  private static final File dir = new File(PictureListController.class
      .getClassLoader()
      .getResource("images" + File.separator)
      .getPath());

  // array of supported extensions (use a List if you prefer)
  private static final String[] EXTENSIONS = new String[]{"png", "jpg", "jpeg"};

  // filter to identify images based on their extensions
  private static final FilenameFilter IMAGE_FILTER = PictureListController::accept;

  private static boolean accept(File dir, String name) {
    for (final String ext : EXTENSIONS) {
      if (name.endsWith("." + ext)) {
        return true;
      }
    }
    return false;
  }

  private List<Path> getAllPathsFromImages() {
    List<Path> pathList = new ArrayList<>();

    if (dir.isDirectory()) { // make sure it's a directory
      for (final File f : dir.listFiles(IMAGE_FILTER)) {
        logger.debug("image: " + f.getName());
        pathList.add(f.toPath());
      }

    }

    return pathList;
  }

  @FXML
  public void initialize() {
    this.businessLayer = new BusinessLayer();
    this.imageFiles = FXCollections.observableArrayList();
    this.pictureShowController = Context.getInstance().getPictureShowController();

    imageFiles.setAll(getAllPathsFromImages());


    listView.setItems(imageFiles);
    listView.setCellFactory(this::call);

    listView.getItems().forEach(e -> {
      Path path = (Path) e;
      String[] str = path.toUri().toString().split(File.separator);
      String fullname = str[str.length-1];
      String name = fullname.split("\\.")[0];

      Picture picture = giveMeDaPic(name);
      EXIF exif = mockMeSomeExif(picture);
      IPTC iptc = mockMeSomeIptc(picture);

      businessLayer.savePicture(picture);
      businessLayer.saveEXIF(exif);
      businessLayer.saveIPTC(iptc);


    });

    listView.getSelectionModel().selectedItemProperty().addListener(
        (ChangeListener<Path>) (observable, oldValue, newValue) -> {
          pictureShowController.setImage(newValue);
          this.mainController = Context.getInstance().getMainController();
          mainController.loadDaStuff(newValue);
        });
  }

  private IPTC mockMeSomeIptc(Picture picture) {
    IPTC myIptc = MockIPTC.create();
    myIptc.setPicture(picture);
    return myIptc;
  }

  private EXIF mockMeSomeExif(Picture picture) {
  EXIF myExif = MockEXIF.create();
  myExif.setPicture(picture);
  return myExif;
  }

  private Picture giveMeDaPic(String name) {
    Picture picture = new Picture();
    picture.setName(name);

    return picture;
  }

  private Object call(Object listView) {
    return new ListCell<Path>() {
      private final ImageView imageView = new ImageView();

      @Override
      public void updateItem(Path path, boolean empty) {
        super.updateItem(path, empty);
        if (empty) {
          setText(null);
          setGraphic(null);
        } else {
          imageView.setImage(new Image(path.toUri().toString(), 100, 100, true, true, true));
          setGraphic(imageView);
        }
      }
    };
  }
}
