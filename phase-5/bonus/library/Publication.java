package library;

import java.time.LocalDate;

/**
 * The Publication class represents a publication in the library.
 */
public class Publication {
    private String title;
    private String author;
    private int copyright;
    private String loanedTo;
    private LocalDate dueDate;

    /**
     * Constructs a Publication object with title, author, and copyright year.
     *
     * @param title     The title of the publication.
     * @param author    The author of the publication.
     * @param copyright The copyright year of the publication.
     */
    public Publication(String title, String author, int copyright) {
        this.title = title;
        this.author = author;

        int currentYear = LocalDate.now().getYear();
        if (copyright < 1900 || copyright > currentYear) {
            throw new IllegalArgumentException("Invalid copyright year.");
        }

        this.copyright = copyright;
    }

    /**
     * Checks out the publication to a patron.
     *
     * @param patron The patron checking out the publication.
     */
    public void checkout(String patron) {
        this.loanedTo = patron;
        this.dueDate = LocalDate.now().plusDays(14);
    }

    /**
     * Returns the publication to the library.
     */
    public void returnPublication() {
        this.loanedTo = null;
        this.dueDate = null;
    }

    /**
     * Generates a string representation of the publication.
     *
     * @return The string representation of the publication.
     */
    @Override
    public String toString() {
        StringBuilder publicationInfo = new StringBuilder();
        publicationInfo.append("Title: ").append(title).append("\n");
        publicationInfo.append("Author: ").append(author).append("\n");
        publicationInfo.append("Copyright: ").append(copyright).append("\n");

        if (loanedTo != null) {
            publicationInfo.append("Loaned To: ").append(loanedTo).append("\n");
            publicationInfo.append("Due Date: ").append(dueDate).append("\n");
        }

        return publicationInfo.toString();
    }
}

