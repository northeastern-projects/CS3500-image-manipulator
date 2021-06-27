package controller;

import filecontroller.FileController;
import filecontroller.IFileController;
import filter.DownScale;
import imagemodel.IImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import filter.Blur;
import filter.Greyscale;
import filter.Mosaic;
import filter.Sepia;
import filter.Sharpen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import layermodel.ILayer;
import layermodel.Layer;
import view.IGraphicalView;

/**
 * This class represents a visual rendering of an Image Processing server. It implements IController
 * and ActionListener in order to create a GUI version of an ILayer model. This class takes in an
 * IGraphical view and an ILayer model and delegates to each based on what the user does when main
 * is running. This class has a method that starts the controller, go, and an actionPerformed
 * method that refreshes the GUI each time a button is pushed or input is given.
 */
public class GraphicalController implements IController, ActionListener {

  private final IFileController fileController;
  private final IGraphicalView view;
  private ILayer model;

  /**
   * Creates a GraphicalController object with a view and model.
   *
   * @param view IGraphicalView obj
   * @param model ILayer obj
   */
  public GraphicalController(IGraphicalView view, ILayer model) {
    this.fileController = new FileController();
    this.view = view;
    this.model = model;
  }

  @Override
  public void go() {
    this.view.setListener(this);
    this.view.display();
  }

  private void loadHandler() {
    List<String> res = this.view.dialogHandler("load");
    if (res.size() == 2) {
      if (res.get(0).equals("Image")) {
        try {
          this.model.addLayer(this.fileController.readImage(res.get(1)));
        } catch (IOException ioException) {
          this.view.alert("There was an error loading that image");
        }
      } else {
        try {
          this.model = this.fileController.readState(res.get(1));
        } catch (FileNotFoundException e) {
          this.view.alert("There was an error loading the state");
        }
      }
    }
  }

  private boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private void mosaicHandler() {
    List<String> res = this.view.dialogHandler("mosaic");

    if (!res.get(0).equals("")) {
      String numSeed = res.get(0);

      if (this.isNumeric(numSeed)) {
        this.model.applyToCurrent(new Mosaic(Integer.parseInt(numSeed)));
      } else {
        this.view.alert("Please only enter a number");
      }
    } else {
      this.view.alert("Invalid number of seeds");
    }
  }

  private void downscaleHandler() {
    List<String> res = this.view.dialogHandler("downscale");

    if (!res.get(0).equals("") && !res.get(1).equals("")) {
      String width = res.get(0);
      String height = res.get(1);
      if (this.isNumeric(width) && this.isNumeric(height)) {
        this.model.applyToCurrent(new DownScale(Integer.parseInt(width), Integer.parseInt(height)));
      } else {
        this.view.alert("Width and height can only be numbers!");
      }
    } else {
      this.view.alert("Invalid width or height");
    }
  }

  private void toggleVisHandler() {
    List<String> res = this.view.dialogHandler("toggle");

    if (!res.get(0).equals("")) {
      String idx = res.get(0);
      if (this.isNumeric(idx)) {
        int pIdx = Integer.parseInt(idx);
        if (this.model.getProps().get(0) < pIdx || pIdx <= 0) {
          this.view.alert("That index is out of bounds!");
        } else {
          this.model.toggleVisibility(pIdx);
        }
      } else {
        this.view.alert("The index must be a number");
      }
    } else {
      this.view.alert("Invalid layer index");
    }
  }

  private void setCurrentHandler() {
    List<String> res = this.view.dialogHandler("set");

    if (!res.get(0).equals("")) {
      String idx = res.get(0);
      if (this.isNumeric(idx)) {
        int pIdx = Integer.parseInt(idx);
        if (this.model.getProps().get(0) < pIdx || pIdx <= 0) {
          this.view.alert("That index is out of bounds!");
        } else {
          this.model.setCurrent(pIdx);
        }
      } else {
        this.view.alert("The index must be a number");
      }
    } else {
      this.view.alert("Invalid layer index");
    }
  }

  private void saveHandler() {
    List<String> res = this.view.dialogHandler("save");
    String filePath = res.get(0);
    try {
      this.fileController.writeTextOrPPM(filePath, "txt", this.model.toString());
    } catch (IOException e) {
      this.view.alert("Could not save state!");
    }
  }

  private void exportHandler() {
    List<String> res = this.view.dialogHandler("export");
    String filePath = res.get(0);
    String fileExt = res.get(1);
    try {
      this.fileController.writeImage(filePath, fileExt, this.model.getCurrentVisible());
    } catch (IOException e) {
      this.view.alert("Error exporting image!");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("Load")) {
      this.loadHandler();
    } else {
      if (this.model.hasCurrent()) {
        switch (command) {
          case "Blur":
            this.model.applyToCurrent(new Blur());
            break;
          case "Sepia":
            this.model.applyToCurrent(new Sepia());
            break;
          case "Sharpen":
            this.model.applyToCurrent(new Sharpen());
            break;
          case "Greyscale":
            this.model.applyToCurrent(new Greyscale());
            break;
          case "Mosaic":
            this.mosaicHandler();
            break;
          case "Downscale":
            this.downscaleHandler();
            break;
          case "Toggle Visibility":
            this.toggleVisHandler();
            break;
          case "Set Current":
            this.setCurrentHandler();
            break;
          case "Save":
            this.saveHandler();
            break;
          case "Export":
            this.exportHandler();
            break;
          case "Blend All Layers":
            IImage blended = this.model.blend();
            this.model = new Layer(new ArrayList<>(Collections.singletonList(blended)), blended.getProps());
            this.view.setModel(this.model); //they loose sync otherwise
            break;
          default:
            throw new IllegalStateException("Reached a point which should absolutely not have "
                + "been reached");
        }
      } else {
        this.view.alert("There is no image to modify! Load an image first!");
      }
    }

    if (this.model.hasCurrent()) {
      this.view.refresh();
    }
  }
}
