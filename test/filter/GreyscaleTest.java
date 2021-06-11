package filter;


public class GreyscaleTest extends ATransformTest {

  @Override
  public IModifier objectCreator() {
    return new Greyscale();
  }

  @Override
  public ATransform objectTransformCreator() {
    return new Greyscale();
  }
}