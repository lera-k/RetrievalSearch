import java.util.Set;

public interface InvertedIndex {
    void addWord(String word, int docNum);
    //Set<Integer> docSet(String word);
}
