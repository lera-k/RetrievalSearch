import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class ClauseSearch {

    private ClauseIndex clauseIndex;
    public ClauseSearch(){}
    public ClauseSearch(ClauseIndex clauseIndex) {
        this.clauseIndex = clauseIndex;
    }

    public Set<Integer> clauseSearch(String clauseQuery) {
        String[] clauseArr = clauseArray(clauseQuery);
        Set<Integer> res = docSet(clauseArr[0]);
        for (int i = 1; i < clauseArr.length; i++) {
            res = BooleanSearch.andSearch(res.iterator(), docSet(clauseArr[i]).iterator());
        }
        return res;
    }

    private Set<Integer> docSet(String clause) {
        Set<Integer> docSet = clauseIndex.docSet(clause);
        if (docSet == null) return Collections.emptySet();
        return docSet;
    }

    public String[] clauseArray(String query) {
        String[] words = query.split("\\s+");
        String[] clauses = new String[words.length - 1];
        for (int i = 0; i < words.length - 1; i++) {
            clauses[i] = DocsReading.cleanWord(clauses[i]) + DocsReading.cleanWord(clauses[i + 1]);
        }
        return clauses;
    }


}