package filter;

public class Greyscale extends ATransform{
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