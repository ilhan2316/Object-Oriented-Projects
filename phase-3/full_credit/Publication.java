
import java.time.LocalDate;

public class Publication {
    private String title;
    private String author;
    private int copyright;
    private String loanedTo;
    private LocalDate dueDate;

    public Publication(String title, String author, int copyright) {
        this.title = title;
        this.author = author;

        // Validate copyright year
        int currentYear = LocalDate.now().getYear();
        if (copyright < 1900 || copyright > currentYear) {
            throw new IllegalArgumentException("Invalid copyright year.");
        }

        this.copyright = copyright;
    }

    public void checkout(String patron) {
        this.loanedTo = patron;
        this.dueDate = LocalDate.now().plusDays(14);
    }

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