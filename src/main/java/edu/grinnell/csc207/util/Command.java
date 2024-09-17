package edu.grinnell.csc207.util;

/**
 * Enumeration of valid non-expression commands.
 * @author Drew Fargo
 * @version 1.0
 */
public enum Command implements Lexeme {
  /** Exit command. */
  QUIT {
    /**
     * Signal the context to cease.
     * @param context The context
     */
    public void alterContext(BFCalculator context) {
      context.stopRunning();
    } // alterContext
  }, // QUIT
  /** Store command. */
  STORE {
    /**
     * Store does not change the context other than state.
     * @param context The context
     */
    public void alterContext(BFCalculator context) {
      context.setDirectAddress(true);
    } // alterContext
  } // STORE
} // enum Command
