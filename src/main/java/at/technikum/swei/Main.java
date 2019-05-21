package at.technikum.swei;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws Exception {
    scene = new Scene(loadFXML("scene"));
    stage.setTitle("PICDB");
    stage.setScene(scene);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("fxml" + File.separator + fxml + ".fxml"));
    return fxmlLoader.load();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
