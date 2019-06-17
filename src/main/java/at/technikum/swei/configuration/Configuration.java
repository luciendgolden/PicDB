package at.technikum.swei.configuration;

import at.technikum.swei.Main;
import java.io.File;
import org.apache.log4j.PropertyConfigurator;

public class Configuration {

  public static Configuration INSTANCE;

  static{
    PropertyConfigurator.configure(
        Configuration.class
            .getClassLoader()
            .getResource("META-INF" + File.separator + "log4j.properties"));
  }

  private Configuration() {
  }

  public static Configuration   getINSTANCE() {
    if (INSTANCE==null)
      INSTANCE = new Configuration();
    return INSTANCE;
  }
}
