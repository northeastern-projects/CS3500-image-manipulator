package layer;

import ImageModel.IImage;
import java.io.IOException;
import java.util.List;

public interface ILayer {

  void addLayer(IImage image);

  IImage getLayer(int index);

  List<Integer> getLayerInfo();

  IImage blend();

  void setCurrent(int index);

  List<Integer> getProps();

}
