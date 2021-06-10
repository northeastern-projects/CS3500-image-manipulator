package src.img;

import java.io.IOException;
import src.filter.IModifier;

public interface IImage {

  void applyFilter(IModifier IModifier);
  void print();
  void save() throws IOException;

}
