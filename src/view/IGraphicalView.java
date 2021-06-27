package view;

import java.awt.event.ActionListener;
import java.util.List;

public interface IGraphicalView {

  void display();

  void refresh();

  void alert(String message);

  List<String> mosaicDetailsPane();

  List<String> downscaleDetailsPane();

  List<String> loadFileDialog();

  void setListener(ActionListener listener);

}
