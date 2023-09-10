
import java.util.Scanner;

public class LibraryManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("Labyrinth Library");

        // Add 3 favorite books to the library
        library.addPublication(new Publication("The Bell Jar", "Sylvia Plath", 1963));
        library.addPublication(new Publication("The Secret History", "Donna Tart", 1992));
        library.addPublication(new Publication("Harry Potter and the Sorcerer's Stone", "J.K Rowling", 1997));

        System.out.println(library);

        try {
            System.out.print("Enter the index of the book to check out: ");
            int publicationIndex = scanner.nextInt();
            scanner.nextLine(); 

            if (publicationIndex >= 0 && publicationIndex <= 2) {
                System.out.print("Enter the patron's name: ");
                String patron = scanner.nextLine();

                library.checkout(publicationIndex, patron);

                // Print the updated Library
                System.out.println("Book checked out successfully!");
                System.out.println(library);
            } else {
                System.out.println("Invalid publication index!");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the publication index. Program will now exit.");
        }
    }
}

