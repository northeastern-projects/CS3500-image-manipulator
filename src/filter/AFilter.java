package filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import img.Pixel;

public abstract class AFilter extends AModifier{

  public AFilter(double[][] kernel) {
    super(kernel);
  }

  private List<Double> generateNewRGB(List<Pixel> origPixels, Pixel pixel, int width, int height) {
    List<Double> newRGB = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));

    int key = (kernel.length - 1) / 2;
    int kIndex = 0;
    for (int i = -1 * key; i <= key; i++) {
      for (int j = -1 * key; j <= key; j++) {

        int index = ((pixel.y + i) * width) + (pixel.x + j);

        if (!(index < 0 || index >= origPixels.size() || (pixel.x + j < 0) ||(pixel.y + i < 0) || (pixel.x + j) > width - 1 || (pixel.y + i) > height - 1)) {
          List<Double> thisRGB = origPixels.get(index).applyToAllChannels(crushedKernel.get(kIndex));

          for (int k = 0; k < newRGB.size(); k++) {
            newRGB.set(k, newRGB.get(k) + thisRGB.get(k));
          }
        }

        kIndex++;
      }
    }

    return newRGB;
  }

  @Override
  protected Pixel applyToPixel(List<Pixel> origPixels, Pixel pixel, int width, int height) {
    //get pixel with new rgb values
    return new Pixel(pixel.x, pixel.y, this.generateNewRGB(origPixels, pixel, width, height));
  }
}
