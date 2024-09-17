package edu.grinnell.csc207.util;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The program's parser, governs program state.
 * @author Drew Fargo
 * @version 1.0
 */
public class Parser {
  /** The output writer. */
  private PrintWriter writer;
  /** Whether the program is to run in quiet mode. */
  private boolean quiet;
  /** The lexer. */
  private Lexer lexer;

  /** The workhorse of the parser. */
  private BFCalculator calculator;

  /**
   * App constructor.
   * @param outWriter Output writer
   * @param lexScanner Lexical scanner to use
   * @param isQuiet Should be true if scanner is automated
   */
  public Parser(PrintWriter outWriter, Scanner lexScanner, boolean isQuiet) {
    this.quiet = isQuiet;
    this.writer = outWriter;
    this.calculator = new BFCalculator();
    PrintWriter lexWriter = this.quiet ? null : this.writer;
    this.lexer = new Lexer(this.calculator, lexScanner, lexWriter);
  } // Parser

  /**
   * Run the calculator until a quit directive is hit.
   */
  public void run() {
    while (this.calculator.getRunning()) {
      Lexeme next;
      try {
        next = this.lexer.nextToken();
      } catch (SyntaxException e) {
        System.err.println("Syntax error");
        this.calculator.setDirectAddress(true);
        continue;
      } // try
      next.alterContext(this.calculator);
    } // while

    if (quiet) {
      for (String result : this.calculator.getHistory()) {
        this.writer.println(result);
      } // for
    } // if
  } // run
} // class Parser
