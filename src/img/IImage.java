package img;

import java.io.IOException;

import filter.IModifier;
import java.util.List;

/**
 * The IImage interface that contains all publicly implementable methods for an image.
 * An IImage can have a modifier applied to it and can be saved to a new file in p3 ppm format.
 */
public interface IImage {

  /**
   * Applies the given modifier to the current image. Only 1 modifier may be
   * applied to an image at a time, to apply more modifiers call this method repeatedly.
   *
   * @param iModifier the modifier to be applied.
   * @throws IllegalArgumentException if the modifier provided is invalid or null.
   */
  void applyFilter(IModifier iModifier) throws IllegalArgumentException;

  /**
   * This converts the image to a valid .ppm format and saves it to the /res folder with the given
   * file type.
   *
   * @param fName the file name
   * @param fType the file type
   * @throws IOException IO exception in the event that there is an error writing the file.
   */
  void save(String fName, String fType) throws IOException;

  /**
   * Returns a deep copy of the list of pixels of this image.
   *
   * @return the list of pixels
   */
  List<IPixel> getPixels();

  /**
   * Gets pixel.
   *
   * @param x the x
   * @param y the y
   * @return the pixel
   */
  IPixel getPixel(int x, int y);

  /**
   * Return a list of the properties (width, height, depth).
   *
   * @return the list of properties
   */
  List<Integer> getProps();

}
