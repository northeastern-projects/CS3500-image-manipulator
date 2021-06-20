package Controller;

import FileController.FileController;
import FileController.IFileController;
import LayerModel.ILayer;
import View.View;
import java.io.IOException;

public class Controller implements IController{

  private final IFileController fileController;
  private final View view;
  private final ILayer model;

  public Controller (View view, ILayer model) {
    this.fileController = new FileController();
    this.view = view;
    this.model = model;
  }

  @Override
  public void handleInput(String input) {

  }

  @Override
  public void saveState(String stateName) throws IOException {
    fileController.writeFile(stateName, "txt", model.toString());
  }

  @Override
  public void loadState(String stateName) {
    fileController.readText(stateName);
  }
}
