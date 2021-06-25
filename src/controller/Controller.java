package controller;

import filecontroller.FileController;
import filecontroller.IFileController;
import filter.*;
import layermodel.ILayer;
import view.ITextView;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents an implementation of IController that allows delegation to the view and
 * model as well as input and output control. This controller allows a models state to be saved
 * as a file as well as reading through an input file to delegate commands.
 */
public class Controller implements IController {

  private final IFileController fileController;
  private final ITextView view;
  private ILayer model;
  private boolean running;

  /**
   * Creates a controller object.
   *
   * @param view  a View object
   * @param model An ILayer object.
   */
  public Controller(ITextView view, ILayer model) {
    this.fileController = new FileController();
    this.view = view;
    this.model = model;
    this.running = true;
  }

  @Override
  public void go() throws IOException {
    while (this.running) {
      this.handleInput(this.view.getInput());
    }
  }

  private void handleInput(String input) throws IOException {
    String[] components = input.split(" ");

    /*
    possible commands that the user can issue:
    
    load image STRING: path to image (auto creates new layer)
    load state STRING: path to state
    apply MODIFIER: apply a modifier to current image
    set INTEGER: set this as current

    save state STRING: save the layer with the name
    save image INTEGER STRING: save the image at said index with given name
    save image current STRING: save the current image with the given name
    save image all STRING: export each image individually with the given prefix + index

    export STRING: save the layer as an image with the given name;

    toggle INTEGER: index of layer
     */

    if (components.length < 2) {
      if (components[0].equalsIgnoreCase("exit")) {
        this.view.displayOutput("All unsaved changes will be lost.\n");
        this.running = false;
      } else {
        this.view.displayOutput("Invalid number of arguments.\n");
      }
    } else {
      switch (components[0]) {
        case "load":
          this.loadInputHandler(components);
          break;
        case "apply":
          IModifier modifier = this.getModifier(components[1]);
          if (modifier != null) {
            try {
              model.applyToCurrent(modifier);
            } catch (IllegalArgumentException e) {
              this.view.displayOutput(e.getMessage() + System.lineSeparator());
            }
          }
          break;
        case "set":
          try {
            try {
              model.setCurrent(Integer.parseInt(components[1]));
            } catch (IllegalArgumentException e) {
              this.view.displayOutput("Must enter integer.\n");
            }
          } catch (IllegalArgumentException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
          break;
        case "save":
          try {
            this.saveInputHandler(components);
          } catch (IOException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
          break;
        case "toggle":
          try {
            try {
              this.model.toggleVisibility(Integer.parseInt(components[1]));
            } catch (IllegalArgumentException e) {
              this.view.displayOutput("Must enter integer after toggle.\n");
            }
          } catch (IllegalArgumentException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
          break;
        case "export":
          String[] subcomps = components[1].split("\\.");
          this.fileController.writeImage(subcomps[0], subcomps[1], this.model.blend());
        default:
          this.view.displayOutput("unable to perform that operation!\n");
      }
    }
  }

  private void saveInputHandler(String[] args) throws IOException {
    if (args.length < 3) {
      this.view.displayOutput("Invalid number of arguments.\n");
    } else {
      switch (args[1]) {
        case "image":
          try {
            this.saveImageHandlerHelper(args);
          } catch (IOException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
          break;
        case "state":
          try {
            this.saveState(args[2]);
          } catch (IOException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
        default:
          this.view.displayOutput("unable to perform that operation!\n");
      }
    }
  }

  private void saveImageHandlerHelper(String[] args) throws IOException {
    if (args.length < 4) {
      this.view.displayOutput("Invalid number of arguments.\n");
    } else {
      String[] subcomps = args[3].split("\\.");
      if (subcomps.length < 2) {
        this.view.displayOutput("Invalid file extension on " + args[3] + "." + System.lineSeparator());
      } else {
        switch (args[2]) {
          case "current":
            this.fileController.writeImage(subcomps[0], subcomps[1], this.model.getCurrent());
          case "all":
            //DO NOTHING
          default:
            try {
              this.fileController.writeImage(subcomps[0], subcomps[1],
                      this.model.getLayer(Integer.parseInt(args[2])));
            } catch (IllegalArgumentException e) {
              this.view.displayOutput("Layer at this index does not exist.\n");
            }

        }
      }
    }
  }

  private void loadInputHandler(String[] args) throws IOException {
    if (args.length < 3) {
      this.view.displayOutput("Invalid number of arguments.\n");
    } else {
      switch (args[1]) {
        case "image":
          try {
            this.model.addLayer(fileController.readImage(args[2]));
          } catch (UnsupportedOperationException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
          break;
        case "state":
          try {
            this.model = this.fileController.readState(args[2]);
          } catch (FileNotFoundException e) {
            this.view.displayOutput(e.getMessage() + System.lineSeparator());
          }
          break;
        default:
          this.view.displayOutput("Unknown asset to load.\n");
      }
    }
  }

  private IModifier getModifier(String name) throws IOException {
    switch (name) {
      case "blur":
        return new Blur();
      case "sharpen":
        return new Sharpen();
      case "sepia":
        return new Sepia();
      case "greyscale":
        return new Greyscale();
      case "mosaic":
        return new Mosaic(15000);
      case "downscale":
        return new DownScaling(1080, 720);
      default:
        this.view.displayOutput("Cannot apply that modifier!\n");
        return null;
    }
  }

  private void saveState(String stateName) throws IOException {
    fileController.writeTextOrPPM("res/" + stateName, "txt", model.toString());
  }
}