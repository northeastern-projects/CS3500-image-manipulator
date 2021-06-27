import controller.GraphicalController;
import controller.IController;
import controller.TextController;
import layermodel.ILayer;
import layermodel.Layer;
import view.GraphicalView;
import view.IGraphicalView;
import view.ITextView;
import view.TextView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class allows a user to create, modify,and save images or print images to the console.
 */
public class Main {

  /**
   * Demo of main with two text and one gui view.
   */
  public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
    ITextView view;
    IGraphicalView view2;
    IController controller;
    ILayer model = new Layer();
    if (args.length < 1) {
      throw new IllegalArgumentException("Please enter 'file' followed by the txt file name for"
              + "file-based scripting or enter 'interactive' for user input.\n");
    }
    if (args[0].equalsIgnoreCase("-script")) {
      view = new TextView(new FileReader(args[1]), System.out);
      controller = new TextController(view, model);
      controller.go();
    } else if (args[0].equalsIgnoreCase("-text")) {
      view = new TextView(new InputStreamReader(System.in), System.out);
      controller = new TextController(view, model);
      controller.go();
    } else if (args[0].equalsIgnoreCase("-interactive")) {
      UIManager.setLookAndFeel(new NimbusLookAndFeel());
      view2 = new GraphicalView("Image manipulator!", model);
      controller = new GraphicalController(view2, model);
      controller.go();
    } else {
      throw new IllegalArgumentException("Please enter 'file' followed by the txt file name for"
              + "file-based scripting or enter 'interactive' for user input.\n");
    }
  }
}
