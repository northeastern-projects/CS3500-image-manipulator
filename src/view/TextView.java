package view;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the view of an Image model and parses any user input.
 */

public class TextView implements ITextView {

  private Scanner sc;
  private Readable rd;
  private Appendable ap;

  public TextView(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
    sc = new Scanner(rd);
  }

  @Override
  public String getInput() throws IOException {
    this.displayOutput("Enter input: ");
    if (sc.hasNextLine()) {
      return sc.nextLine();
    } else {
      this.displayOutput("unable to read input");
      return null;
    }
  }

  @Override
  public void displayOutput(String output) throws IOException {
    this.ap.append(output);
  }
}
