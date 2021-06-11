package filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import img.Pixel;

public class ATransform extends AModifier{

  public ATransform(double[][] kernel) {
    super(kernel);
  }

  private List<Double> generateNewRGB(Pixel pixel) {
    List<Double> newRGB = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));
    newRGB.set(0, pixel.applyToR(crushedKernel.get(0))
        + pixel.applyToG(crushedKernel.get(1))
        + pixel.applyToB(crushedKernel.get(2)));
    newRGB.set(1, pixel.applyToR(crushedKernel.get(3))
        + pixel.applyToG(crushedKernel.get(4))
        + pixel.applyToB(crushedKernel.get(5)));
    newRGB.set(2, pixel.applyToR(crushedKernel.get(6))
        + pixel.applyToG(crushedKernel.get(7))
        + pixel.applyToB(crushedKernel.get(8)));

    return newRGB;
  }

  @Override
  protected Pixel applyToPixel(List<Pixel> pixels, Pixel pixel, int width) {
    //get pixel with new rgb values
    return new Pixel(pixel.x, pixel.y, this.generateNewRGB(pixel));
  }
}
