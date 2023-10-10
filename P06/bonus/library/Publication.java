package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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

        int currentYear = LocalDate.now().getYear();
        if (copyright < 1900 || copyright > currentYear) {
            throw new IllegalArgumentException("Invalid copyright year.");
        }

        this.copyright = copyright;
    }

    public Publication(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.author = br.readLine();

        // Read and validate copyright year
        try {
            this.copyright = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid copyright year format in input stream.");
        }

        String loanStatus = br.readLine();
        if ("checked in".equals(loanStatus)) {
            this.loanedTo = null;
            this.dueDate = null;
        } else if ("checked out".equals(loanStatus)) {
            this.loanedTo = br.readLine();
            String dueDateStr = br.readLine();
            try {
                this.dueDate = LocalDate.parse(dueDateStr);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid due date format in input stream.");
            }
        } else {
            throw new IllegalArgumentException("Invalid loan status in input stream.");
        }
    }

    public void checkout(String patron) {
        this.loanedTo = patron;
        this.dueDate = LocalDate.now().plusDays(14);
    }

    public void returnPublication() {
        this.loanedTo = null;
        this.dueDate = null;
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

    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + '\n');
        bw.write(author + '\n');
        bw.write("" + copyright + '\n'); // Convert copyright to string

        if (loanedTo == null) {
            bw.write("checked in\n");
        } else {
            bw.write("checked out\n");
            bw.write(loanedTo + '\n');
            bw.write(dueDate.toString() + '\n');
        }
    }

    public static Publication createFromSavedString(String line) {
        return null;
    }

    public void checkin() {
    }

    public static Publication load(BufferedReader reader) {
        return null;
    }

    public void setCopyright(int year) {
    }

    public int getCopyright() {
        return 0;
    }
}

