package src.img;

import src.filter.IFilter;

public interface IImage {

  void applyFilter(IFilter IFilter);
  void print();

}
