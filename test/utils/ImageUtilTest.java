package utils;

import filter.Sepia;
import img.Checkerboard;
import img.IImage;
import img.Image;
import img.Pixel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class tests utility methods that read a PPM image from file and create an {@link IImage}
 * and write a file with output-name and file-contents to another ppm file.
 */
public class ImageUtilTest {
  ImageUtil ut;
  List<Pixel> pixels;
  IImage img;
  IImage checkerboard;
  String imgFile;
  String imgFileSepia;
  String checkerboardFile;

  @Before
  public void initData() {
    ut = new ImageUtil();

    pixels = new ArrayList<Pixel>();
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
    checkerboard = new Checkerboard(3, 3, 255);

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

  @Test
  public void testExportedImageContents() throws IOException {
    //in res folder we have an image that we created of the sepia version of img
    //here we are making sure that that read and write produce the correct string
    //also testing that checkerboard also returns the correct output
    assertNotEquals(imgFile, ImageUtil.readPPM("res/sepia3x3.ppm").toString());
    img.applyFilter(new Sepia());
    assertEquals(imgFileSepia, ImageUtil.readPPM("res/sepia3x3.ppm").toString());
    assertEquals(checkerboardFile, ImageUtil.readPPM("res/check.ppm").toString());
  }
}