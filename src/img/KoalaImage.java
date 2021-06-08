package src.img;

import java.util.Map;
import java.util.List;
import src.filter.IFilter;

public class KoalaImage extends AImage{

  public KoalaImage(Map<List<Integer>, List<Integer>> pixels) {
    super(pixels);
  }

  @Override
  public void applyFilter(IFilter IFilter) {
    IFilter.modify(super.pixels);
  }
}
