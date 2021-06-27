package controller;

import filecontroller.FileController;
import filecontroller.IFileController;
import filter.DownScale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import filter.Blur;
import filter.Greyscale;
import filter.Mosaic;
import filter.Sepia;
import filter.Sharpen;
import java.util.List;
import layermodel.ILayer;
import view.IGraphicalView;

public class GraphicalController implements IController, ActionListener {

  private final IFileController fileController;
  private final IGraphicalView view;
  private ILayer model;

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
    List<String> res = this.view.loadFileDialog();
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
    List<String> res = this.view.mosaicDetailsPane();

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
    List<String> res = this.view.downscaleDetailsPane();

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

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //TODO add toggle visibility and set current
      case "Load":
        this.loadHandler();
        break;
      case "Blur":
        if (this.model.hasCurrent()) {
          this.model.applyToCurrent(new Blur());
        } else {
          this.view.alert("There is no image to modify! Load an image first!");
        }
        break;
      case "Sepia":
        if (this.model.hasCurrent()) {
          this.model.applyToCurrent(new Sepia());
        } else {
          this.view.alert("There is no image to modify! Load an image first!");
        }
        break;
      case "Sharpen":
        if (this.model.hasCurrent()) {
          this.model.applyToCurrent(new Sharpen());
        } else {
          this.view.alert("There is no image to modify! Load an image first!");
        }
        break;
      case "Greyscale":
        if (this.model.hasCurrent()) {
          this.model.applyToCurrent(new Greyscale());
        } else {
          this.view.alert("There is no image to modify! Load an image first!");
        }
        break;
      case "Mosaic":
        if (this.model.hasCurrent()) {
          this.mosaicHandler();
        } else {
          this.view.alert("There is no image to modify! Load an image first!");
        }
        break;
      case "Downscale":
        if (this.model.hasCurrent()) {
          this.downscaleHandler();
        } else {
          this.view.alert("There is no image to modify! Load an image first!");
        }
        break;
      case "Toggle":
        if (this.model.hasCurrent()) {
          
        }
        break;
      case "Set":
        break;
      case "Save":
        //needs file location and name
        break;
      case "Export":
        //needs file location and name
        break;
      case "Blend":
        this.model.blend();
        break;
      default:
        throw new IllegalStateException("Reached a point which should absolutely not have "
          + "been reached");
    }

    if (this.model.hasCurrent()) {
      this.view.refresh();
    }
  }

}
