package layermodel;

import imagemodel.IImage;
import filter.IModifier;

import java.util.List;

/**
 * An {@code ILayer} is an interface that represents multiple {@link IImage} that can be edited.
 * This interface supplies methods that allow the addition of layers, the ability to get an
 * {@link IImage} from the corresponding layer, blend the layers to create one {@link IImage},
 * the setting of a layer as a current one to be edited, the ability to set a layer as invisible,
 * the ability to apply an {@link IModifier} to a layer, and the ability to get the properties
 * of a layer.
 */
public interface ILayer {

  /**
   * Adds an image as a layer to this ILayer.
   *
   * @param image to be added to the exisiting layers
   * @throws IllegalArgumentException if the image properties do not match those of the layers
   */
  void addLayer(IImage image) throws IllegalArgumentException;

  /**
   * returns the IImage of the layer corresponding to the index.
   *
   * @param index that corresponds to the layer
   * @return an IImage of the layer
   * @throws IllegalArgumentException if index is out of bounds
   */
  IImage getLayer(int index) throws IllegalArgumentException;

  /**
   * Blends all visible IImages.
   *
   * @return an IImage of this ILayer
   */
  IImage blend();

  /**
   * Sets the layer at the given index as the current one.
   *
   * @param index layer that one wants to make the current one
   * @throws IllegalArgumentException if index is out of bounds
   */
  void setCurrent(int index) throws IllegalArgumentException;

  IImage getCurrent() throws IllegalArgumentException;

  /**
   * Sets the layer at the index to invisible.
   *
   * @param index corresponds to the layer than one wants to be invisible
   * @throws IllegalArgumentException if index is out of bounds
   */
  void toggleVisibility(int index) throws IllegalArgumentException;

  /**
   * An observer for all visible images in this ILayer.
   * @return A list of IImage
   */
  List<IImage> getVisible();

  /**
   * Applies the IModifier to the current layer.
   *
   * @param modifier that one wants to apply to the layer
   */
  void applyToCurrent(IModifier modifier);

  /**
   * Creates a list of the number of layers, width, height, depth, and current index of the ILayer.
   *
   * @return a list of Integers
   */
  List<Integer> getProps();

  void removeLayer(int index) throws IllegalArgumentException;

}
