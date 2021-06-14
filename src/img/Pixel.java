package img;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Pixel class represents a single pixel in an image. This class has methods that allow
 * one to set RBG values, print the pixel to add to a PPM file through toString, and apply
 * modifiers to RBG values either separately or to all channels.
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
   * @throws IllegalArgumentException is x or y are less than 0
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
   * @throws IllegalArgumentException is x or y are less than 0
   */
  public Pixel(int x, int y, Number r, Number g, Number b) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid parameters.");
    }
    this.x = x;
    this.y = y;
    this.setR(r);
    this.setG(g);
    this.setB(b);
  }

  /**
   * Sets the r value of this Pixel between 0 and 255.
   *
   * @param r inputted Number
   */
  private void setR(Number r) {
    this.r = Math.max(Math.min(r.intValue(), 255), 0);
  }

  /**
   * Sets the g value of this Pixel between 0 and 255.
   *
   * @param g inputted Number
   */
  private void setG(Number g) {
    this.g = Math.max(Math.min(g.intValue(), 255), 0);
  }

  /**
   * Sets the b value of this Pixel between 0 and 255.
   *
   * @param b inputted Number
   */
  private void setB(Number b) {
    this.b = Math.max(Math.min(b.intValue(), 255), 0);
  }

  @Override
  public String toString() {
    return r + " " + g + " " + b + "  ";
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

}
