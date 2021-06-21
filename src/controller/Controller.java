package controller;

import filecontroller.FileController;
import filecontroller.IFileController;
import filter.Blur;
import filter.Greyscale;
import filter.IModifier;
import filter.Sepia;
import filter.Sharpen;
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
  private final ILayer model;

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
  }

  @Override
  public void go() throws IOException {
    while (true) {
      this.handleInput(this.view.getInput());
    }
  }

  private void handleInput(String input) throws IOException {
    String[] components = input.split(" ");

    /*
    possible commands that the user can issue:
    load STRING: path to image (auto creates new layer)
    apply MODIFIER: apply a modifier to current image
    set INTEGER: set this as current
    save STRING: save the layer with the name
     */

    switch(components[0]) {
      case "load":
        model.addLayer(fileController.readImage(components[1]));
        break;
      case "apply":
        model.applyToCurrent(this.getModifier(components[1]));
        break;
      case "set":
        model.setCurrent(Integer.parseInt(components[1]));
        break;
      case "save":
        this.saveState(components[1]);
    }
  }

  private IModifier getModifier(String name) {
    switch (name) {
      case "blur":
        return new Blur();
      case "sharpen":
        return new Sharpen();
      case "sepia":
        return new Sepia();
      case "greyscale":
        return new Greyscale();
      default:
        throw new UnsupportedOperationException("Cannot find such a modifier");
    }
  }

  private void saveState(String stateName) throws IOException {
    fileController.writeTextOrPPM(stateName, "txt", model.toString());
  }

  private void parseState(String state) throws IOException {
    String[] cmds = state.split(System.lineSeparator());

    for (String cmd : cmds) {
      this.handleInput(cmd);
    }
  }

  private void loadState(String stateName) throws IOException {
    try {
      parseState(fileController.readText(stateName));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found.");
    }
  }
}