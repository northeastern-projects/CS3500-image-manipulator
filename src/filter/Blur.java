package filter;

public class Blur extends AFilter{

  public Blur() {
    super(new double[][]{
        {1.0/16.0, 1.0/8.0, 1.0/16.0},
        {1.0/8.0, 1.0/4.0, 1.0/8.0},
        {1.0/16.0, 1.0/8.0, 1.0/16.0}
    });
  }

  @Override
  public String toString() {
    return "blur";
  }
}
