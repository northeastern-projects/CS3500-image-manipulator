package src.filter;

import java.util.List;
import java.util.Map;
import src.img.Pixel;

public interface IFilter {

  List<Pixel> modify(List<Pixel> pixels);

}
