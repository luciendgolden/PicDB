package at.technikum.swei.button.mediator;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ConcreteMediator implements Mediator<ToggleButton> {

  private EXIFButton exifButton;
  private IPTCButton iptcButton;

  @Override
  public void registerComponent(Component component) {
    component.setMediator(this);
    switch (component.getName()) {
      case "EXIFButton":
        this.exifButton = (EXIFButton) component;
        this.exifButton.setOnAction(exifButton);
        break;
      case "IPTCButton":
        this.iptcButton = (IPTCButton) component;
        this.iptcButton.setOnAction(iptcButton);
        break;
    }
  }

  @Override
  public void call(ToggleButton button) {
    if (button instanceof EXIFButton) {
      toggleButton(exifButton,true);
      toggleButton(iptcButton, false);
    } else if(button instanceof IPTCButton){
      toggleButton(exifButton, false);
      toggleButton(iptcButton,true);
    }
  }

  private void toggleButton(ToggleButton button, boolean flag) {
    button.setSelected(flag);
  }
}
