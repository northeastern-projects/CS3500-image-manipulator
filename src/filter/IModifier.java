package filter;

import java.util.List;
import img.Image;
import img.Pixel;

/**
 * The IModifier interface contains all publicly accessible and implementable methods for modifiers.
 * Modifiers represent both filters and transforms.
 */
public interface IModifier {

  /**
   * Modify an image.
   *
   * @param image the image to be modified
   * @return the list of modified pixels after applying a transform.
   */
  List<Pixel> modify(Image image);

}
