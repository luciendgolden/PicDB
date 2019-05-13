package at.technikum.swei.button.mediator;

public interface Component {
  void setMediator(Mediator mediator);

  default String getName(){
    return this.getClass().getSimpleName();
  }
}
