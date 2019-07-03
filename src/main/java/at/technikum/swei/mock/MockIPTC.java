package at.technikum.swei.mock;

import at.technikum.swei.domain.IPTC;
import java.util.Random;

public class MockIPTC {

  private static final String[] colorMark = new String[]{
      "Green",
      "Blue",
      "Yellow",
      "Pink",
  };

  private static final String[] description = new String[]{
      "Braccas meas vescimini!",
      "Draco Dormiens Nunquam Titillandus",
      "All hope abandon, ye who enter here",
      "Non est ad astra mollis e terris via\" - \"There is no easy way from the earth to the stars",
  };



  public static IPTC create(){
    Random random = new Random();
    IPTC iptc = new IPTC();

    iptc.setColorMark(colorMark[random.nextInt(colorMark.length)]);
    iptc.setDescription(description[random.nextInt(description.length)]);
    iptc.setReview(String.valueOf(random.nextInt(10)));

    return iptc;
  }
}
