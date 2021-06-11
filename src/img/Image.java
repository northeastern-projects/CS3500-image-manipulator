package img;

import java.io.IOException;
import java.util.List;
import filter.IModifier;
import utils.ImageUtil;

public class Image implements IImage{

  public List<Pixel> pixels;
  public final int width;
  public final int height;
  public final int depth;

  public Image(List<Pixel> pixels, int width, int height, int depth) {
    this.pixels = pixels;
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  private String getFileString() {
    StringBuilder matrix = new StringBuilder("P3\n" + width + "\n" + height + "\n" + depth + "\n");
    for (Pixel p : this.pixels) {
      matrix.append(p.toString());
    }
    return matrix + "\n";
  }

  @Override
  public void applyFilter(IModifier IModifier) {
    this.pixels = IModifier.modify(this);
  }

  @Override
  public void print() {
    this.pixels.forEach(System.out::println);
  }

  @Override
  public void save(String fName) throws IOException {
    System.out.println("Saving...");
    ImageUtil.writePPM("res/" + fName + ".ppm", this.getFileString());
    System.out.println("Done!\n");
  }
}
