package at.technikum.swei.controller;

import static at.technikum.swei.Main.openPopup;

import at.technikum.swei.button.mediator.Component;
import at.technikum.swei.button.mediator.ConcreteMediator;
import at.technikum.swei.button.mediator.Mediator;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;

public class Controller {
  @FXML
  private ToggleButton iptcToggleButton;

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
    Mediator<ToggleButton> buttonMediator = new ConcreteMediator();
    buttonMediator.registerComponent((Component) iptcToggleButton);
    buttonMediator.registerComponent((Component) exifToggleButton);
  }
}
