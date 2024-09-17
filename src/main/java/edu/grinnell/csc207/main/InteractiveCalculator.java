package edu.grinnell.csc207.main;

import java.util.Scanner;
import java.io.PrintWriter;
import edu.grinnell.csc207.util.Parser;

/**
 * The main class of the program.
 * Prompts the user interactively, parses the input, and calculates the result.
 * QUIT directive used to quit, STORE [a-z] directive allows for register storage.
 * @author Drew Fargo
 * @version 1.0
 */
public class InteractiveCalculator {
  /**
   * The main method of the program.
   * @param args Redundant
   */
  public static void main(String[] args) {
    Scanner lexicalScanner = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out, true);
    Parser app = new Parser(out, lexicalScanner, false);
    app.run();
  } // main
} // class InteractiveCalculator
