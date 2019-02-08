public class Test {
    static final int NUM_OF_DOCS = 5;
    static final String QUERY = "Brutus AND Campus";

    public static void main(String[] args) {
        Document[] docList = new Document[NUM_OF_DOCS];
        for (int i = 0; i < NUM_OF_DOCS; i++) {
            docList[i] = new Document("data/doc" + (i + 1) + ".txt", i);
        }
        PositionIndex positionIndex = new PositionIndex();
        ClauseIndex clauseIndex = new ClauseIndex();
        DocsReading docsReading = new DocsReading(docList);
        docsReading.fillVocab(clauseIndex);
        docsReading.fillVocab(positionIndex);
        ClauseSearch clauseSearch = new ClauseSearch(clauseIndex);
        PositionSearch positionSearch = new PositionSearch(positionIndex);
        clauseSearch.clauseSearch(QUERY).forEach(System.out::println);
        positionSearch.clausePosSearch(QUERY).forEach(System.out::println);
    }
}
