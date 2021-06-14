package img;

import java.util.ArrayList;
import java.util.List;

/**
 * The Checkerboard image contains a representation of a simple checkerboard image.
 * It contains all methods needed to create a black and white checkerboard pattern.
 * implements the IImage interface.
 */
public class Checkerboard extends Image {

  /**
   * Instantiates a new Checkerboard image.
   *
   * @param width  the width of the image
   * @param height the height
   * @param depth  the color depth (normally 255)
   * @throws IllegalArgumentException if width, height, or depth are less than 0
   */
  public Checkerboard(int width, int height, int depth) {
    super(generateCheckerboard(width, height, depth), width, height, depth);
  }

  private static List<Pixel> generateCheckerboard(int width, int height, int depth) {
    List<Pixel> pixels = new ArrayList<>();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if ((i + j) % 2 == 0) {
          pixels.add(new Pixel(i, j, 0, 0, 0));
        } else {
          pixels.add(new Pixel(i, j, depth, depth, depth));
        }
      }
    }
    return pixels;
  }
}
