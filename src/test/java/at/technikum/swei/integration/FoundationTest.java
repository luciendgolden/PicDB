package at.technikum.swei.integration;

import at.technikum.swei.Main;
import org.junit.Test;

public class FoundationTest {

  @Test
  public void test001() {
    String path = Main.class.getClassLoader().getResource("images")
        .getPath();

    System.out.println(path);
  }

}
