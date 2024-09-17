package edu.grinnell.csc207.main;

import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import edu.grinnell.csc207.util.Parser;

/**
 * The main class of the QuickCalculator program.
 * Takes the arguments and treats them as individual lines of
 * input into the calculator. Automatically appends a quit
 * directive to the end of input.
 * @author Drew Fargo
 * @version 1.0
 */
public class QuickCalculator {
  /**
   * The main method of the program.
   * @param args Each argument is a line fed into the program.
   */
  public static void main(String[] args) {
    List<String> argList = Arrays.asList(args);

    String input = String.join("\n", argList);
    input += "\nQUIT\n";
    Scanner scanner = new Scanner(input);
    PrintWriter out = new PrintWriter(System.out, true);
    Parser app = new Parser(out, scanner, true);
    app.run();
  } // main
} // class QuickCalculator
