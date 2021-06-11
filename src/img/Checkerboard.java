package img;

import filter.IModifier;
import java.io.IOException;
import utils.ImageUtil;

/**
 * The Checkerboard image contains a representation of a simple checkerboard image.
 * It contains all methods needed to create a black and white checkerboard pattern.
 * Extends the IImage interface.
 */
public class Checkerboard implements IImage{

  int width;
  int height;
  int depth;

  /**
   * Instantiates a new Checkerboard image.
   *
   * @param width  the width of the image
   * @param height the height (which may not be the same as the width)
   * @param depth  the color depth (normally 255)
   */
  public Checkerboard (int width, int height, int depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  @Override
  public String toString() {
    StringBuilder matrix = new StringBuilder("P3\n" + width + "\n" + height + "\n" + depth + "\n");
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        if ((i + j) % 2 == 0) {
          matrix.append("0 0 0  ");
        } else {
          matrix.append("255 255 255  ");
        }
      }
    }
    return matrix + "\n";
  }

  @Override
  public void applyFilter(IModifier IModifier) {
    throw new UnsupportedOperationException("Cannot modify checkerboards");
  }

  @Override
  public void save(String fName) throws IOException {
    System.out.println("Saving...");
    ImageUtil.writePPM("res/" + fName + ".ppm", this.toString());
    System.out.println("Done!\n");
  }
}
