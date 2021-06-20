package Controller;

import java.io.IOException;
import java.util.List;

public interface IController {

  void handleInput(String input);

  void saveState(String stateName) throws IOException;

  void loadState(String stateName);

}
