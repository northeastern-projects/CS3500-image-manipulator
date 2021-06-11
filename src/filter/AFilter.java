package filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import img.Pixel;

public abstract class AFilter extends AModifier{

  public AFilter(double[][] kernel) {
    super(kernel);
  }

  private List<Double> generateNewRGB(List<Pixel> origPixels, Pixel pixel, int width) {
    List<Double> newRGB = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));

    int key = (kernel.length - 1) / 2;
    int kIndex = 0;
    for (int i = -1 * key; i <= key; i++) {
      for (int j = -1 * key; j <= key; j++) {
        int index = ((pixel.y + i) * width) + (pixel.x + j);
        if (index < 0 || index >= origPixels.size()) {
          kIndex++;
          break;
        } else {
          List<Double> thisRGB = origPixels.get(index).applyToAllChannels(crushedKernel.get(kIndex));
          for (int k = 0; k < newRGB.size(); k++) {
            newRGB.set(k, newRGB.get(k) + thisRGB.get(k));
          }
          kIndex++;
        }
      }
    }

    return newRGB;
  }

  @Override
  protected Pixel applyToPixel(List<Pixel> origPixels, Pixel pixel, int width) {
    //get pixel with new rgb values
    return new Pixel(pixel.x, pixel.y, this.generateNewRGB(origPixels, pixel, width));
  }
}
