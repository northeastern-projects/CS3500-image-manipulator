package filter;

/**
 * The Greyscale transform.
 */
public class Greyscale extends ATransform{

  /**
   * Instantiates a new Greyscale object with a valid kernel.
   */
  public Greyscale() {
    super(new double[][] {
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}
    });
  }

  @Override
  public String toString() {
    return "greyscale";
  }
}
