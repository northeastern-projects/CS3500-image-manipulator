package Controller;

import FileController.FileController;
import FileController.IFileController;
import LayerModel.ILayer;
import View.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents an implementation of IController that allows delegation to the view and
 * model as well as input and output control. This controller allows a models state to be saved
 * as a file as well as reading through an input file to delegate commands.
 */
public class Controller implements IController {

  private final IFileController fileController;
  private final View view;
  private final ILayer model;

  /**
   * Creates a controller object.
   *
   * @param view a View object
   * @param model An ILayer object.
   */
  public Controller(View view, ILayer model) {
    this.fileController = new FileController();
    this.view = view;
    this.model = model;
  }

  @Override
  public void handleInput(String input) {

  }

  @Override
  public void saveState(String stateName) throws IOException {
    fileController.writeTextOrPPM(stateName, "txt", model.toString());
  }

  @Override
  public void loadState(String stateName) throws FileNotFoundException {
    try {
    fileController.readText(stateName);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found.");
    }
  }
}
