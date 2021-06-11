import filter.Greyscale;
import filter.IModifier;
import img.Checkerboard;
import img.Image;
import img.Pixel;
import java.io.IOException;
import filter.Blur;
import filter.Sepia;
import filter.Sharpen;
import img.IImage;
import java.util.ArrayList;
import java.util.List;
import utils.ImageUtil;

public class Main {

  static IImage img;

  //demo main
  public static void main(String []args) throws IOException {
    String filename;
    String name = "flower";

    if (args.length > 0) {
      filename = args[0];
    }
    else {
      filename = "res/" + name + ".ppm";
    }

//    List<Pixel> pixels;
//    pixels = new ArrayList<Pixel>();
//    pixels.add(new Pixel(0, 0, 100, 100, 100));
//    pixels.add(new Pixel(0, 1, 100, 100, 100));
//    pixels.add(new Pixel(0, 2, 100, 100, 100));
//    pixels.add(new Pixel(1, 0, 100, 100, 100));
//    pixels.add(new Pixel(1, 1, 100, 100, 100));
//    pixels.add(new Pixel(1, 2, 100, 100, 100));
//    pixels.add(new Pixel(2, 0, 100, 100, 100));
//    pixels.add(new Pixel(2, 1, 100, 100, 100));
//    pixels.add(new Pixel(2, 2, 100, 100, 100));
//
//    img = new Image(pixels, 3,3,255);
//    IModifier mod = new Blur();
//    img.applyFilter(mod);
//    System.out.print(img);

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
