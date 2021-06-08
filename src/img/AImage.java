package src.img;

import java.util.List;
import java.util.Map;
import src.filter.IFilter;

public abstract class AImage implements IImage{

  protected Map<List<Integer>, List<Integer>> pixels;

  public AImage(Map<List<Integer>, List<Integer>> pixels) {
    this.pixels = pixels;
  }

  @Override
  public void applyFilter(IFilter IFilter) {
    this.pixels = IFilter.modify(this.pixels);
  }

  @Override
  public void print() {
    this.pixels.forEach((pixel, rgb) -> {
      System.out.println("Pixel: " + pixel + " has RGB: " + rgb);
    });
  }

}
