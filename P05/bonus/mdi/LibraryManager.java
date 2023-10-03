package mdi;


import java.util.List;
import java.util.Scanner;
import library.Library;
import library.Publication;
import library.Video;

public class LibraryManager {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
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
                System.out.println("0) Exit");

                System.out.print("\nEnter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                       
                        listPublications(library);
                        break;
                    case 2:
                        
                        addPublication(scanner, library);
                        break;
                    case 3:
                        
                        checkoutPublication(scanner, library);
                        break;
                    case 4:
                        
                        checkinPublication(scanner, library);
                        break;
                    case 5:
                        
                        listPatrons(library);
                        break;
                    case 6:
                        
                        addPatron(scanner, library);
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        }
    }

    private static Video createVideo(String title, String director, int year, int runtime) {
        try {
            return new Video(title, director, year, runtime);
        } catch (Video.InvalidRuntimeException e) {
            System.err.println("Invalid runtime exception: " + e.getMessage());
            return null;
        }
    }

    private static void listPublications(Library library) {
        List<Publication> publications = library.getPublications();
        for (int i = 0; i < publications.size(); i++) {
            System.out.println(i + ": " + publications.get(i));
        }
    }

    private static void addPublication(Scanner scanner, Library library) {
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
            Publication publication = new Video(title, author, copyright, runtime);
            library.addPublication(publication);
        } else {
            Publication publication = new Publication(title, author, copyright);
            library.addPublication(publication);
        }

        System.out.println("Publication checked out successfully!");
        System.out.println(library);
    }

    private static void checkoutPublication(Scanner scanner, Library library) {
        System.out.println("Select a publication to check out:");
        listPublications(library);
        System.out.print("Enter the index of the publication: ");
        int publicationIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (isValidPublicationIndex(publicationIndex, library)) {
            System.out.print("Enter the name of the patron: ");
            String patron = scanner.nextLine();
            library.checkout(publicationIndex, patron);
            System.out.println("Publication checked out successfully!");
        } else {
            System.out.println("Invalid publication index!");
        }
    }

    private static void checkinPublication(Scanner scanner, Library library) {
        System.out.println("Select a publication to check in:");
        listPublications(library);
        System.out.print("Enter the index of the publication: ");
        int publicationIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (isValidPublicationIndex(publicationIndex, library)) {
            library.checkin(publicationIndex);
            System.out.println("Publication checked in successfully!");
        } else {
            System.out.println("Invalid publication index!");
        }
    }

    private static void listPatrons(Library library) {
        List<String> patrons = library.getPatrons();
        for (int i = 0; i < patrons.size(); i++) {
            System.out.println(i + ": " + patrons.get(i));
        }
    }
    
    private static void addPatron(Scanner scanner, Library library) {

        System.out.print("Enter the name of the new patron: ");
        String newPatron = scanner.nextLine();
        library.addPatron(newPatron);
        System.out.println("New patron added successfully!");
    }
    
    private static boolean isValidPublicationIndex(int index, Library library) {
        return index >= 0 && index < library.getPublications().size();
    }
}


