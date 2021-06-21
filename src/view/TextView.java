package view;

import java.util.Scanner;

/**
 * This class represents the view of an Image model and parses any user input.
 */

public class TextView implements ITextView {

  private Scanner sc;


  /**
   * Creates a TextView object.
   */
  public TextView() {
    sc = new Scanner(System.in);
  }

  @Override
  public String getInput() {
    if (sc.hasNextLine()) {
      return sc.nextLine();
    } else {
      this.displayOutput("unable to read input");
      return null;
    }
  }

  @Override
  public void displayOutput(String output) {
    System.out.println(output);
  }
}
