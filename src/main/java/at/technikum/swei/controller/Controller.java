package at.technikum.swei.controller;

import static at.technikum.swei.Main.openPopup;

import at.technikum.swei.business.BusinessLayer;
import at.technikum.swei.button.mediator.Component;
import at.technikum.swei.button.mediator.ConcreteMediator;
import at.technikum.swei.button.mediator.Mediator;
import at.technikum.swei.domain.Picture;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;

public class Controller {

  @FXML
  private ToggleButton iptcToggleButton;

  private BusinessLayer businessLayer;

  @FXML
  private ToggleButton exifToggleButton;

  @FXML
  private MenuItem photographerButton;

  @FXML
  private void handlePhotographer(ActionEvent event) throws IOException {
    openPopup("photographerView");
  }

  @FXML
  void initialize() {
    Context.getInstance().setMainController(this);

    businessLayer = new BusinessLayer();
    Mediator<ToggleButton> buttonMediator = new ConcreteMediator();
    buttonMediator.registerComponent((Component) iptcToggleButton);
    buttonMediator.registerComponent((Component) exifToggleButton);
  }

  public void loadDaStuff(Path path) {
    String[] str = path.toUri().toString().split(File.separator);
    String fullname = str[str.length - 1];
    String name = fullname.split("\\.")[0];

    Picture picture = businessLayer.getPictureByName(name);
    Long picId = picture.getId();

    System.out.println(picId);
  }
}
