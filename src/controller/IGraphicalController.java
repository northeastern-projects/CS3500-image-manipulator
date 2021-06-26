package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

public interface IGraphicalController extends ActionListener {
  /**
   * Method that starts controller.
   *
   * @throws IOException when input or output fail
   */

  void go() throws IOException;
}
