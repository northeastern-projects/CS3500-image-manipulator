package src.img;

import java.util.Map;
import java.util.List;
import src.filter.IFilter;

public class KoalaImage extends AImage{

  public KoalaImage(List<Pixel> pixels) {
    super(pixels);
  }

  @Override
  public void applyFilter(IFilter IFilter) {
    IFilter.modify(super.pixels);
  }
}
