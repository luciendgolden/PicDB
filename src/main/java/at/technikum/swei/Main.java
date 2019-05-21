package at.technikum.swei;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = loadFXML("scene");
    primaryStage.setTitle("PICDB");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("fxml" + File.separator + fxml + ".fxml"));
    return fxmlLoader.load();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
