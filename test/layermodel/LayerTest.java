package layermodel;

import filter.Blur;
import imagemodel.IImage;
import imagemodel.IPixel;
import imagemodel.Image;
import imagemodel.Pixel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the LayerModel class which
 */
public class LayerTest {

  int height;
  int width;
  int depth;
  List<IPixel> pixels;
  List<IPixel> pixels2;
  List<IPixel> pixelsBlended;
  List<IPixel> pixelsDiffProps;
  IImage img;
  IImage img2;
  IImage img3;
  IImage diffPropsImage;
  List<IImage> realLayers;
  List<IImage> nullLayers;
  List<IImage> invalidPropsLayers;
  ILayer layer;
  String blurredImage;

  @Before
  public void setUp() {
    width = 3;
    height = 3;
    depth = 255;
    pixels = new ArrayList<>();
    pixels.add(new Pixel(0, 0, 100, 100, 100));
    pixels.add(new Pixel(0, 1, 100, 100, 100));
    pixels.add(new Pixel(0, 2, 100, 100, 100));
    pixels.add(new Pixel(1, 0, 100, 100, 100));
    pixels.add(new Pixel(1, 1, 100, 100, 100));
    pixels.add(new Pixel(1, 2, 100, 100, 100));
    pixels.add(new Pixel(2, 0, 100, 100, 100));
    pixels.add(new Pixel(2, 1, 100, 100, 100));
    pixels.add(new Pixel(2, 2, 100, 100, 100));
    pixels2 = new ArrayList<>();
    pixels2.add(new Pixel(0, 0, 0, 100, 100));
    pixels2.add(new Pixel(0, 1, 0, 100, 100));
    pixels2.add(new Pixel(0, 2, 0, 100, 100));
    pixels2.add(new Pixel(1, 0, 0, 100, 100));
    pixels2.add(new Pixel(1, 1, 0, 100, 100));
    pixels2.add(new Pixel(1, 2, 0, 100, 100));
    pixels2.add(new Pixel(2, 0, 0, 100, 100));
    pixels2.add(new Pixel(2, 1, 0, 100, 100));
    pixels2.add(new Pixel(2, 2, 0, 100, 100));

    pixelsBlended = new ArrayList<>();
    pixelsBlended.add(new Pixel(0, 0, 50, 100, 100));
    pixelsBlended.add(new Pixel(0, 1, 50, 100, 100));
    pixelsBlended.add(new Pixel(0, 2, 50, 100, 100));
    pixelsBlended.add(new Pixel(1, 0, 50, 100, 100));
    pixelsBlended.add(new Pixel(1, 1, 50, 100, 100));
    pixelsBlended.add(new Pixel(1, 2, 50, 100, 100));
    pixelsBlended.add(new Pixel(2, 0, 50, 100, 100));
    pixelsBlended.add(new Pixel(2, 1, 50, 100, 100));
    pixelsBlended.add(new Pixel(2, 2, 50, 100, 100));

    pixelsDiffProps = new ArrayList<>();
    pixelsDiffProps.add(new Pixel(0, 0, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(0, 1, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(0, 2, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(0, 3, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(1, 0, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(1, 1, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(1, 2, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(1, 3, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(2, 0, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(2, 1, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(2, 2, 0, 100, 100));
    pixelsDiffProps.add(new Pixel(2, 3, 0, 100, 100));

    img = new Image(pixels, width, height, depth);
    img2 = new Image(pixels2, width, height, depth);
    img3 = new Image(pixels, width, height, depth);
    diffPropsImage = new Image(pixelsDiffProps, width, 4, depth);

    realLayers = new ArrayList<>(Arrays.asList(img, img2));
    nullLayers = null;
    invalidPropsLayers = new ArrayList<>(Arrays.asList(img, img2, img3, diffPropsImage));
    layer = new Layer(realLayers, width, height, depth);
    blurredImage = "P3\n" +
            "3\n" +
            "3\n" +
            "255\n" +
            "56 56 56  75 75 75  56 56 56  75 75 75  100 100 100  "
            + "75 75 75  56 56 56  75 75 75  56 56 56  \n";
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullListOfImages() {
    new Layer(nullLayers, width, height, depth);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListOfImagesWithDiffProperties() {
    new Layer(invalidPropsLayers, width, height, depth);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addLayerWithDiffProperties() {
    layer.addLayer(diffPropsImage);
  }

  @Test
  public void addLayer() {
    List<Integer> props = layer.getProps();
    assertEquals("2", props.get(0).toString());
    layer.addLayer(img3);
    props = layer.getProps();
    assertEquals("3", props.get(0).toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetLayerOutOfBounds() {
    layer.getLayer(20);
  }

  @Test
  public void testGetLayer() {
    layer.addLayer(img3);
    assertEquals(img3, layer.getLayer(2));
  }

  @Test
  public void testBlendAllImages() {
    assertEquals(new Image(pixelsBlended, width, height, depth).toString(),
            layer.blend().toString());
  }

  @Test
  public void testBlendWhenAllInvisible() {
    layer.toggleVisibility(0);
    layer.toggleVisibility(1);
    assertEquals("P3\n3\n3\n255\n0 0 0  0 0 0  0 0 0  0 0 0  0 0 0  0 0 0  "
                    + "0 0 0  0 0 0  0 0 0  \n",
            layer.blend().toString());
  }

  @Test
  public void testBlendWhenOnlyOneVisible() {
    layer.toggleVisibility(0);
    assertEquals(img2.toString(), layer.blend().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentOutofBounds() {
    layer.setCurrent(25);
  }
  @Test
  public void testSetCurrent() {
    assertEquals("0", layer.getProps().get(4).toString());
    layer.setCurrent(1);
    assertEquals("1", layer.getProps().get(4).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvisibilityOutofBounds() {
    layer.toggleVisibility(25);
  }
  @Test
  public void testSetInvisibility() {
    assertEquals(2, layer.getVisible().size());
    layer.toggleVisibility(0);
    assertEquals(1, layer.getVisible().size());
    layer.toggleVisibility(1);
    assertEquals(0, layer.getVisible().size());
  }

  @Test
  public void testGetVisible() {
    assertEquals(realLayers, layer.getVisible());
    layer.toggleVisibility(1);
    assertEquals(new ArrayList<>(Arrays.asList(img)).toString(), layer.getVisible().toString());
  }

  @Test
  public void testApplyToCurrent() {
    assertEquals(img.toString(), layer.getLayer(0).toString());
    layer.applyToCurrent(new Blur());
    assertEquals(blurredImage, layer.getLayer(0).toString());
  }

  @Test
  public void testToString() {
    layer.toggleVisibility(1);
    layer.applyToCurrent(new Blur());
    assertEquals("LAYER\n1\n3\n3\n255\n\n" + blurredImage + "\n", layer.toString());
  }

  @Test
  public void testGetProps() {
    List<Integer> props = new ArrayList<>(Arrays.asList(2, 3, 3, 255, 0));
    assertEquals(props.toString(), layer.getProps().toString());
    layer.setCurrent(1);
    props = new ArrayList<>(Arrays.asList(2, 3, 3, 255, 1));
    assertEquals(props.toString(), layer.getProps().toString());
  }
}
