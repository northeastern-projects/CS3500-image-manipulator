package view;

import java.awt.event.ActionListener;
import java.util.List;
import layermodel.IROLayer;

/**
 * This interface represents a GUI view. This interface creates methods that will allow further
 * implementation of a GUI. We have created methods that allow the rendering of a GUI, the ability
 * to refresh a GUi, the ability to show error messages, the ability to update the current read-only
 * model, the ability to deal will button commands
 *
 */
public interface IGraphicalView {

  void display();

  void refresh();

  void alert(String message);

  void setModel(IROLayer model);

  List<String> dialogHandler(String identifier);

  void setListener(ActionListener listener);

}
