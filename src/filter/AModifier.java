package filter;

import java.util.ArrayList;
import java.util.List;
import img.Image;
import img.Pixel;

/**
 * The AModifier abstract class implements methods used by both filters and transforms.
 * Extends the IModifier interface.
 */
public abstract class AModifier implements IModifier{
  protected List<Pixel> pixels;
  protected double[][] kernel;
  protected List<Double> crushedKernel;

  /**
   * Instantiates a new Modifier.
   *
   * @param kernel the kernel which is validated to be an odd x odd configuration
   */
  public AModifier(double[][] kernel) {

    if (!this.isValidKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel");
    }

    this.pixels = new ArrayList<>();
    this.kernel = kernel;
    this.crushedKernel = this.flattenKernel(kernel);

  }

  private boolean isValidKernel(double[][] kernel) {
    if (kernel.length % 2 == 0) {
      return false;
    }

    for (double[] row : kernel) {
      if (row.length % 2 == 0) {
        return false;
      }
    }

    return true;
  }

  protected List<Double> flattenKernel(double[][] kernel) {
    List<Double> crushedKernel = new ArrayList<>();

    for (double[] row : kernel) {
      for (double n : row) {
        crushedKernel.add(n);
      }
    }

    return crushedKernel;
  }

  abstract protected Pixel applyToPixel(List<Pixel> pixels, Pixel pixel, int width, int height);

  @Override
  public List<Pixel> modify(Image image) {
    System.out.println("Applying modifier...");
    List<Pixel> origPixels = image.pixels;

    origPixels.forEach((pixel) -> {
      this.pixels.add(this.applyToPixel(origPixels, pixel, image.width, image.height));
    });

    return this.pixels;
  }
}
