package filter;

import imagemodel.IImage;
import imagemodel.IPixel;
import imagemodel.Pixel;

import java.util.ArrayList;
import java.util.List;

public class DownScaling implements IModifier {
  private int width;
  private int height;

  public DownScaling(int width, int height) {
    this.width = width;
    this.height = height;
  }


  @Override
  public String toString() {
    return "downscale";
  }

  @Override
  public List<IPixel> modify(IImage image) {
    int width = image.getProps().get(0);
    int height = image.getProps().get(1);

    for (IPixel pixel : image.getPixels()) {
      double newXRatio = 1.0 * pixel.getCoords().get(0) / width;
      double newYRatio = 1.0 * pixel.getCoords().get(1) / height;
      int newWidth = (int) (this.width * newXRatio);
      int newHeight = (int) (this.height * newYRatio);
      new Pixel(newWidth, newHeight, this.getColorAt(newXRatio, newYRatio, newWidth, newHeight));
    }

    /**
     * 3000 --- 1000 1/3 (333.9,334)
     *
     * for every pixel in ImagePixels, we find corresponding location
     * for new pixels
     * return the new list of updated sized pixels
     */
    return image.getPixels();

    return null;

  }

  private List<Double> getColorAt(double xRatio, double yRatio, int width, int height) {
    List<Double> rgb = new ArrayList<>();
    double r = 0.0;
    double g = 0.0;
    double b = 0.0;

    rgb.add(r);
    rgb.add(g);
    rgb.add(b);
    return rgb;


  }
}
