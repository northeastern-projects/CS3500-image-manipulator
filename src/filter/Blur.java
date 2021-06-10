package src.filter;

public class Blur extends AFilter{

  public Blur() {
    super(new float[][]{
        {1/16, 1/8, 1/16},
        {1/8, 1/4, 1/8},
        {1/16, 1/8, 1/16}
    });
  }

}
