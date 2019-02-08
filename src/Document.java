import java.util.Objects;

public class Document {
    private int docID;
    private String name;

    public Document(String name, int docID) {
        this.docID = docID;
        this.name = name;
    }

    public int getDocID() {
        return docID;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return docID == document.docID &&
                Objects.equals(name, document.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(docID, name);
    }
}
