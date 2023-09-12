import java.util.Scanner;

public class LibraryManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("Labyrinth Library");

        
        library.addPublication(new Publication("The Bell Jar", "Sylvia Plath", 1963));
        library.addPublication(new Publication("The Secret History", "Donna Tartt", 1992));
        library.addPublication(new Publication("Harry Potter and the Sorcerer's Stone", "J.K Rowling", 1997));

        
        library.addPatron("Alice");

        System.out.println(library);

        try {
            System.out.print("Enter the index of the book to check out: ");
            int publicationIndex = scanner.nextInt();
            scanner.nextLine();

            if (publicationIndex >= 0 && publicationIndex <= 2) {
                
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
                    System.out.println("Book checked out successfully!");
                    System.out.println(library);
                } else {
                    System.out.println("Invalid patron index!");
                }
            } else {
                System.out.println("Invalid publication index!");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid integers for the publication and patron indexes. Program will now exit.");
        }
    }
}
