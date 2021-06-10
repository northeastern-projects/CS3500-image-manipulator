package src.img;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pixel {
  //coord
  public int x;
  public int y;
  //rgb
  public int r;
  public int g;
  public int b;

  public Pixel(int x, int y) {
    this.x = x;
    this.y = y;
    this.setR(0);
    this.setG(0);
    this.setB(0);
  }

  public Pixel(int x, int y, Number r, Number g, Number b) {
    this.x = x;
    this.y = y;
    this.setR(r);
    this.setG(g);
    this.setB(b);
  }


  private void setR(Number r) {
    this.r = Math.min(r.intValue(), 255);
    this.r = Math.max(r.intValue(), 0);
  }

  private void setG(Number g) {
    this.g = Math.min(g.intValue(), 255);
    this.g = Math.max(g.intValue(), 0);
  }

  private void setB(Number b) {
    this.b = Math.min(b.intValue(), 255);
    this.b = Math.max(b.intValue(), 0);
  }

  @Override
  public String toString() {
    return "I am pixel at: " + x + ", " + y + " with color: (" + r + ", " + g + ", " + b + ") and"
        + " I am so pretty!";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return x == pixel.x && y == pixel.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public List<Pixel> getNeighbours(List<Pixel> pixels, int scope) {
    List<Pixel> neighbours = new ArrayList<>();
    List<Integer> map = new ArrayList<>();
    int key = (scope - 1) / 2;

    for (int i = -1 * key; i <= key; i++) {
      for (int j = -1 * key; j <= key; j++) {
        int idx = (pixels.indexOf(new Pixel(this.x - j, this.y - i))); //if weird results swap
        // these indices
        neighbours.add(idx < 0 ? null : pixels.get(idx));
      }
    }

    return neighbours;
  }
}
