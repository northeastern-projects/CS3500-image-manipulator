package img;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import filter.IModifier;
import javax.imageio.ImageIO;
import utils.ImageUtil;

/**
 * The Image class that contains methods to print Image as a file, modify an image, and save an
 * image to a new file in the res folder. This class also implements the IImage interface.
 */
public class Image implements IImage {

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
   * @throws IllegalArgumentException if pixels is null or if width, height, or
   *                                  depth are less than 0
   */
  public Image(List<Pixel> pixels, int width, int height, int depth) {
    if (pixels == null || width < 0 || height < 0 || depth < 0) {
      throw new IllegalArgumentException("Invalid Parameters.");
    }
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
  public void applyFilter(IModifier iModifier) {
    this.pixels = iModifier.modify(this);
  }

  @Override
  public void save(String fName, String fType) throws IOException {
    System.out.println("Saving..."); //TODO
    ImageUtil.writePPM("res/" + fName + fType, this.toString());
    System.out.println("Done!\n");
  }

  @Override
  public List<Pixel> getPixels() {
    return new ArrayList<>(this.pixels);
  }

  @Override
  public Pixel getPixel(int x, int y) {
    return this.pixels.get((y * width) + x);
  }

  @Override
  public List<Integer> getProps() {
    return new ArrayList<>(Arrays.asList(this.width, this.height, this.depth));
  }
}
