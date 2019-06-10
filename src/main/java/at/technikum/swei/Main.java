package at.technikum.swei;

import at.technikum.swei.configuration.Configuration;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;

public class Main extends Application {

  private Configuration configuration = Configuration.getINSTANCE();

  private static final Logger logger = Logger.getLogger(Main.class);


  private static Scene scene;

  @Override
  public void start(Stage stage) throws Exception {

    logger.info("Start javafx application");

    scene = new Scene(loadFXML("mainWindowView"));
    stage.setTitle("PICDB");
    stage.setScene(scene);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getClassLoader().getResource("fxml" + File.separator + fxml + ".fxml"));
    return fxmlLoader.load();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
