package src.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import src.img.Pixel;

public class ATransform extends AModifier{

  public ATransform(float[][] kernel) {
    super(kernel);
  }

  private List<Float> generateNewRGB(List<Float> crushedKernel, Pixel pixel) {
    List<Float> newRGB = new ArrayList<>(Arrays.asList(0f, 0f, 0f));
    newRGB.set(0, crushedKernel.get(0) * pixel.r
        + crushedKernel.get(1) * pixel.g
        + crushedKernel.get(2) * pixel.b);
    newRGB.set(1, crushedKernel.get(3) * pixel.r
        + crushedKernel.get(4) * pixel.g
        + crushedKernel.get(5) * pixel.b);
    newRGB.set(2, crushedKernel.get(6) * pixel.r
        + crushedKernel.get(7) * pixel.g
        + crushedKernel.get(8) * pixel.b);

    return newRGB;
  }

  private Pixel applyToPixel(List<Pixel> pixels, Pixel pixel) {
    //get pixel with new rgb values
    return new Pixel(pixel.x, pixel.y, this.generateNewRGB(this.flattenKernel(), pixel));
  }

  @Override
  public List<Pixel> modify(List<Pixel> pixels) {
    AtomicInteger count = new AtomicInteger();
    pixels.forEach((pixel) -> {
      this.pixels.add(this.applyToPixel(pixels, pixel));
      System.out.println(count.getAndIncrement() + "/" + pixels.size());
    });

    return this.pixels;
  }
}
