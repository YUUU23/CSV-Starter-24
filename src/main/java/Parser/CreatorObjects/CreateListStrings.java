package Parser.CreatorObjects;

import Parser.CreatorFromRow;
import Parser.FactoryFailureException;
import java.util.List;

public class CreateListStrings implements CreatorFromRow<List<String>> {

  @Override
  public List<String> create(List<String> row) throws FactoryFailureException {
    return row;
  }
}
