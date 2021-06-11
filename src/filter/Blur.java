package filter;

/**
 * The Blue filter.
 */
public class Blur extends AFilter{

  /**
   * Instantiates a new Blur kernel. This kernel is known to be valid.
   */
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
