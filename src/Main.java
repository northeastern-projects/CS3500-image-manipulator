import controller.Controller;
import controller.IController;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import layermodel.ILayer;
import layermodel.Layer;
import view.ITextView;
import view.TextView;

/**
 * This class allows a user to create, modify,and save images or print images to the console.
 */
public class Main {

  /**
   * Demo of main.
   */
  public static void main(String[] args) throws IOException {
    ITextView view;
    try {
      if (args[0].equalsIgnoreCase("file")) {
        view = new TextView(new FileReader(args[1]), System.out);
      } else if (args[0].equalsIgnoreCase("interactive")) {
        view = new TextView(new InputStreamReader(System.in), System.out);
      }
      else {
        throw new ArrayIndexOutOfBoundsException("");
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Please enter \'file\' with command script-file path or" +
              "enter \'interactive\' to manually create images.");
    }
    ILayer model = new Layer();
    IController controller = new Controller(view, model);
    controller.go();

  }
}
