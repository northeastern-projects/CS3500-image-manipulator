import filter.Greyscale;
import filter.IModifier;
import img.Checkerboard;
import java.io.IOException;
import filter.Blur;
import filter.Sepia;
import filter.Sharpen;
import img.IImage;
import utils.ImageUtil;

public class Main {

  static IImage img;

  //demo main
  public static void main(String []args) throws IOException {
    String filename;
    String name = "road";

    if (args.length > 0) {
      filename = args[0];
    }
    else {
      filename = "res/" + name + ".ppm";
    }

//    img = new Checkerboard(19, 11, 255);
//    img.save("check");

    img = ImageUtil.readPPM(filename);
    IModifier mod = new Blur();
    img.applyFilter(mod);
    img.save(name + "_" + mod);

    img = ImageUtil.readPPM(filename);
    mod = new Sharpen();
    img.applyFilter(mod);
    img.save(name + "_" + mod);

    img = ImageUtil.readPPM(filename);
    mod = new Sepia();
    img.applyFilter(mod);
    img.save(name + "_" + mod);

    img = ImageUtil.readPPM(filename);
    mod = new Greyscale();
    img.applyFilter(mod);
    img.save(name + "_" + mod);
  }

}
