package view;

import java.awt.event.ActionListener;
import java.util.List;
import layermodel.IROLayer;

public interface IGraphicalView {

  void display();

  void refresh();

  void alert(String message);

  void setModel(IROLayer model);

  List<String> dialogHandler(String identifier);

  void setListener(ActionListener listener);

}
