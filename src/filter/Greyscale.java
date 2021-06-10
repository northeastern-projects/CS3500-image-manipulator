package src.filter;

public class Greyscale extends ATransform{
  public Greyscale() {
    super(new float[][] {
        {0.2126f, 0.7152f, 0.0722f},
        {0.2126f, 0.7152f, 0.0722f},
        {0.2126f, 0.7152f, 0.0722f}
    });
  }
}
