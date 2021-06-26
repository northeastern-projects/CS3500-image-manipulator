import controller.GraphicalController;
import controller.IController;
import java.io.IOException;

import layermodel.ILayer;
import layermodel.Layer;
import view.GraphicalView;
import view.IGraphicalView;

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
    ILayer model = new Layer();
    IGraphicalView view = new GraphicalView("home");
    IController controller = new GraphicalController(view, model);

    controller.go();
  }
}
