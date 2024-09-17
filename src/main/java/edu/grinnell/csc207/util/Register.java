package edu.grinnell.csc207.util;

/**
 * A BigFraction register.
 * @author Drew Fargo
 * @version 1.0
 */
public class Register implements Lexeme {
  /** The register's name. */
  private char name;
  /** The register's value. */
  private BigFraction value;

  /**
   * Creates a register from a name and initial value.
   * @param pName The name as a character
   * @param pValue The initial value
   */
  public Register(char pName, BigFraction pValue) {
    this.name = pName;
    this.value = pValue;
  } // Register

  /**
   * Accessor for the register.
   * @return the register's value
   */
  public BigFraction get() {
    return this.value.clone();
  } // get

  /**
   * Mutator for the register.
   * @param val The new value
   */
  public void set(BigFraction val) {
    this.value = val.clone();
  } // set

  /**
   * See what we're supposed to do, then refer to register in appropriate language.
   * @param context The context
   */
  public void alterContext(BFCalculator context) {
    if (context.getDirectAddress()) {
      context.store(this.name);
    } else {
      this.value.alterContext(context);
    } // refer to direct contents
  } // alterContext
} // class Register
