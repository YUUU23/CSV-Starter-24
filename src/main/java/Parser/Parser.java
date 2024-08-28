package Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Parser<T> {
  /** TODO is this defensive enough? Feel free to edit any variable declarations */
  private final Reader reader;

  private final List<T> parsedContent;

  private List<String> header;
  private final CreatorFromRow<T> creatorClass;

  /**
   * TODO feel free to modify the header and body of this constructor however you wish
   *
   * @param reader - reader for the CSV File
   */
  public Parser(Reader reader, CreatorFromRow<T> creatorClass) throws IOException {
    this.reader = reader;
    this.parsedContent = new ArrayList<>();
    this.creatorClass = creatorClass;
  }

  /**
   * TODO feel free to modify this method to incorporate your design choices
   *
   * @throws IOException when buffer reader fails to read-in a line
   */
  public void parse(Boolean hasHeader) throws IOException, FactoryFailureException {
    String line;
    Pattern regexSplitCSVRow = Pattern.compile(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*(?![^\\\"]*\\\"))");
    BufferedReader readInBuffer =
        new BufferedReader(reader); // wraps around readers to improve efficiency when reading
    int numOfCols = -1;
    while ((line = readInBuffer.readLine()) != null) {
      String[] result = regexSplitCSVRow.split(line);
      List<String> lineToArr = Arrays.stream(result).toList();
      if (numOfCols == -1) {
        numOfCols = lineToArr.size();
        if (hasHeader) {
          this.header = lineToArr;
          continue;
        }
      }
      if (lineToArr.size() != numOfCols) {
        throw new FactoryFailureException(
            "This row doesn't have the correct number of columns", lineToArr);
      }
      this.parsedContent.add(this.creatorClass.create(lineToArr));
    }
    readInBuffer.close();
  }

  public List<T> getParsed() {
    return List.copyOf(parsedContent);
  }

  public List<String> getHeader() {
    return List.copyOf(header);
  }
}
