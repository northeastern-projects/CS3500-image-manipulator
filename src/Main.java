package src;

import src.img.IImage;
import src.img.KoalaImage;
import src.utils.ImageUtil;

public class Main {

  static IImage koala;

  //demo main
  public static void main(String []args) {
    String filename;

    if (args.length>0) {
      filename = args[0];
    }
    else {
      filename = "src/img/Koala.ppm";
    }

    koala = new KoalaImage(ImageUtil.readPPM(filename));
    koala.print();
  }

}
