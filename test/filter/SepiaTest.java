package filter;

public class SepiaTest extends ATransformTest {

  @Override
  public IModifier objectCreator() {
    return new Sepia();
  }

  @Override
  public ATransform objectTransformCreator() {
    return new Sepia();
  }
}