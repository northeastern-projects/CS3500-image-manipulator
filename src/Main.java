import controller.Controller;
import controller.IController;
import layermodel.ILayer;
import layermodel.Layer;
import controller.GraphicalController;
import controller.IController;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import layermodel.ILayer;
import layermodel.Layer;
import view.GraphicalView;
import view.IGraphicalView;
import view.ITextView;
import view.TextView;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class allows a user to create, modify,and save images or print images to the console.
 */
public class Main {

  /**
   * Demo of main.
   */
//  public static void main(String[] args) throws IOException {
//    ITextView view;
//    if (args.length < 1) {
//      throw new IllegalArgumentException("Please enter 'file' followed by the txt file name for"
//          + "file-based scripting or enter 'interactive' for user input.\n");
//    }
//    if (args[0].equalsIgnoreCase("file")) {
//      view = new TextView(new FileReader(args[1]), System.out);
//    } else if (args[0].equalsIgnoreCase("interactive")) {
//      view = new TextView(new InputStreamReader(System.in), System.out);
//    } else {
//      throw new IllegalArgumentException("Please enter 'file' followed by the txt file name for"
//          + "file-based scripting or enter 'interactive' for user input.\n");
//    }
//    ILayer model = new Layer();
//    IController controller = new Controller(view, model);
//  }

  public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new NimbusLookAndFeel());

    ILayer model = new Layer();
    IGraphicalView view = new GraphicalView("Image manipulator!", model);
    IController controller = new GraphicalController(view, model);

    controller.go();
  }
}
