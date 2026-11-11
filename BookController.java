import java.io.IOException;
import java.util.*;

public class BookController {

    private List<Book> books;
    private BookView view;
    private BookFileManager fileManager;

    public BookController(BookView view) {

        this.view = view;
        this.fileManager = new BookFileManager();
        books = new ArrayList<>();

        books.add(new Book("The Hobbit", "J.R.R. Tolkien",
                "Allen & Unwin", 1937, 310, 15.5));

        books.add(new Book("1984", "George Orwell",
                "Secker & Warburg", 1949, 328, 12.0));

        books.add(new Book("Animal Farm", "George Orwell",
                "Secker & Warburg", 1945, 112, 9.5));

        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald",
                "Scribner", 1925, 180, 11.0));

        books.add(new Book("Pride and Prejudice", "Jane Austen",
                "T. Egerton", 1813, 432, 13.0));

        books.add(new Book("Harry Potter", "J.K. Rowling",
                "Bloomsbury", 1997, 352, 18.0));

        books.add(new Book("The Da Vinci Code", "Dan Brown",
                "Doubleday", 2003, 454, 16.0));

        books.add(new Book("Dune", "Frank Herbert",
                "Chilton Books", 1965, 412, 17.0));

        books.add(new Book("To Kill a Mockingbird", "Harper Lee",
                "J.B. Lippincott", 1960, 281, 14.0));

        books.add(new Book("The Catcher in the Rye", "J.D. Salinger",
                "Little, Brown", 1951, 277, 10.5));
    }

    public void run() {
        int choice = -1;

        do {
            try {
                choice = view.showMenu();

                if (choice == 1) {
                    showAllBooks();
                }
                else if (choice == 2) {
                    searchByAuthor();
                }
                else if (choice == 3) {
                    searchByPublisher();
                }
                else if (choice == 4) {
                    searchByYear();
                }
                else if (choice == 5) {
                    sortByPublisher();
                }
                else if (choice == 6) {
                    saveBooks();
                }
                else if (choice == 7) {
                    loadBooks();
                }
                else if (choice == 8) {
                    findLongestLine();
                }
                else if (choice == 9) {
                    encryptOrDecrypt();
                }
                else if (choice == 10) {
                    analyzeHtmltags();
                }
                else if (choice == 0) {
                    view.showMessage("Program is finished");
                }
                else {
                    view.showMessage("Wrong menu option");
                }

            }
            catch (NumberFormatException e) {
                view.showMessage("Enter a valid number");
            }
            catch (IOException e) {
                view.showMessage("File or connection error: " + e.getMessage());
            }
            catch (ClassNotFoundException e) {
                view.showMessage("Unknown object in file");
            }
            catch (IllegalArgumentException e) {
                view.showMessage("Input error: " + e.getMessage());
            }
            catch (Exception e) {
                view.showMessage("Error: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private void showAllBooks() {
        view.showBooks(books);
    }

    private void searchByAuthor() {
        String author = view.readText("Enter author: ");
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        view.showBooks(result);
    }

    private void searchByPublisher() {
        String publisher = view.readText("Enter publisher: ");
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        view.showBooks(result);
    }

    private void searchByYear() {
        int year = view.readInt("Enter year: ");

        if (year < 0 || year > 9999) {
            throw new IllegalArgumentException("Incorrect year");
        }

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getYear() > year) {
                result.add(book);
            }
        }
        view.showBooks(result);
    }

    private void sortByPublisher() {
        List<Book> result = new ArrayList<>(books);

        Collections.sort(result, new Comparator<Book>() {
            @Override public int compare(Book a, Book b) {
                return a.getPublisher().compareToIgnoreCase(b.getPublisher());
            }
        });
        view.showBooks(result);
    }

    private void saveBooks() throws IOException {
        String fileName = view.readText("Enter file name for saving: ");
        fileManager.saveBooks(books, fileName);
        view.showMessage("Books were saved successfully");
    }

    private void loadBooks()
            throws IOException, ClassNotFoundException {

        String fileName = view.readText("Enter file name for loading: ");
        List<Book> loadedBooks = fileManager.loadBooks(fileName);
        books = loadedBooks;
        view.showMessage("Books were loaded successfully");
    }

    private void findLongestLine() throws IOException {
        String fileName = view.readText("Enter text file name: ");
        String result = FileMethods.findLineWithMostWords(fileName);
        view.showMessage(result);
    }

    private void encryptOrDecrypt() throws IOException {
        System.out.println("1. Encrypt file");
        System.out.println("2. Decrypt file");
        int choice = view.readInt("Your choice: ");

        if (choice != 1 && choice != 2) {
            throw new IllegalArgumentException("Choose 1 or 2");
        }

        String inputFile = view.readText("Enter input file name: ");
        String outputFile = view.readText("Enter output file name: ");

        if (inputFile.equals(outputFile)) {
            throw new IllegalArgumentException("Input and output files must be different");
        }

        char key = view.readKey();

        if (choice == 1) {
            CipherMethods.encrypt(inputFile, outputFile, key);
            view.showMessage("File encrypted successfully");
        }
        else {
            CipherMethods.decrypt(inputFile, outputFile, key);
            view.showMessage("File decrypted successfully.");
        }
    }

    private void analyzeHtmltags() throws IOException {
        String address = view.readText("Enter URL: ");
        Map<String, Integer> tags = HtmlTagAnalyzer.countTags(address);

        if (tags.isEmpty()) {
            view.showMessage("No tags found");
            return;
        }

        HtmlTagAnalyzer.printAlphabetically(tags);
        HtmlTagAnalyzer.printByFrequency(tags);
    }
}
