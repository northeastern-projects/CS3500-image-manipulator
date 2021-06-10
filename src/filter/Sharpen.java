package src.filter;

public class Sharpen extends AFilter{

  public Sharpen() {
    super(new float[][]{
        {-1/8, -1/8, -1/8, -1/8, -1/8},
        {-1/8, 1/4, 1/4, 1/4, -1/8},
        {-1/8, 1/4, 1, 1/4, -1/8},
        {-1/8, 1/4, 1/4, 1/4, -1/8},
        {-1/8, -1/8, -1/8, -1/8, -1/8}
    });
  }

}
