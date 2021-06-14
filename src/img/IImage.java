package img;

import java.io.IOException;

import filter.IModifier;

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
   */
  void applyFilter(IModifier iModifier);

  /**
   * This converts the image to a valid .ppm format and saves it to the /res
   * folder.
   *
   * @param fName the file name
   * @throws IOException IO exception in the event that there is an error writing the file.
   */
  void save(String fName) throws IOException;

}
