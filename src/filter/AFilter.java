package src.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AFilter implements IFilter {

  protected Map<List<Integer>, List<Integer>> pixels;
  protected Number[][] kernel;

  public AFilter(Number[][] kernel) {

    if (!this.isValidKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel");
    }

    this.pixels = new HashMap<>();
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

  private List<Integer> applyToPixel(Map<List<Integer>, List<Integer>> pixels, List<Integer> rgb) {
    for(Integer color: rgb) {
      //get neighbours etc...
      //do some matrix stuff here.
    }
  }

  @Override
  public Map<List<Integer>, List<Integer>> modify(Map<List<Integer>, List<Integer>> pixels) {

    pixels.forEach((pixel, rgb) -> {
      this.pixels.put(
       pixel, this.applyToPixel(pixels, rgb)
      );
    });

    return this.pixels;
  }
}
