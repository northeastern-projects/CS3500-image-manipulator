package controller;

import filecontroller.FileController;
import filecontroller.IFileController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import layermodel.ILayer;
import view.IGraphicalView;

public class GraphicalController implements IGraphicalController{

  private final IFileController fileController;
  private final IGraphicalView view;
  private ILayer model;
  private boolean running;

  public GraphicalController(IGraphicalView view, ILayer model) {
    this.fileController = new FileController();
    this.view = view;
    this.model = model;
    this.running = true;
  }

  @Override
  public void go() throws IOException {
    this.view.setListener(this);
    this.view.display();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

}
