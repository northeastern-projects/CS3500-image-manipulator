import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import filecontroller.IFileController;
import filecontroller.FileController;
import imagemodel.IImage;
import imagemodel.IPixel;
import imagemodel.Image;
import imagemodel.Pixel;

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

    /* this should give script input file to controller which should parse that and dispatch to
     * view and model based on what it is given.
     */
    List<IPixel> pixels;
    IImage img;
    pixels = new ArrayList<>();
    pixels.add(new Pixel(0, 0, 100, 100, 100));
    pixels.add(new Pixel(0, 1, 200, 200, 200));
    pixels.add(new Pixel(0, 2, 100, 100, 100));
    pixels.add(new Pixel(1, 0, 100, 100, 100));
    pixels.add(new Pixel(1, 1, 100, 100, 100));
    pixels.add(new Pixel(1, 2, 100, 100, 100));
    pixels.add(new Pixel(2, 0, 100, 100, 100));
    pixels.add(new Pixel(2, 1, 100, 100, 100));
    pixels.add(new Pixel(2, 2, 100, 100, 100));

    img = new Image(pixels, 3, 3, 255);

    IFileController controller = new FileController();
    controller.writeImage("ex2", "jpg", img);
    //System.out.println(img);

  }
}
