package view;

/**
 * This interface outlines the basic things that the view should parsing commands. IView allows for
 * interactive and file-based textual scripting.
 */
public interface ITextView {

  /**
   * Returns a string of user input.
   *
   * @return String
   */
  String getInput();

  /**
   * Prints the string that is given.
   *
   * @param output String
   */
  void displayOutput(String output);

}
