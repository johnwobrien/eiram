package edu.rit.se.sse.eiram;

import edu.rit.se.sse.eiram.grammar.*;

import org.antlr.runtime.*;

/**
 * Hello world!
 */
public class Main {

  public static void main(String[] args) throws Exception {
    EiramParser parser = new EiramParser(
        new CommonTokenStream(
          new EiramLexer(
            new ANTLRStringStream("_2"))));

    parser.label();
  }

}
