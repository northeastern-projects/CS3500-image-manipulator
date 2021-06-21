package view;

import java.io.IOException;

/**
 * This interface outlines the basic things that the view should parsing commands. IView allows for
 * interactive and file-based textual scripting.
 */
public interface ITextView {

  String getInput() throws IOException;

  void displayOutput(String output) throws IOException;

}
