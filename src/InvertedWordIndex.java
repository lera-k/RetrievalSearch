import java.util.*;

public class InvertedWordIndex implements InvertedIndex {
    private TreeMap<String, TreeSet<Integer>> indexMap;
    private Document[] docList;

    public InvertedWordIndex(Document[] docList) {
        this.docList = docList;
        indexMap = new TreeMap<>();
    }

    public void addWord(String word, int docNum) {
        if (!indexMap.containsKey(word)) {
            indexMap.put(word, new TreeSet<>());
        }
        indexMap.get(word).add(docNum);
    }

    public Set<Integer> docSet(String word) {
        if (!indexMap.containsKey(word)) return new TreeSet<>();
        return indexMap.get(word);
    }

    public Set<Integer> invertedDocSet(String word) {
        Set<Integer> docSet = docSet(word);
        if (docSet.size() == 0) return new TreeSet<>(docSet);
        Set<Integer> res = new TreeSet<>();
        for (Document document : docList) {
            if (!docSet.contains(document.getDocID())) res.add(document.getDocID());
        }
        return res;
    }
}
