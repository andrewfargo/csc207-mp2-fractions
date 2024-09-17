package edu.grinnell.csc207.util;

/**
 * Represents a valid token of input for the Lexer to process.
 * @author Drew Fargo
 * @version 1.0
 */
public interface Lexeme {
  /**
   * What this lexeme will do to the context.
   * @param context
   */
  void alterContext(BFCalculator context);
} // interface Lexeme
