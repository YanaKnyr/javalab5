import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookFileManager {

    public void saveBooks(List<Book> books, String fileName)
            throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
        output.writeObject(books);
        output.close();
    }

    public List<Book> loadBooks(String fileName)
            throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
        Object object = input.readObject();
        input.close();

        if (!(object instanceof ArrayList<?>)) {
            throw new IOException("Incorrect file format");
        }

        ArrayList<?> list = (ArrayList<?>) object;
        List<Book> books = new ArrayList<>();

        for (Object item : list) {
            if (!(item instanceof Book)) {
                throw new IOException("File does not contain books");
            }

            books.add((Book) item);
        }
        return books;
    }
}
