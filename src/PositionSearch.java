import java.util.*;

public class PositionSearch {

    private PositionIndex positionIndex;
    private ClauseSearch clauseSearch = new ClauseSearch();

    public PositionSearch(PositionIndex positionIndex) {
        this.positionIndex = positionIndex;
    }

    public Set<Integer> clausePosSearch(String clauseQuery) {
        Set<Integer> allDocs = clauseDocSearch(clauseQuery);
        Set<Integer> res = Collections.emptySet();
        ArrayList positions;
        String[] clauseArr = clauseSearch.clauseArray(clauseQuery);
        for (int doc : allDocs) {
            positions = positionIndex.getClauseIndexMap().get(clauseArr[0]).get(doc);
            for (int i = 1; i < clauseArr.length; i++) {
                positions = posIntersection(positions, positionIndex.getClauseIndexMap().get(clauseArr[i]).get(doc));
            }
            if (positions.size() != 0) res.add(doc);
        }
        return res;
    }

    public Set<Integer> clauseDocSearch(String clauseQuery) {
        Set<Integer> docSet = clauseSearch.clauseSearch(clauseQuery);
        String[] clauseArr = clauseSearch.clauseArray(clauseQuery);
        Set<Integer> res = positionIndex.docSet(clauseArr[0]);
        for (int i = 1; i < clauseArr.length; i++) {
            res = BooleanSearch.andSearch(res.iterator(), positionIndex.docSet(clauseArr[i]).iterator());
        }
        return res;
    }

    private ArrayList<Integer> posIntersection(ArrayList<Integer> pos1, ArrayList<Integer> pos2) {
        ArrayList<Integer> res = new ArrayList<>();
        for (Integer position1 : pos1) {
            for (Integer position2 : pos2) {
                if (position1.equals(position2)) res.add(position1);
            }
        }
        return res;
    }
}
