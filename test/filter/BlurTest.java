package filter;

import img.Pixel;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class BlurTest extends AFilterTest {
  List<Pixel> pixels;
  List<Pixel> pixelsBlurred;

  @Before
  public void initData() {
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

    pixelsBlurred = new ArrayList<Pixel>();
    pixelsBlurred.add(new Pixel(0, 0, 56, 56, 56));
    pixelsBlurred.add(new Pixel(0, 1, 75, 75, 75));
    pixelsBlurred.add(new Pixel(0, 2, 56, 56, 56));
    pixelsBlurred.add(new Pixel(1, 0, 75, 75, 75));
    pixelsBlurred.add(new Pixel(1, 1, 100, 100, 100));
    pixelsBlurred.add(new Pixel(1, 2, 75, 75, 75));
    pixelsBlurred.add(new Pixel(2, 0, 56, 56, 56));
    pixelsBlurred.add(new Pixel(2, 1, 75, 75, 75));
    pixelsBlurred.add(new Pixel(2, 2, 56, 56, 56));
  }

  @Override
  public IModifier objectCreator() {
    return new Blur();
  }

  @Override
  public AFilter objectFilterCreator() {
    return new Blur();
  }
}