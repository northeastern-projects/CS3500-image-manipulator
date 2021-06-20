package LayerModel;

import ImageModel.IImage;
import FilterModel.IModifier;
import java.util.List;

public interface ILayer {

  void addLayer(IImage image);

  IImage getLayer(int index);

  IImage blend();

  void setCurrent(int index);

  void applyToCurrent(IModifier modifier);

  List<Integer> getProps();

}
