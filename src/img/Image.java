package img;

import java.io.IOException;
import java.util.List;
import filter.IModifier;
import utils.ImageUtil;

/**
 * The Image class that contains all methods to represent and modify an image.
 */
public class Image implements IImage{

  public List<Pixel> pixels;
  public final int width;
  public final int height;
  public final int depth;

  /**
   * Instantiates a new Image.
   *
   * @param pixels the pixels that make up the image (note this is 1D not 2D)
   * @param width  the width of the image
   * @param height the height
   * @param depth  the color depth (usually 255)
   */
  public Image(List<Pixel> pixels, int width, int height, int depth) {
    this.pixels = pixels;
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  @Override
  public String toString() {
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
  public void save(String fName) throws IOException {
    System.out.println("Saving...");
    ImageUtil.writePPM("res/" + fName + ".ppm", this.toString());
    System.out.println("Done!\n");
  }
}
