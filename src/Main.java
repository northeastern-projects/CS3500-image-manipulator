package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import src.filter.Blur;
import src.img.IImage;
import src.utils.ImageUtil;

public class Main {

  static IImage koala;

  //demo main
  public static void main(String []args) throws IOException {
    String filename;

    if (args.length>0) {
      filename = args[0];
    }
    else {
      filename = "src/img/Koala.ppm";
    }

    koala = ImageUtil.readPPM(filename);
    koala.applyFilter(new Blur());
    koala.save();
  }

}
