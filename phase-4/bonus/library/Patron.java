package library;

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
}

