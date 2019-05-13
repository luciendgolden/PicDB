package at.technikum.swei.button.mediator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

public class IPTCButton extends ToggleButton implements EventHandler<ActionEvent>, Component {

  private Mediator<ToggleButton> mediator;

  public IPTCButton() {
    super("IPTC");
    setSelected(true);
  }

  @Override
  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    //handle IPTC Button
    //blabla logic
    //inform mediator of click!
    this.mediator.call(this);
  }
}
