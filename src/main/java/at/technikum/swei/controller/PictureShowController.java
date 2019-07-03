package at.technikum.swei.controller;

import java.nio.file.Path;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PictureShowController {

  @FXML
  private ImageView imageView;

  private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

  public void setImage(Path path) {
    imageView.setImage(new Image(path.toUri().toString(), 250, 300, true, true, true));
  }

  @FXML
  public void initialize() {
    Context.getInstance().setPictureShowController(this);
  }
}
