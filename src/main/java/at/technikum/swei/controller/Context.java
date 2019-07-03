package at.technikum.swei.controller;

import at.technikum.swei.button.mediator.EXIFButton;

public class Context {

  private final static Context context = new Context();

  private PictureShowController pictureShowController;

  private Controller mainController;

  public static Context getInstance() {
    return context;
  }

  public PictureShowController getPictureShowController() {
    return pictureShowController;
  }

  public void setPictureShowController(
      PictureShowController pictureShowController) {
    this.pictureShowController = pictureShowController;
  }

  public Controller getMainController() {
    return mainController;
  }

  public void setMainController(Controller mainController) {
    this.mainController = mainController;
  }
}
