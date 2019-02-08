import java.util.*;

public class ClauseIndex implements InvertedIndex {
    private Map<String, TreeSet<Integer>> clauseIndex;

    public ClauseIndex() {
        clauseIndex = new HashMap<>();
    }

    @Override
    public void addWord(String word, int docNum) {
        if (word == null) throw new NullPointerException("Clause is null");
        TreeSet<Integer> docIds = clauseIndex.get(word);
        if (docIds == null) {
            docIds = new TreeSet<>();
            clauseIndex.put(word, docIds);
        }
        docIds.add(docNum);
    }

    public Set<Integer> docSet(String clause) {
        return clauseIndex.get(clause);
    }
}
