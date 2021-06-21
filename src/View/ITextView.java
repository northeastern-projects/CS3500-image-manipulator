package View;

/**
 * This interface outlines the basic things that the view should parsing commands. IView allows for
 * interactive and file-based textual scripting.
 */
public interface ITextView {

  String getInput();

  void displayOutput(String output);

}
