import java.util.List;
import java.util.Scanner;

public class BookView {

    private Scanner scanner = new Scanner(System.in);

    public int showMenu() {

        System.out.println("\nBook program menu:");
        System.out.println("1. Show all books");
        System.out.println("2. Search by author");
        System.out.println("3. Search by publisher");
        System.out.println("4. Search by year");
        System.out.println("5. Sort by publisher");
        System.out.println("6. Save books to file");
        System.out.println("7. Load books from file");
        System.out.println("8. Find line with most words");
        System.out.println("9. Encrypt or decrypt file");
        System.out.println("10. Analyze HTML tags");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String readText(String message) {
        System.out.print(message);
        String text = scanner.nextLine().trim();
        
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        return text;
    }

    public int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    public char readKey() {
        String key = readText("Enter key character: ");

        if (key.length() != 1) {
            throw new IllegalArgumentException("Key must contain one character.");
        }

        return key.charAt(0);
    }

    public void showBooks(List<Book> books) {

        if (books.isEmpty()) {
            System.out.println("Books were not found");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
