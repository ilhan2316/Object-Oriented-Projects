
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

    /**
     * Constructs a Patron object with a given name.
     *
     * @param name The name of the patron.
     */
    public Patron(String name) {
        this.name = name;
        this.checkedOutPublications = new ArrayList<>();
    }

    /**
     * Gets the name of the patron.
     *
     * @return The name of the patron.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks out a publication to the patron.
     *
     * @param publication The publication to check out.
     */
    public void checkOutPublication(Publication publication) {
        checkedOutPublications.add(publication);
    }

    /**
     * Returns a publication to the library.
     *
     * @param publication The publication to return.
     */
    public void returnPublication(Publication publication) {
        checkedOutPublications.remove(publication);
    }

    /**
     * Gets the list of publications checked out by the patron.
     *
     * @return A list of checked-out publications.
     */
    public List<Publication> getCheckedOutPublications() {
        return checkedOutPublications;
    }

    /**
     * Saves the patron's data to the provided BufferedWriter stream.
     *
     * @param bw The BufferedWriter to write to.
     * @throws IOException if an I/O error occurs while writing.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        for (Publication publication : checkedOutPublications) {
            bw.write(publication.toString() + '\n');
        }
        bw.write("EndOfCheckedOutPublications\n");
    }

    /**
     * Constructs a Patron object by reading data from the provided BufferedReader stream.
     *
     * @param br The BufferedReader to read from.
     * @throws IOException if an I/O error occurs while reading.
     */
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
