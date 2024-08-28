package edu.brown.cs.student.main;

import Parser.CreatorFromRow;
import Parser.CreatorObjects.CreateListStrings;
import Parser.FactoryFailureException;
import Parser.Parser;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The Main class of our project. This is where execution begins. Note: For this first sprint, you
 * will not be running the parser through main(), but rather interacting with the parser through
 * extensive testing!
 */
public final class Main {

  /**
   * The main method is the entry point of the application.
   *
   * @param args command-line arguments passed to the program
   */
  public static void main(String[] args) {
    try {
      CreatorFromRow<List<String>> creatorObject = new CreateListStrings();
      Parser<List<String>> parser =
          new Parser<List<String>>(new FileReader(args[0]), creatorObject);
      boolean hasHeader = args[1].equalsIgnoreCase("true");
      parser.parse(hasHeader);
      System.out.println(parser.getParsed());
    } catch (IOException e) {
      System.err.println("File not Found");
    } catch (FactoryFailureException e) {
      throw new RuntimeException(e);
    }
  }
}
