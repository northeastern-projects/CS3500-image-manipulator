package src.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import src.img.Pixel;

public abstract class AFilter extends AModifier{

  public AFilter(float[][] kernel) {
    super(kernel);
  }

  private List<Float> generateNewRGB(List<Pixel> neighbours, List<Float> crushedKernel) {
    List<Float> newRGB = new ArrayList<>(Arrays.asList(0f, 0f, 0f));

    for (int i = 0; i < neighbours.size(); i++) {
      Pixel neighbour = neighbours.get(i);
      if (neighbour != null) {
        List<Float> thisRGB = neighbour.applyToAllChannels(crushedKernel.get(i));
        for (int j = 0; j < newRGB.size(); j++) {
          newRGB.set(j, newRGB.get(j) + thisRGB.get(j));
        }
      }
    }

    return newRGB;
  }

  private Pixel applyToPixel(List<Pixel> pixels, Pixel pixel) {
    //get neighbours
    List<Pixel> neighbours = pixel.getNeighbours(pixels, kernel.length);
    List<Float> crushedKernel = this.flattenKernel();
    //get pixel with new rbg values
    return new Pixel(pixel.x, pixel.y, this.generateNewRGB(neighbours, crushedKernel));
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
