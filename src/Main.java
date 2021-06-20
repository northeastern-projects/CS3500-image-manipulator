import java.io.IOException;

import FileController.IFileController;
import FileController.FileController;

/**
 * This class allows a user to create, modify,and save images or print images to the console.
 */
public class Main {

  /**
   * Demo of main. Currently, creates a blurred, sharpened, monochrome, and sharpened version of
   * flower, a ppm file that exists in the res/ folder. Also has the ability to create a
   * checkerboard image.
   */
  public static void main(String[] args) {
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

    IFileController controller = new FileController();
    //System.out.println(img);

  }
}
