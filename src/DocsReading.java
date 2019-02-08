import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DocsReading {
    private Document[] documents;


    public DocsReading(Document[] documents) {
        this.documents = documents;
    }

    public void fillVocab(InvertedIndex index) {
        for (Document document:documents) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(document.getName()));
                String line, lastWord = null;
                while ((line = reader.readLine()) != null) {
                    line = cleanWord(line);
                    String[] tokens = line.split("\\s+");
                    if (tokens.length == 0) continue;
                    if (lastWord != null) index.addWord(lastWord + " " + tokens[0], document.getDocID());
                    for (int i = 0; i < tokens.length - 1; i++) {
                        index.addWord(tokens[i] + " " + tokens[i + 1], document.getDocID());
                    }
                    lastWord = tokens[tokens.length - 1];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillVocab(PositionIndex index) {
        for (Document document:documents) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(document.getName()));
                String line;
                int position = 0;
                while ((line = reader.readLine()) != null) {
                    line = cleanWord(line);
                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {
                        index.addClause(token, document.getDocID(), position++);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String cleanWord(String word) {
        word = word.replace(".", "");
        word = word.replace(",", "");
        word = word.replace("!", "");
        word = word.replace("?", "");
        word = word.replace("“", "");
        word = word.replace("”", "");
        word = word.replace("(", "");
        word = word.replace(")", "");
        return word;
    }
}
