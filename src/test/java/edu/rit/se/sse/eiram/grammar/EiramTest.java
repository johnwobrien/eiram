package edu.rit.se.sse.eiram.grammar;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.MismatchedTokenException;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the production rules of eiram's grammar.
 *
 * <p>The grammar is defined in
 * <code>src/main/antlr3/edu/rit/se/sse/eiram/grammar/Eiram.g</code>.</p>
 *
 * <p>This is in lieu of using gunit for grammar testing because of its poor
 * integration with JUnit. A test passes if either:</p>
 *
 * <ul>
 *   <li>No exception was thrown.</li>
 *   <li>An <emph>expected</emph> exception was thrown.</li>
 * </ul>
 *
 */
public class EiramTest {

  private static PrintStream originalStdErr;

  @BeforeClass
  public static void setUpTest() throws Exception {
    EiramTest.originalStdErr = System.err;

    // Makes antlr not write to console.
    System.setErr(
        new PrintStream(
          new OutputStream() {
            public void write(int b) { }
           }));
  }

  @AfterClass
  public static void tearDownTests() throws Exception {
    System.setOut(EiramTest.originalStdErr);
  }

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void acceptingLabelRule() throws Exception {
    try {
      this.parser("start").label();
      this.parser("_start").label();
      this.parser("_start01").label();
      this.parser("_start_01").label();
      this.parser("_start_01_03_").label();
      this.parser("_TWO").label();
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test(expected=NullPointerException.class)
  public void rejectingLabelRule() throws Exception {
    this.parser("___").label();
  }

  private EiramParser parser(String input) throws Exception {
    ANTLRStringStream stream = new ANTLRStringStream(input);
    EiramLexer lexer = new EiramLexer(stream);
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    return new EiramParser(tokens);
  }

}
