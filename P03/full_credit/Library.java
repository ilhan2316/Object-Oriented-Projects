
import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Publication> publications;

    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void checkout(int publicationIndex, String patron) {
        if (!isValidPublicationIndex(publicationIndex)) {
            System.out.println("Invalid publication index. Please select a valid publication.");
            return; 
        }

        Publication publication = publications.get(publicationIndex);
        publication.checkout(patron);
    }

    private boolean isValidPublicationIndex(int index) {
        return index >= 0 && index <= 2; 
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("\n");

        for (int i = 0; i < publications.size(); i++) {
            stringBuilder.append(i).append("| ").append(publications.get(i)).append("\n");
        }

        return stringBuilder.toString();
    }
}

