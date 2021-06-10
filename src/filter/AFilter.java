package src.filter;

import java.util.ArrayList;
import java.util.List;
import src.img.Pixel;

public abstract class AFilter implements IFilter {

  protected List<Pixel> pixels;
  protected Number[][] kernel;

  public AFilter(Number[][] kernel) {

    if (!this.isValidKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel");
    }

    this.pixels = new ArrayList<>();
    this.kernel = kernel;

  }

  private boolean isValidKernel(Number[][] kernel) {
    if (kernel.length % 2 == 0) {
      return false;
    }

    for (Number[] row : kernel) {
      if (row.length % 2 == 0) {
        return false;
      }
    }

    return true;
  }

  private Pixel applyToPixel(List<Pixel> pixels, Pixel pixel) {
    //get neighbours
    List<Pixel> neighbours = pixel.getNeighbours(pixels, kernel.length);
    //for every true neighbour do math
    for(int i = 0; i < neighbours.size(); i++) {
      if (neighbours.get(i) != null) {

      }
    }
    //then make new pixel and return it.
  }

  @Override
  public List<Pixel> modify(List<Pixel> pixels) {
    pixels.forEach((pixel) -> {
      this.pixels.add(this.applyToPixel(pixels, pixel));
    });

    return this.pixels;
  }
}
