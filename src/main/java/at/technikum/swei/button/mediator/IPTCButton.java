package at.technikum.swei.button.mediator;

import at.technikum.swei.controller.Context;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

public class IPTCButton extends ToggleButton implements EventHandler<ActionEvent>, Component {

  private Mediator<ToggleButton> mediator;

  private boolean isSelected = true;

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
    Context.getInstance().getMainController().setExifClicked(false);
    Context.getInstance().getMainController().setIptcClicked(true);
    Context.getInstance().getMainController().iptcLoad(actionEvent);
    this.mediator.call(this);
  }
}
