package layermodel;

import imagemodel.IImage;
import java.util.List;

public interface IROLayer {

  /**
   * returns the IImage of the layer corresponding to the index.
   *
   * @param index that corresponds to the layer
   * @return an IImage of the layer
   * @throws IllegalArgumentException if index is out of bounds
   */
  IImage getLayer(int index) throws IllegalArgumentException;

  /**
   * Produces the IImage at the current index.
   *
   * @return the current IImage
   * @throws IllegalArgumentException if index is out of bounds
   */
  IImage getCurrent() throws IllegalArgumentException;

  /**
   * An observer for all visible images in this ILayer.
   * @return A list of IImage
   */
  List<IImage> getVisible();

  /**
   * Creates a list of the number of layers, width, height, depth, and current index of the ILayer.
   *
   * @return a list of Integers
   */
  List<Integer> getProps();

  boolean hasCurrent();

}
