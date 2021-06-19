package ImageView;

import ImageController.ImageController;
import filter.Sepia;
import ImageModel.Checkerboard;
import ImageModel.IImage;
import ImageModel.IPixel;
import ImageModel.Image;
import ImageModel.Pixel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class tests utility methods that read a PPM image from file and create an {@link IImage}
 * and write a file with output-name and file-contents to another ppm file.
 */
public class ImageUtilTest {
  ImageController ut;
  List<IPixel> pixels;
  IImage img;
  IImage checkerboard;
  String imgFile;
  String imgFileSepia;
  String checkerboardFile;

  @Before
  public void initData() {
    ut = new ImageController();

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

    img = new Image(pixels, 3, 3, 255);
    checkerboard = new Checkerboard(3, 3, 255, 1);

    imgFile = "P3\n"
            + "3\n"
            + "3\n"
            + "255\n"
            + "100 100 100  100 100 100  100 100 100  100 100 100  100 100 100  "
            + "100 100 100  100 100 100  100 100 100  100 100 100  \n";

    imgFileSepia = "P3\n"
            + "3\n"
            + "3\n"
            + "255\n"
            + "135 120 93  135 120 93  135 120 93  135 120 93  135 120 93  "
            + "135 120 93  135 120 93  135 120 93  135 120 93  \n";

    checkerboardFile = "P3\n"
            + "3\n"
            + "3\n"
            + "255\n"
            + "0 0 0  255 255 255  0 0 0  255 255 255  0 0 0  "
            + "255 255 255  0 0 0  255 255 255  0 0 0  \n";
  }


}