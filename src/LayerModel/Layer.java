package LayerModel;

import ImageModel.IImage;
import ImageModel.IPixel;
import ImageModel.Image;
import ImageModel.Pixel;
import FilterModel.IModifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer implements ILayer {

  //think we should change this to hashmap of IImage, boolean
  // to tell if this IIMage is visible - true or false
  private final List<IImage> layers;
  private int current;
  private final int height;
  private final int width;
  private final int depth;

  public Layer(List<IImage> images, List<Integer> props) {
    this(images, props.get(0), props.get(1), props.get(2));
  }

  public Layer(List<IImage> images, int width, int height, int depth) {
    if (images == null || !this.isValidImages(images)) {
      throw new IllegalArgumentException("Invalid list of images provided");
    }
    this.layers = images;
    this.height = height;
    this.width = width;
    this.depth = depth;
    this.current = 0;
  }

  private boolean canAcceptImage(IImage image) {
    return this.width == image.getProps().get(0)
        && this.height == image.getProps().get(1)
        && this.depth == image.getProps().get(2);
  }

  private boolean isValidImages(List<IImage> images) {
    if (images.size() == 0) {
      return true;
    }

    List<Integer> props = images.get(0).getProps();
    for (IImage img : images) {
      if (!img.getProps().equals(props)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addLayer(IImage image) {
    if (!canAcceptImage(image)) {
      throw new IllegalArgumentException("Image must have same properties as the one in the layer"
          + " below");
    }
    this.layers.add(image);
  }

  @Override
  public IImage getLayer(int index) {
    if (index < 0 || index > this.layers.size()) {
      throw new IllegalArgumentException("Invalid index provided");
    }
    return this.layers.get(index);
  }

  @Override
  public IImage blend() {
    System.out.println("Blending...");
    List<IPixel> pixels = new ArrayList<>();

    for (int j = 0 ; j < height; j++) {
      for (int i = 0; i < width; i++) {
        double avgR = 0.0;
        double avgG = 0.0;
        double avgB = 0.0;

        for (IImage layer : this.layers) {
          IPixel p = layer.getPixel(i, j);
          avgR += p.getColor().get(0);
          avgG += p.getColor().get(1);
          avgB += p.getColor().get(2);
        }

        pixels.add(new Pixel(i, j, avgR / this.layers.size(), avgG / this.layers.size(),
            avgB / this.layers.size()));
      }
    }

    return new Image(pixels, width, height, depth);
  }

  @Override
  public void setCurrent(int index) {
    if (index > 0 && index < this.layers.size()) {
      this.current = index;
    }
  }

  @Override
  public void applyToCurrent(IModifier modifier) {
    this.layers.get(current).applyFilter(modifier);
  }

  @Override
  public String toString() {
    StringBuilder matrix = new StringBuilder("LAYER\n" + this.layers.size() + "\n" + width + "\n"
        + height + "\n" + depth + "\n");
    for (IImage img : this.layers) {
      matrix.append("\n").append(img.toString());
    }
    return matrix + "\n";
  }

  @Override
  public List<Integer> getProps() {
    return new ArrayList<>(Arrays.asList(this.layers.size(), this.height, this.width, this.depth));
  }
}
