package src.img;

import java.io.IOException;
import java.util.List;
import src.filter.IModifier;
import src.utils.ImageUtil;

public class Image implements IImage{

  private List<Pixel> pixels;
  private final int width;
  private final int height;
  private final int depth;

  public Image(List<Pixel> pixels, int width, int height, int depth) {
    this.pixels = pixels;
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  private String getFileString() {
    return "P3\n" + width + "\n" + height + "\n" + depth + "\n" + this.pixels.toString() + "\n";
  }

  @Override
  public void applyFilter(IModifier IModifier) {
    this.pixels = IModifier.modify(this.pixels);
  }

  @Override
  public void print() {
    this.pixels.forEach(System.out::println);
  }

  @Override
  public void save() throws IOException {
    ImageUtil.writePPM("result.ppm", this.getFileString());
  }
}
