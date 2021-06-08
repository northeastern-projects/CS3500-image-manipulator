package src.filter;

import java.util.List;
import java.util.Map;

public interface IFilter {

  Map<List<Integer>, List<Integer>> modify(Map<List<Integer>, List<Integer>> pixels);

}
