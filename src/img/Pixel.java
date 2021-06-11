package img;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pixel {
  //coord
  public int x;
  public int y;
  //rgb
  public int r;
  public int g;
  public int b;

  public Pixel(int x, int y, List<Double> rgb) {
    this.x = x;
    this.y = y;
    this.setR(rgb.get(0));
    this.setG(rgb.get(1));
    this.setB(rgb.get(2));
  }

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

  public double applyToR(double modifier) {
    return this.r * modifier;
  }

  public double applyToG(double modifier) {
    return this.g * modifier;
  }

  public double applyToB(double modifier) {
    return this.b * modifier;
  }

  public List<Double> applyToAllChannels(double modifier) {
    return new ArrayList<>(Arrays.asList(this.applyToR(modifier), this.applyToG(modifier),
        this.applyToB(modifier)));
  }
}
