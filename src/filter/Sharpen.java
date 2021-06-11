package filter;

/**
 * The Sharpen filter.
 */
public class Sharpen extends AFilter{

  /**
   * Instantiates a new Sharpen filter with a kernel that is known to be valid.
   */
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
