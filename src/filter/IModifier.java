package src.filter;

import java.util.List;
import src.img.Pixel;

public interface IModifier {

  List<Pixel> modify(List<Pixel> pixels);

}
