package at.technikum.swei.button.mediator;

import at.technikum.swei.controller.Context;
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
    Context.getInstance().getMainController().setExifClicked(true);
    Context.getInstance().getMainController().setIptcClicked(false);
    Context.getInstance().getMainController().exifLoad(actionEvent);
    this.mediator.call(this);
  }
}
