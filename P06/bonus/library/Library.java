package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Library class represents a library with publications and patrons.
 */
public class Library {
    private String name;
    private List<Publication> publications;
    private List<String> patrons;

    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public Library(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.publications = new ArrayList<>();

        int numPublications = Integer.parseInt(br.readLine());

        for (int i = 0; i < numPublications; i++) {
            String publicationType = br.readLine();
            if ("video".equals(publicationType)) {
                // Read and create a Video object from the stream
                Video video = new Video(br);
                publications.add(video);
            } else if ("publication".equals(publicationType)) {
                // Read and create a Publication object from the stream
                Publication publication = new Publication(br);
                publications.add(publication);
            } else {
                // Handle an unrecognized publication type
                throw new IOException("Invalid publication type found in the file.");
            }
        }

        int numPatrons = Integer.parseInt(br.readLine());

        this.patrons = new ArrayList<>();
        for (int i = 0; i < numPatrons; i++) {
            String patronName = br.readLine();
            patrons.add(patronName);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<String> getPatrons() {
        return patrons;
    }

    public void setPatrons(List<String> patrons) {
        this.patrons = patrons;
    }

    public void addPatron(String newPatron) {
        patrons.add(newPatron);
    }

    public void clearPatrons() {
        patrons.clear();
    }

    public void clearPublications() {
        publications.clear();
    }

    public void checkin(int publicationIndex) {
        // Implement checkin logic here
    }

    public void checkout(int publicationIndex, String patronName) {
        // Implement checkout logic here
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void copyFrom(Library newLibrary) {
        name = newLibrary.getName();
        publications.clear();
        publications.addAll(newLibrary.getPublications());
        patrons.clear();
        patrons.addAll(newLibrary.getPatrons());
    }

    public void save(BufferedWriter bw) throws IOException {
        // Write the library name to the stream
        bw.write(name + '\n');

        // Write the number of publications in the library
        bw.write(publications.size() + "\n");

        // Iterate over the publications and write each one to the stream
        for (Publication publication : publications) {
            if (publication instanceof Video) {
                // Write "video" to indicate a Video object
                bw.write("video\n");
            } else {
                // Write "publication" to indicate a Publication object
                bw.write("publication\n");
            }

            // Write the publication details to the stream
            publication.save(bw);
        }

        // Write the number of patrons in the library
        bw.write(patrons.size() + "\n");

        // Write each patron's name to the stream
        for (String patron : patrons) {
            bw.write(patron + '\n');
        }
    }

    public static Library load(BufferedReader reader) throws IOException {
        String libraryName = reader.readLine();
        Library loadedLibrary = new Library(libraryName);

        int numPublications = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numPublications; i++) {
            String publicationType = reader.readLine();
            Publication publication;
            if ("video".equals(publicationType)) {
                // Create and load a Video object from the stream
                publication = Video.load(reader);
            } else if ("publication".equals(publicationType)) {
                // Create and load a Publication object from the stream
                publication = Publication.load(reader);
            } else {
                // Handle an unrecognized publication type
                throw new IOException("Invalid publication type found in the file.");
            }
            loadedLibrary.addPublication(publication);
        }

        int numPatrons = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numPatrons; i++) {
            String patronName = reader.readLine();
            loadedLibrary.addPatron(patronName);
        }

        return loadedLibrary;
    }
}



