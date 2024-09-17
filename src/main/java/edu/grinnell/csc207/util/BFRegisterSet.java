package edu.grinnell.csc207.util;
import java.math.BigInteger;

/**
 * A set of 26 alphabetical registers.
 * @author Drew Fargo
 * @version 1.0
 */
public final class BFRegisterSet {
  /** How many letters are in the alphabet. */
  private static final int NUMALPHABET = 26;
  /** The register array. */
  private Register[] registers;

  /** Create 26 unique registers. */
  public BFRegisterSet() {
    this.registers = new Register[NUMALPHABET];
    for (int i = 0; i < NUMALPHABET; i++) {
      BigFraction zero = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
      this.registers[i] = new Register((char) ('a' + i), zero);
    } // for
  } // BFRegisterSet

  /**
   * Get the value of the register `c`.
   * @param c Character of the register to pull from.
   * @return The value in register `c`
   */
  public BigFraction get(char c) {
    return this.getReference(c).get();
  } // get

  /**
   * Get the Register object of the `c`.
   * @param c Character of the register
   * @return The register object
   */
  public Register getReference(char c) {
    return this.registers[(int) (c - 'a')];
  } // getReference

  /**
   * Store the value `val` into register `c`.
   * Equivalent to .getReference(c).set(val)
   * @param c The register character to store to
   * @param val The value to be stored
   */
  public void store(char c, BigFraction val) {
    this.registers[(int) (c - 'a')].set(val);
  } // store
} // class BFRegisterSet
