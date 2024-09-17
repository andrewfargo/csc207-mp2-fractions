package edu.grinnell.csc207.util;

/**
 * Class of operators that can reduce.
 * @author Drew Fargo
 * @version 1.0
 */
public interface Reducer extends Lexeme {
  /**
   * Reduce the context by the value.
   * @param context The calculator that should be reduced
   * @param val The value to reduce it by
   */
  void reduce(BFCalculator context, BigFraction val);
} // interface Reducer
