package edu.grinnell.csc207.util;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * The calculator computer class. Governs registers, program state.
 * @author Drew Fargo
 * @version 1.0
 */
public class BFCalculator {
  /** Set of registers a-z. */
  private BFRegisterSet storageRegisters;
  /** Accumulator register. */
  private Register acc;
  /** Which operator is in the current FSM state. */
  private Operator op;
  /** State of direct addressing. */
  private boolean directAddress;
  /** State of input acceptance. */
  private boolean shouldRun;

  /** Calculator history as text. */
  private ArrayList<String> history;

  /** Previous result. */
  private BigFraction pushBackVal;

  /**
   * Constructor of the calculator.
   * Registers and inputs are not shared.
   */
  public BFCalculator() {
    this.storageRegisters = new BFRegisterSet();
    this.op = Operator.PLUS;
    this.acc = new Register('_', new BigFraction(BigInteger.ZERO));
    this.directAddress = true;
    this.shouldRun = true;
    this.history = new ArrayList<String>();
  } // BFCalculator

  /**
   * Get the contents of the accumulator register.
   * @return Value of the accumulator register.
   */
  public BigFraction get() {
    return this.acc.get();
  } // get

  /**
   * Add val to the accumulator register.
   * @param val Value of the addend.
   */
  public void add(BigFraction val) {
    this.acc.set(this.acc.get().add(val));
  } // add

  /**
   * Subtract val to the accumulator register.
   * @param val Value of the fraction to be subtracted.
   */
  public void subtract(BigFraction val) {
    this.acc.set(this.acc.get().subtract(val));
  } // subtract

  /**
   * Multiply val to the accumulator register.
   * @param val The multiplier
   */
  public void multiply(BigFraction val) {
    this.acc.set(this.acc.get().multiply(val));
  } // multiply

  /**
   * Divide the accumulator register by val.
   * @param val The divisor
   */
  public void divide(BigFraction val) {
    this.acc.set(this.acc.get().divide(val));
  } // divide

  /**
   * Zero the accumulator register.
   */
  public void clear() {
    this.pushBackVal = this.acc.get();
    this.acc.set(new BigFraction(0));
    this.setOperator(Operator.PLUS);
    this.directAddress = false;
  } // clear

  /**
   * Store the pushback value to register `reg`.
   * @param reg a-z representing the register to store to.
   */
  public void store(char reg) {
    this.storageRegisters.store(reg, this.pushBackVal);
  } // store

  /**
   * Get the value stored in the register `reg`.
   * @param reg a-z representing the register to pull from.
   * @return A reference to the register object
   */
  public Register getRegister(char reg) {
    return this.storageRegisters.getReference(reg);
  } // getRegister

  /**
   * Indicate that the parser should cease.
   */
  public void stopRunning() {
    this.shouldRun = false;
  } // stopRunning

  /**
   * Accessor for the calculator's running state.
   * @return true if the calculator is still working.
   */
  public boolean getRunning() {
    return this.shouldRun;
  } // getRunning

  /**
   * Mutator for the FSM operator.
   * @param newOp The operator.
   */
  public void setOperator(Operator newOp) {
    this.op = newOp;
  } // setOperator

  /**
   * Accessor for the FSM operator.
   * @return The operator
   */
  public Operator getOperator() {
    return this.op;
  } // getOperator

  /**
   * Accessor for direct addressing state.
   * @return true if we shall refer to the register itself.
   */
  public boolean getDirectAddress() {
    return this.directAddress;
  } // getDirectAddress

  /**
   * Mutator for the direct addressing state.
   * @param flag The new state
   */
  public void setDirectAddress(boolean flag) {
    this.directAddress = flag;
  } // setDirectAddress

  /**
   * Accessor for calculator history.
   * @return The calculator history as a mutable arraylist.
   */
  public ArrayList<String> getHistory() {
    return this.history;
  } // getHistory

} // class BFCalculator
