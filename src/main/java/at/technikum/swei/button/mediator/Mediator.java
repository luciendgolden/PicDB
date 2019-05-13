package at.technikum.swei.button.mediator;

public interface Mediator<T>{
  void registerComponent(Component component);

  void call(T button);
}
