import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//класс, в якому буде зчитуватись файл та додаватись по два токени
public class PositionIndex {
    private Map<String, HashMap<Integer, ArrayList<Integer>>> clauseIndexMap;

    public PositionIndex() {
        clauseIndexMap = new HashMap<>();
    }

    public void addClause(String clause, int docNumber, int positionInDoc) {
        HashMap<Integer, ArrayList<Integer>> clauseBackground = clauseIndexMap.get(clause);
        if (clauseBackground == null) {
            clauseBackground = new HashMap<>();
            clauseIndexMap.put(clause, clauseBackground);
        }
        ArrayList<Integer> clausePositions = clauseBackground.get(docNumber);
        if (clausePositions == null) {
            clausePositions = new ArrayList<>();
            clauseBackground.put(docNumber, clausePositions);
        }
        clausePositions.add(positionInDoc);
    }
    public Set<Integer> docSet(String clause) {
        return clauseIndexMap.get(clause).keySet();
    }
    public Map<String, HashMap<Integer, ArrayList<Integer>>> getClauseIndexMap(){
        return clauseIndexMap;
    }
}
