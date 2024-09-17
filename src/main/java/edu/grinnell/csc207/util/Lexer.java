package edu.grinnell.csc207.util;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.function.Predicate;

/**
 * My not-so-great implementation of a lexer.
 * @author Drew Fargo
 * @version 1.0
 */
class Lexer {
  /** The text scanner. */
  private Scanner scanner;
  /** The lexemes on this line. */
  private ArrayList<Lexeme> lexemes;
  /** The grammar's regular expression. */
  private Predicate<String> regex;
  /** Prompt printwriter. */
  private PrintWriter writer;
  /** Context required for registers. */
  private BFCalculator context;

  /** String of current line. For quiet mode. */
  private String line;

  /** Register valid strings. */
  private static String register = "[a-z]";
  /** Fractional input. */
  private static String fraction = "-?\\d+(/\\d+)?";

  /** The grammar of the language. */
  private static String grammar = "^(QUIT|STORE [a-z]|((-?\\d+(/\\d+)?|[a-z])"
      + "( [-+*/] (-?\\d+(/\\d+)?|[a-z]))*))$";

  /**
   * Create a new lexer from some scanner.
   * @param pContext FSM context needed for creating register lexemes.
   * @param pScanner the Scanner the lexer takes from.
   * @param pWriter A PrintWriter for the prompt, or null if none.
   */
  Lexer(BFCalculator pContext, Scanner pScanner, PrintWriter pWriter) {
    this.scanner = pScanner;
    this.context = pContext;
    this.regex = Pattern.compile(Lexer.grammar).asMatchPredicate();
    this.lexemes = new ArrayList<Lexeme>();
    this.writer = pWriter;
  } // Lexer

  /**
   * Convert a string into a lexeme.
   * @param inString The string to convert.
   * @throws SyntaxException when the string is not recognizable.
   * @return The lexeme
   */
  private Lexeme fromString(String inString) throws SyntaxException {
    Lexeme ret;

    if (inString.matches("^" + fraction + "$")) {
      ret = new BigFraction(inString);
    } else if (inString.matches("^" + register + "$")) {
      ret = this.context.getRegister(inString.charAt(0));
    } else {
      switch (inString) {
        case "/":
          ret = Operator.SLASH;
          break;
        case "*":
          ret = Operator.STAR;
          break;
        case "+":
          ret = Operator.PLUS;
          break;
        case "-":
          ret = Operator.MINUS;
          break;
        case "QUIT":
          ret = Command.QUIT;
          break;
        case "STORE":
          ret = Command.STORE;
          break;
        default:
          throw new SyntaxException();
      } // switch
    } // else

    return ret;
  } // fromString

  /**
   * Get the next line and parse it into lexemes.
   * @throws SyntaxException if the line does not match the regular grammar.
   */
  private void parseLine() throws SyntaxException {
    boolean quiet = this.writer == null;
    // If we were accessing a register, do not print the latest value
    if (!this.context.getDirectAddress()) {
      if (!quiet) {
        this.writer.println(this.context.get().toString());
      } else {
        this.context.getHistory().add(this.line + " = " + this.context.get());
      } // else
    } // if

    // If we aren't in quiet mode, prompt the user.
    if (!quiet) {
      this.writer.print(">");
      this.writer.flush();
    } // if

    this.context.clear();
    this.line = scanner.nextLine();
    if (!this.regex.test(this.line)) {
      throw new SyntaxException();
    } // if

    String[] tokenStrings = this.line.split("\\s");
    for (String tokenString : tokenStrings) {
      Lexeme convert = this.fromString(tokenString);
      this.lexemes.add(convert);
    } // for
  } // parseLine

  /**
   * Get the next token in the input sequence.
   * @throws SyntaxException when a new line is read and has a syntax error.
   * @return The parsed lexeme.
   */
  public Lexeme nextToken() throws SyntaxException {
    while (this.lexemes.isEmpty()) {
      parseLine();
    } // while
    return this.lexemes.remove(0);
  } // nextToken
} // class Lexeme
