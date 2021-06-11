package img;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Pixel class that represents a single Pixel in an image.
 */
public class Pixel {
  //coord
  public int x;
  public int y;
  //rgb
  public int r;
  public int g;
  public int b;

  /**
   * Instantiates a new Pixel given an x/y coord and a List of Doubles representing the RGB values.
   *
   * @param x   the x coord
   * @param y   the y
   * @param rgb the rgb values list
   */
  public Pixel(int x, int y, List<Double> rgb) {
    this(x, y, rgb.get(0), rgb.get(1), rgb.get(2));
  }

  /**
   * Instantiates a new Pixel given the x/y and individual R, G, and B values.
   *
   * @param x the x coord
   * @param y the y
   * @param r the r value (validated to be between 0 and max color depth)
   * @param g the g
   * @param b the b
   */
  public Pixel(int x, int y, Number r, Number g, Number b) {
    this.x = x;
    this.y = y;
    this.setR(r);
    this.setG(g);
    this.setB(b);
  }

  private void setR(Number r) {
    this.r = Math.max(Math.min(r.intValue(), 255), 0);
  }

  private void setG(Number g) {
    this.g = Math.max(Math.min(g.intValue(), 255), 0);
  }

  private void setB(Number b) {
    this.b = Math.max(Math.min(b.intValue(), 255), 0);
  }

  @Override
  public String toString() {
    return r + " " + g + " " + b + "  ";
  }

  /**
   * Apply a given modifier to the R channel of the pixel. The modifier is multiplied by the
   * current r value and is then clamped to be between 0 and max color depth.
   *
   * @param modifier the modifier to be applied
   * @return the modified R value
   */
  public double applyToR(double modifier) {
    return this.r * modifier;
  }

  /**
   * Apply the modifier to the G channel.
   *
   * @param modifier the modifier
   * @return the modified G value
   */
  public double applyToG(double modifier) {
    return this.g * modifier;
  }

  /**
   * Apply the modifier to the B channel.
   *
   * @param modifier the modifier
   * @return the modified B value
   */
  public double applyToB(double modifier) {
    return this.b * modifier;
  }

  /**
   * Apply a modifier to all channels.
   *
   * @param modifier the modifier
   * @return the list of R, G and B values after modifying each of them.
   */
  public List<Double> applyToAllChannels(double modifier) {
    return new ArrayList<>(Arrays.asList(this.applyToR(modifier), this.applyToG(modifier),
        this.applyToB(modifier)));
  }
}
