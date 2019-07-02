package at.technikum.swei;

import org.junit.Test;

public class FoundationTest {

  @Test
  public void test001() {
    /*
    String path = Main.class
        .getClassLoader()
        .getResource("images")
        .getPath();
     */

    String path = Main.class.getClassLoader().getResource("images")
        .getPath();

    System.out.println(path);
  }

}
