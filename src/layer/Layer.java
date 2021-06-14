package layer;

import img.IImage;
import img.Image;
import img.Pixel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.ImageUtil;

public class Layer implements ILayer{

  List<IImage> layers;
  int height;
  int width;
  int depth;

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
    if (!this.getLayerInfo().equals(image.getProps())) {
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
  public List<Integer> getLayerInfo() {
    return new ArrayList<>(Arrays.asList(width, height, depth));
  }

  @Override
  public IImage blend() {
    System.out.println("Blending...");
    List<Pixel> pixels = new ArrayList<>();

    for (int j = 0 ; j < height; j++) {
      for (int i = 0; i < width; i++) {
        double avgR = 0.0;
        double avgG = 0.0;
        double avgB = 0.0;

        for (IImage layer : this.layers) {
          Pixel p = layer.getPixel(i, j);
          avgR += p.r;
          avgG += p.g;
          avgB += p.b;
        }

        pixels.add(new Pixel(i, j, avgR / this.layers.size(), avgG / this.layers.size(),
            avgB / this.layers.size()));
      }
    }

    return new Image(pixels, width, height, depth);
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
  public void save(String fName) throws IOException {
    System.out.println("Saving...");
    ImageUtil.writePPM("res/" + fName + ".dat", this.toString());
    System.out.println("Done!\n");
  }
}