package at.technikum.swei.mock;

import at.technikum.swei.domain.EXIF;
import java.util.Random;

public class MockEXIF {

  private static final String[] cameraVendor = new String[]{
      "Sony",
      "Nikon",
      "Canon",
      "Panasonic",
  };

  public static EXIF create(){
    EXIF exif = new EXIF();
    Random rand = new Random();
    int randvendor = rand.nextInt(cameraVendor.length);

    exif.setCameraVendor(cameraVendor[randvendor]);
    exif.setFlash(String.valueOf(rand.nextBoolean()));
    exif.setResolution(String.valueOf(rand.nextInt(50))+" px");

    return exif;
  }
}
