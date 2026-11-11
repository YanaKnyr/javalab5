import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class Decipher extends FilterReader {
    private char key;

    public Decipher(Reader reader, char key) {
        super(reader);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int character = in.read();

        if (character == -1) {
            return -1;
        }

        return (char) (character - key);
    }

    @Override
    public int read(char[] buffer, int offset, int length)
            throws IOException {

        if (length == 0) {
            return 0;
        }

        int count = 0;

        while (count < length) {
            int character = read();

            if (character == -1) {
                break;
            }

            buffer[offset + count] = (char) character;
            count++;
        }

        if (count == 0) {
            return -1;
        }

        return count;
    }
}
