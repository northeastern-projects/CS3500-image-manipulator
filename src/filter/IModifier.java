package filter;

import java.util.List;

import img.Image;
import img.Pixel;

/**
 * The IModifier interface contains all publicly accessible and implementable methods for modifiers.
 * Modifiers represent both filters and transformers.
 */
public interface IModifier {

  /**
   * Modify an image with this IModifier.
   *
   * @param image the image to be modified
   * @return the list of modified pixels after applying a modifier.
   */
  List<Pixel> modify(Image image);

}
