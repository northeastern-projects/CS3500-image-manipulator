package layer;

import img.IImage;
import java.io.IOException;
import java.util.List;

public interface ILayer {

  void addLayer(IImage image);

  IImage getLayer(int index);

  List<Integer> getLayerInfo();

  IImage blend();

  void save(String fName) throws IOException;

  List<Integer> getProps();

}
