package Controller;

import java.io.IOException;

/**
 * The IController interface creates an implementation that will allow for the handling of user
 * input, the saving of a state as a file, and the loading of a state based on the file type.
 */
public interface IController {

  void handleInput(String input);

  void saveState(String stateName) throws IOException;

  void loadState(String stateName);

}
