import filter.Greyscale;
import filter.IModifier;

import img.Image;
import java.io.IOException;

import filter.Blur;
import filter.Sepia;
import filter.Sharpen;
import img.IImage;

import java.util.ArrayList;
import java.util.Arrays;
import layer.ILayer;
import layer.Layer;
import utils.ImageUtil;

/**
 * This class allows a user to create, modify,and save images or print images to the console.
 */
public class Main {

  /**
   * Demo of main. Currently, creates a blurred, sharpened, monochrome, and sharpened version of
   * flower, a ppm file that exists in the res/ folder. Also has the ability to create a
   * checkerboard image.
   */
  public static void main(String[] args) throws IOException {
    String filename;
    String name = "testpng.png";

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res/" + name;
    }

    IImage img = ImageUtil.readFile(filename);
    System.out.println(img);

  }
}
