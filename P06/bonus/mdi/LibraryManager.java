package mdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import library.Library;
import library.Publication;
import library.Video;

public class LibraryManager {
    public static void main(String[] args) {
        Library library = new Library("Labyrinth Library");

        library.addPublication(new Publication("The Bell Jar", "Sylvia Plath", 1963));
        library.addPublication(new Publication("The Secret History", "Donna Tartt", 1992));
        library.addPublication(new Publication("Harry Potter and the Sorcerer's Stone", "J.K Rowling", 1997));

        Video video1 = createVideo("Ender's Game", "Gavin Hood", 2013, 114);
        Video video2 = createVideo("Remember the Titans", "Boaz Yakin", 2015, 120);
        Video video3 = createVideo("Shawshank Redemption", "Frank Darabont", 1994, 142);

        if (video1 != null) {
            library.addPublication(video1);
        }
        if (video2 != null) {
            library.addPublication(video2);
        }
        if (video3 != null) {
            library.addPublication(video3);
        }

        library.addPatron("Alice");

        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("--------------");
            System.out.println("Main Menu");
            System.out.println("--------------");
            System.out.println("Labyrinth Library\n");

            System.out.println("Publications");
            System.out.println("1) List");
            System.out.println("2) Add");
            System.out.println("3) Check out");
            System.out.println("4) Check in");

            System.out.println("\nPatrons");
            System.out.println("5) List");
            System.out.println("6) Add Patron");

            System.out.println("\nFiles");
            System.out.println("7) Save");
            System.out.println("8) Open");
            System.out.println("0) Exit");

            try {
                System.out.print("Enter your choice: ");
                if (scan.hasNextLine()) {
                    String input = scan.nextLine();
                    int num = Integer.parseInt(input);

                    switch (num) {
                        case 1:
                            listPublications(library);
                            break;
                        case 2:
                            addPublication(scan, library);
                            break;
                        case 3:
                            checkoutPublication(scan, library);
                            break;
                        case 4:
                            checkinPublication(scan, library);
                            break;
                        case 5:
                            listPatrons(library);
                            break;
                        case 7:
                            saveLibrary(scan, library);
                            break;
                        case 8:
                            openLibrary(scan, library);
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                } else {
                    System.out.println("No input provided. Please enter a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }

    public static Video createVideo(String title, String director, int year, int runtime) {
        try {
            return new Video(title, director, year, runtime);
        } catch (Video.InvalidRuntimeException e) {
            System.err.println("Invalid runtime exception: " + e.getMessage());
            return null;
        }
    }

    public static void listPublications(Library library) {
        List<Publication> publications = library.getPublications();
        if (publications.isEmpty()) {
            System.out.println("No publications available.");
        } else {
            System.out.println("List of Publications:");
            for (int i = 0; i < publications.size(); i++) {
                System.out.println(i + ": " + publications.get(i));
            }
        }
    }

    public static void addPublication(Scanner scanner, Library library) {
        System.out.print("Enter the title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author/director: ");
        String author = scanner.nextLine();
        System.out.print("Enter the copyright year: ");
        int copyright = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is this a video? (yes/no): ");
        String isVideo = scanner.nextLine();
        if ("yes".equalsIgnoreCase(isVideo)) {
            System.out.print("Enter the runtime (in minutes): ");
            int runtime = scanner.nextInt();
            scanner.nextLine();
            try {
                Publication publication = new Video(title, author, copyright, runtime);
                library.addPublication(publication);
                System.out.println("Video added successfully!");
            } catch (Video.InvalidRuntimeException e) {
                System.err.println("Invalid runtime exception: " + e.getMessage());
            }
        } else {
            Publication publication = new Publication(title, author, copyright);
            library.addPublication(publication);
            System.out.println("Publication added successfully!");
        }
    }

    public static void checkoutPublication(Scanner scanner, Library library) {
        System.out.println("Select a publication to check out:");
        listPublications(library);
        System.out.print("Enter the index of the publication: ");
        int publicationIndex = scanner.nextInt();
        scanner.nextLine();

        List<Publication> publications = library.getPublications();
        if (isValidPublicationIndex(publicationIndex, publications)) {
            System.out.print("Enter the patron's name: ");
            String patronName = scanner.nextLine();
            library.checkout(publicationIndex, patronName);
            System.out.println("Publication checked out successfully!");
        } else {
            System.out.println("Invalid publication index. Please select a valid publication.");
        }
    }

    public static void checkinPublication(Scanner scanner, Library library) {
        System.out.println("Select a publication to check in:");
        listPublications(library);
        System.out.print("Enter the index of the publication: ");
        int publicationIndex = scanner.nextInt();
        scanner.nextLine();

        List<Publication> publications = library.getPublications();
        if (isValidPublicationIndex(publicationIndex, publications)) {
            library.checkin(publicationIndex);
            System.out.println("Publication checked in successfully!");
        } else {
            System.out.println("Invalid publication index. Please select a valid publication.");
        }
    }

    public static void listPatrons(Library library) {
        List<String> patrons = library.getPatrons();
        if (patrons.isEmpty()) {
            System.out.println("No patrons available.");
        } else {
            System.out.println("List of Patrons:");
            for (int i = 0; i < patrons.size(); i++) {
                System.out.println(i + ": " + patrons.get(i));
            }
        }
    }

    public static void addPatron(Scanner scanner, Library library) {
        System.out.print("Enter the name of the new patron: ");
        String newPatron = scanner.nextLine();
        library.addPatron(newPatron);
        System.out.println("New patron added successfully!");
    }

    public static void saveLibrary(Scanner scanner, Library library) {
        System.out.print("Enter the filename to save the library data: ");
        String filename = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            library.save(writer);
            System.out.println("Library data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error: Could not write to the file. " + e.getMessage());
        }
    }

    public static void openLibrary(Scanner scanner, Library library) {
        System.out.print("Enter the filename to open the library data: ");
        String filename = scanner.nextLine();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Library newLibrary = Library.load(reader);
            if (newLibrary != null) {
                library.copyFrom(newLibrary);
                System.out.println("Library data loaded successfully!");
            } else {
                System.err.println("Error: Invalid library data format in the file.");
            }
        } catch (IOException e) {
            System.err.println("Error: Could not open the file. " + e.getMessage());
        }
    }
     

    private static boolean isValidPublicationIndex(int index, List<Publication> publications) {
        return index >= 0 && index < publications.size();
    }
}