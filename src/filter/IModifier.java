package filter;

import java.util.List;
import img.Image;
import img.Pixel;

public interface IModifier {

  List<Pixel> modify(Image image);

}
