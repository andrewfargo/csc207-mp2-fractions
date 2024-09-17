package edu.grinnell.csc207.util;

/**
 * All operators valid to the calculator.
 * @author Drew Fargo
 * @version 1.0
 */
public enum Operator implements Reducer {
  /** Addition. */
  PLUS {
    /**
     * Reduce by adding to context.
     * @param context The calculator
     * @param val The value to add
     */
    public void reduce(BFCalculator context, BigFraction val) {
      context.add(val);
    } // reduce
    /**
     * If we run into a plus sign, this is what we do.
     * @param context The calculator
     * @pre This lexeme is valid in context.
     */
    public void alterContext(BFCalculator context) {
      context.setOperator(Operator.PLUS);
    } // alterContext
  }, // PLUS
  /** Subtraction. */
  MINUS {
    /**
     * Reduce by subtracting from context.
     * @param context The calculator
     * @param val The value
     */
    public void reduce(BFCalculator context, BigFraction val) {
      context.subtract(val);
    } // reduce
    /**
     * If we run into a minus sign, this is what we do.
     * @param context The context
     * @pre This lexeme is valid in context.
     */
    public void alterContext(BFCalculator context) {
      context.setOperator(Operator.MINUS);
    } // alterContext
  }, // MINUS
  /** Multiplication. */
  STAR {
    /**
     * Reduce by multiplying context.
     * @param context The calculator
     * @param val The multiple
     */
    public void reduce(BFCalculator context, BigFraction val) {
      context.multiply(val);
    } // reduce
    /**
     * If we run into a star, this is what we do.
     * @param context The context
     * @pre This lexeme is valid in context.
     */
    public void alterContext(BFCalculator context) {
      context.setOperator(Operator.STAR);
    } // alterContext
  }, // STAR
  /** Division. */
  SLASH {
    /**
     * Reduce by dividing from context.
     * @param context The calculator
     * @param val The dividend
     */
    public void reduce(BFCalculator context, BigFraction val) {
      context.divide(val);
    } // reduce
    /**
     * If we run into a slash, this is what we do.
     * @param context The context
     * @pre This lexeme is valid in context.
     */
    public void alterContext(BFCalculator context) {
      context.setOperator(Operator.SLASH);
    } // alterContext
  } // SLASH
} // enum Operator
