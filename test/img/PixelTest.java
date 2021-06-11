package img;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PixelTest {
  Pixel random;
  Pixel white;
  Pixel black;
  List<Double> result;

  @Before
  public void setUp() {
    random = new Pixel(3, 3, 40, 30, 112);
    white = new Pixel(5, 12, 255, 255, 255);
    black = new Pixel(12, 4, 0, 0, 0);
    result = new ArrayList<Double>(Arrays.asList(12.0, 9.0, 33.6));
  }

  @Test
  public void testToString() {
    assertEquals("40 30 112  ", random.toString());
    assertEquals("0 255 34  ", new Pixel(12, 1, -3, 1234, 34).toString());
  }

  @Test
  public void testApplyToR() {
    assertEquals(140011.6, random.applyToR(3500.29), 0.001);
    assertEquals(0.0, black.applyToR(123.123123), 0.001);
  }

  @Test
  public void testApplyToG() {
    assertEquals(105008.7, random.applyToG(3500.29), 0.001);
    assertEquals(0.0, black.applyToR(123.123123), 0.001);
  }

  @Test
  public void testApplyToB() {
    assertEquals(392032.48, random.applyToB(3500.29), 0.001);
    assertEquals(0.0, black.applyToR(123.123123),0.001);
  }

  @Test
  public void testApplyToAllChannels() {
    assertEquals(result, random.applyToAllChannels(0.3));
    assertEquals(new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0)),
            black.applyToAllChannels(123.123123));
  }
}