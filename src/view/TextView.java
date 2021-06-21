package view;

import java.util.Scanner;

public class TextView implements ITextView {

  private Scanner sc;

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
