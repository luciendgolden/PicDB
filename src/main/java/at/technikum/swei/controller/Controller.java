package at.technikum.swei.controller;

import at.technikum.swei.button.mediator.Component;
import at.technikum.swei.button.mediator.ConcreteMediator;
import at.technikum.swei.button.mediator.EXIFButton;
import at.technikum.swei.button.mediator.IPTCButton;
import at.technikum.swei.button.mediator.Mediator;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class Controller {
  @FXML
  private ToggleButton iptcToggleButton;

  @FXML
  private ToggleButton exifToggleButton;

  @FXML
  void initialize() {
    Mediator<ToggleButton> buttonMediator = new ConcreteMediator();
    buttonMediator.registerComponent((Component) iptcToggleButton);
    buttonMediator.registerComponent((Component) exifToggleButton);
  }

  public void handle(){

  }
}
