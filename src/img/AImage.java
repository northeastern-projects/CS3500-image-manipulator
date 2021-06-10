package src.img;

import java.util.List;
import src.filter.IFilter;

public abstract class AImage implements IImage{

  protected List<Pixel> pixels;

  public AImage(List<Pixel> pixels) {
    this.pixels = pixels;
  }

  @Override
  public void applyFilter(IFilter IFilter) {
    this.pixels = IFilter.modify(this.pixels);
  }

  @Override
  public void print() {
    this.pixels.forEach(System.out::println);
  }

}
