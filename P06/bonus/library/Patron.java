package library;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Patron class represents a library patron who can check out publications.
 */
public class Patron {
    private String name;
    private List<Publication> checkedOutPublications;

    public Patron(String name) {
        this.name = name;
        this.checkedOutPublications = new ArrayList<>();
    }

    public void checkOutPublication(Publication publication) {
        checkedOutPublications.add(publication);
    }

    public void returnPublication(Publication publication) {
        checkedOutPublications.remove(publication);
    }

    public List<Publication> getCheckedOutPublications() {
        return checkedOutPublications;
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        for (Publication publication : checkedOutPublications) {
            bw.write(publication.toString() + '\n');
        }
        bw.write("EndOfCheckedOutPublications\n");
    }

    public Patron(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.checkedOutPublications = new ArrayList<>();

        String line;
        while (!(line = br.readLine()).equals("EndOfCheckedOutPublications")) {
            Publication publication = Publication.createFromSavedString(line);
            if (publication != null) {
                checkedOutPublications.add(publication);
            }
        }
    }
}

