package img;

import java.io.IOException;
import filter.IModifier;

public interface IImage {

  void applyFilter(IModifier IModifier);
  void save(String fName) throws IOException;

}
