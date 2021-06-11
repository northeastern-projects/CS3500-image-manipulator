package img;

import java.io.IOException;
import filter.IModifier;

/**
 * The Image interface that contains all publicly implementable methods for an image.
 */
public interface IImage {

  /**
   * Apply filter. Applies the given modifier to the current image. Only 1 modifier may be
   * applied to an image at a time, to apply more modifiers call this method repeatedly.
   *
   * @param IModifier the modifier to be applied.
   */
  void applyFilter(IModifier IModifier);

  /**
   * Save the image. This converts the image to a valid .ppm format and saves it to the /res
   * folder.
   *
   * @param fName the file name
   * @throws IOException IO exception in the event that there is an error writing the file.
   */
  void save(String fName) throws IOException;

}
