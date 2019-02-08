import java.util.*;

public class BooleanSearch {
    private InvertedWordIndex invertedWordIndex;

    public BooleanSearch(InvertedWordIndex invertedWordIndex) {
        this.invertedWordIndex = invertedWordIndex;
    }

    public Set<Integer> search(String query) {
        Queue<Set<Integer>> docSets = new LinkedList<>();
        Queue<String> operationsWithTerms = new LinkedList<>();
        String[] tokens = query.split("\\s+");
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "AND":
                    operationsWithTerms.add(tokens[i]);
                    break;
                case "OR":
                    operationsWithTerms.add(tokens[i]);
                    break;
                case "NOT":
                    docSets.add(invertedWordIndex.invertedDocSet(tokens[++i]));
                    break;
                default:
                    docSets.add(invertedWordIndex.docSet(tokens[i]));
                    break;
            }
        }
        return search(docSets, operationsWithTerms);
    }

    private Set<Integer> search(Queue<Set<Integer>> docSets, Queue<String> operations) {
        Set<Integer> result = docSets.remove();
        Set<Integer> docsWithTerm2;
        while (operations.size() != 0) {
            docsWithTerm2 = docSets.remove();
            switch (operations.remove()) {
                case "AND":
                    result = andSearch(result.iterator(), docsWithTerm2.iterator());
                    break;
                case "OR":
                    result = orSearch(result.iterator(), docsWithTerm2.iterator());
                    break;
            }
        }
        return result;
    }

    public static Set<Integer> andSearch(Iterator<Integer> docIdSet1, Iterator<Integer> docIdSet2) {
        Set<Integer> answer = new TreeSet<>();
        int fromFirstId = docIdSet1.next();
        int fromSecondId = docIdSet2.next();
        while (true) {
            if (fromFirstId == fromSecondId) {
                answer.add(fromFirstId);
                if (!docIdSet1.hasNext() || !docIdSet2.hasNext()) break;
                fromFirstId = docIdSet1.next();
                fromSecondId = docIdSet2.next();
            } else if (fromFirstId > fromSecondId && docIdSet2.hasNext()) {
                fromSecondId = docIdSet2.next();
            } else if (fromFirstId < fromSecondId && docIdSet1.hasNext()) {
                fromFirstId = docIdSet1.next();
            } else break;
        }
        return answer;
    }

    private Set<Integer> orSearch(Iterator<Integer> docIdSet1, Iterator<Integer> docIdSet2) {
        Set<Integer> res = new TreeSet<>();
        docIdSet1.forEachRemaining(res::add);
//        while(docIdSet1.hasNext()){
//            res.add(docIdSet1.next());
//        }
        docIdSet2.forEachRemaining(res::add);
        return res;
    }


}
