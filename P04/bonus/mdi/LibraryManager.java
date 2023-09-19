package mdi;

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

            System.out.println(library);

            try {
                System.out.print("Enter the index of the publication to check out: ");
                int publicationIndex = scanner.nextInt();
                scanner.nextLine();

                if (publicationIndex >= 0 && publicationIndex < library.getPublications().size()) {
                    System.out.println("Select a patron:");
                    for (int i = 0; i < library.getPatrons().size(); i++) {
                        System.out.println(i + ": " + library.getPatrons().get(i));
                    }

                    System.out.print("Enter the patron index: ");
                    int patronIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (patronIndex >= 0 && patronIndex < library.getPatrons().size()) {
                        String selectedPatron = library.getPatrons().get(patronIndex);

                        library.checkout(publicationIndex, selectedPatron);
                        System.out.println("Publication checked out successfully!");
                        System.out.println(library);
                    } else {
                        System.out.println("Invalid patron index!");
                    }
                } else {
                    System.out.println("Invalid publication index!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid integers for the publication and patron indexes. Program will now exit.");
            } catch (Video.InvalidRuntimeException e) {
                System.out.println("Invalid runtime exception: " + e.getMessage());
            }
        }
    }

    private static Video createVideo(String title, String author, int copyright, int runtime) {
        try {
            return new Video(title, author, copyright, runtime);
        } catch (Video.InvalidRuntimeException e) {
            System.err.println("Invalid runtime exception: " + e.getMessage());
            return null;
        }
    }
}
