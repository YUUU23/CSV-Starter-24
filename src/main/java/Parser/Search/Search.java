package Parser.Search;

import java.util.ArrayList;
import java.util.List;

public class Search {

  public List<List<String>> search(
      List<String> header, List<List<String>> csv, String column, String searchQuery) {
    List<List<String>> result = new ArrayList<>(); // Initialize the result list
    int colNum = 0;

    if (column.isBlank()) {
      colNum = -1;
    } else if (column.matches("-?\\d+(\\.\\d+)?")) {
      colNum = Integer.parseInt(column);
    } else {
      if (header.contains(searchQuery)) {
        colNum = header.indexOf(searchQuery);
      }
    }

    for (int i = 0; i < csv.size(); i++) {
      if (csv.get(i).get(colNum).contains(searchQuery)
          || (colNum == -1 && csv.get(i).contains(searchQuery))) {
        result.add(csv.get(i));
      }
    }

    return result;
  }
}
