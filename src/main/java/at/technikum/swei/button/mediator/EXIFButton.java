package at.technikum.swei.button.mediator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

public class EXIFButton extends ToggleButton implements EventHandler<ActionEvent>, Component {

  private Mediator<ToggleButton> mediator;

  public EXIFButton() {
    super("EXIF");
    setSelected(false);
  }

  @Override
  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    //handle EXIF
    //logic blabla
    //inform mediator
    this.mediator.call(this);
  }
}
