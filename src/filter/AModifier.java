package src.filter;

import java.util.ArrayList;
import java.util.List;
import src.img.Pixel;

public abstract class AModifier implements IModifier{
  protected List<Pixel> pixels;
  protected float[][] kernel;

  public AModifier(float[][] kernel) {

    if (!this.isValidKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel");
    }

    this.pixels = new ArrayList<>();
    this.kernel = kernel;

  }

  private boolean isValidKernel(float[][] kernel) {
    if (kernel.length % 2 == 0) {
      return false;
    }

    for (float[] row : kernel) {
      if (row.length % 2 == 0) {
        return false;
      }
    }

    return true;
  }

  protected List<Float> flattenKernel() {
    List<Float> crushedKernel = new ArrayList<>();

    for (float[] row : kernel) {
      for (float n : row) {
        crushedKernel.add(n);
      }
    }

    return crushedKernel;
  }
}
