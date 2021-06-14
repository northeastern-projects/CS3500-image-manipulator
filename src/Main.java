import filter.Greyscale;
import filter.IModifier;

import java.io.IOException;

import filter.Blur;
import filter.Sepia;
import filter.Sharpen;
import img.IImage;

import utils.ImageUtil;

/**
 * This class allows a user to create, modify,and save images or print images to the console.
 */
public class Main {

  static IImage img;

  /**
   * Demo of main. Currently, creates a blurred, sharpened, monochrome, and sharpened version of
   * flower, a ppm file that exists in the res/ folder. Also has the ability to create a
   * checkerboard image.
   */
  public static void main(String[] args) throws IOException {
    String filename;
    String name = "road";

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res/" + name + ".ppm";
    }

    // code for Checkerboard creation
    // List<Pixel> pixels;
    // pixels = new ArrayList<Pixel>();
    // pixels.add(new Pixel(0, 0, 100, 100, 100));
    // pixels.add(new Pixel(0, 1, 100, 100, 100));
    // pixels.add(new Pixel(0, 2, 100, 100, 100));
    // pixels.add(new Pixel(1, 0, 100, 100, 100));
    // pixels.add(new Pixel(1, 1, 100, 100, 100));
    // pixels.add(new Pixel(1, 2, 100, 100, 100));
    // pixels.add(new Pixel(2, 0, 100, 100, 100));
    // pixels.add(new Pixel(2, 1, 100, 100, 100));
    // pixels.add(new Pixel(2, 2, 100, 100, 100));

    // img = new Image(pixels, 3,3,255);
    // IModifier mod = new Blur();
    // img.applyFilter(mod);
    // System.out.print(img);

    // img = new Checkerboard(19, 11, 255);
    // img.save("check");

    //code for Image Creation
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
