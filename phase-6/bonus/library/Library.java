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
    public String name;
    public List<Publication> publications;
    public List<String> patrons;

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
                
                Video video = new Video(br);
                publications.add(video);
            } else if ("publication".equals(publicationType)) {
                
                Publication publication = new Publication(br);
                publications.add(publication);
            } else {
                
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

    public void addPatron(String newPatron) {
        patrons.add(newPatron);
    }

    public List<String> clearPatrons() {
        patrons.clear();
        return patrons;
    }

    public List<Publication> clearPublications() {
        publications.clear();
        return publications;
    }

    public void checkin(int publicationIndex) {
        // Implement checkin logic here
    }

    public void checkout(int publicationIndex, String patronName) {
        
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void copyFrom(Library newLibrary) {
        name = newLibrary.name;
        publications.clear();
        publications.addAll(newLibrary.publications);
        patrons.clear();
        patrons.addAll(newLibrary.patrons);
    }

    public void save(BufferedWriter bw) throws IOException {
        
        bw.write(name + '\n');

        
        bw.write(publications.size() + "\n");

        
        for (Publication publication : publications) {
            if (publication instanceof Video) {
                
                bw.write("video\n");
            } else {

                bw.write("publication\n");
            }


            publication.save(bw);
        }

        
        bw.write(patrons.size() + "\n");

        
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
                
                publication = Video.load(reader);
            } else if ("publication".equals(publicationType)) {
                
                publication = Publication.load(reader);
            } else {
               
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




