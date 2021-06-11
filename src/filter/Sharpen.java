package filter;

public class Sharpen extends AFilter{

  public Sharpen() {
    super(new double[][]{
        {-1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0},
        {-1.0/8.0, 1.0/4.0, 1.0/4.0, 1.0/4.0, -1.0/8.0},
        {-1.0/8.0, 1.0/4.0, 1, 1.0/4.0, -1.0/8.0},
        {-1.0/8.0, 1.0/4.0, 1.0/4.0, 1.0/4.0, -1.0/8.0},
        {-1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0}
    });
  }

  @Override
  public String toString() {
    return "sharpen";
  }

}
