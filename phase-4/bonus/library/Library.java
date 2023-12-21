
package library;

import java.util.ArrayList;
import java.util.List;

/**
 * The Library class represents a library with publications and patrons.
 */
public class Library {
    private String name;
    private List<Publication> publications;
    private List<String> patrons;

    /**
     * Constructs a Library object with a given name.
     *
     * @param name The name of the library.
     */
    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    /**
     * Adds a publication to the library.
     *
     * @param publication The publication to add.
     */
    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    /**
     * Adds a patron to the library.
     *
     * @param patron The patron to add.
     */
    public void addPatron(String patron) {
        patrons.add(patron);
    }

    /**
     * Gets the list of patrons in the library.
     *
     * @return A list of patrons.
     */
    public List<String> getPatrons() {
        return patrons;
    }

    /**
     * Checks out a publication to a patron.
     *
     * @param publicationIndex The index of the publication to check out.
     * @param patron           The patron checking out the publication.
     */
    public void checkout(int publicationIndex, String patron) {
        if (!isValidPublicationIndex(publicationIndex)) {
            System.out.println("Invalid publication index. Please select a valid publication.");
            return;
        }

        Publication publication = publications.get(publicationIndex);
        publication.checkout(patron);
    }

    // ... (Other methods)

    /**
     * Checks if the given publication index is valid.
     *
     * @param index The publication index to check.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidPublicationIndex(int index) {
        return index >= 0 && index < publications.size();
    }
    
    /**
     * Generates a string representation of the library.
     *
     * @return The string representation of the library.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("\n");

        for (int i = 0; i < publications.size(); i++) {
            stringBuilder.append(i).append("| ").append(publications.get(i)).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Gets the list of publications in the library.
     *
     * @return A list of publications.
     */
    public List<Publication> getPublications() {
        return publications;
    }
}
