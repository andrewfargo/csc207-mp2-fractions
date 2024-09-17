package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Represents a rational number of BigIntegers.
 * @author Drew Fargo
 * @version 1.0
 */
public class BigFraction implements Lexeme {

  /** Numerator. */
  private BigInteger num;

  /** Denominator. */
  private BigInteger den;

  /**
   * Constructor for strings.
   * @param str The string of form a/b or a
   * @throws SyntaxException if str is not of proper form.
   */
  public BigFraction(String str) {
    int index = str.indexOf('/');
    if (index == -1) {
      this.num = new BigInteger(str);
      this.den = BigInteger.ONE;
      return;
    } // if
    String numStr = str.substring(0, index);
    String denStr = str.substring(index + 1);

    this.num = new BigInteger(numStr);
    this.den = new BigInteger(denStr);
    this.reduce();
  } // BigFraction

  /**
   * Constructor for whole numbers.
   * @param number A whole number.
   */
  public BigFraction(BigInteger number) {
    this(number, BigInteger.ONE);
  } // BigFraction(BigInteger)

  /**
   * Constructor for whole integers.
   * @param number A whole integer
   */
  public BigFraction(int number) {
    this(BigInteger.valueOf(number));
  } // BigFraction(int)

  /**
   * Constructor specifying numerator and denominator as BigIntegers.
   * @param numerator The numerator as a BigInteger
   * @param denominator The denominator as a BigInteger
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.den = denominator;
    this.reduce();
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Constructor specifying numerator and denominator as integers.
   * @param numerator The numerator as an integer
   * @param denominator The denominator as an integer
   */
  public BigFraction(int numerator, int denominator) {
    this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
  } // BigFraction(int, int)

  /**
   * Accessor for the numerator.
   * @return The numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // getNum

  /**
   * Accessor for the denominator.
   * @return The denominator
   */
  public BigInteger denominator() {
    return this.den;
  } // getDen

  /**
   * Reduce this BigFraction.
   */
  private void reduce() {
    BigInteger gcd = this.num.gcd(this.den);
    this.num = this.num.divide(gcd);
    this.den = this.den.divide(gcd);
    if (this.den.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.multiply(BigInteger.valueOf(-1));
      this.den = this.den.multiply(BigInteger.valueOf(-1));
    } // if
  } // reduce

  /**
   * Add val to this BigFraction.
   * @param val The addend
   * @return The sum
   */
  public BigFraction add(BigFraction val) {
    BigFraction ret;
    BigInteger newNum = this.num.multiply(val.denominator())
        .add(this.den.multiply(val.numerator()));
    BigInteger newDen = this.den.multiply(val.denominator());
    ret = new BigFraction(newNum, newDen);
    ret.reduce();
    return ret;
  } // add

  /**
   * Subtract val from this BigFraction.
   * @param val The subtrahend
   * @return The difference
   */
  public BigFraction subtract(BigFraction val) {
    val = val.multiply(new BigFraction(-1));
    return this.add(val);
  } // subtract

  /**
   * Multiply val to this BigFraction.
   * @param val The multiplier
   * @return The product
   */
  public BigFraction multiply(BigFraction val) {
    BigInteger newNum = this.num.multiply(val.numerator());
    BigInteger newDen = this.den.multiply(val.denominator());
    BigFraction ret = new BigFraction(newNum, newDen);
    ret.reduce();
    return ret;
  } // multiply

  /**
   * Divide val from this BigFraction.
   * @param val The divisor
   * @return The quotient
   */
  public BigFraction divide(BigFraction val) {
    val = new BigFraction(val.denominator(), val.numerator());
    return this.multiply(val);
  } // divide

  /**
   * Reduce the calculator if the FSM asks us to.
   * @param context the FSM context
   */
  public void alterContext(BFCalculator context) {
    context.getOperator().reduce(context, this);
  } // alterContext

  /**
   * Clone the current object.
   * @return A cloned object
   */
  public BigFraction clone() {
    return new BigFraction(this.num, this.den);
  } // clone

  /**
   * Represent this fraction as a string.
   * @return Fraction in mixed number form
   */
  public String toString() {
    String ret = "";

    ret += this.num.toString();

    if (!this.den.equals(BigInteger.ONE)) {
      ret += "/" + this.den.toString();
    } // if

    return ret;
  } // toString
} // class BigFraction
