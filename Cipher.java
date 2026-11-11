import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class Cipher extends FilterWriter {
    private char key;

    public Cipher(Writer writer, char key) {
        super(writer);
        this.key = key;
    }

    @Override
    public void write(int character) throws IOException {
        out.write((char) (character + key));
    }

    @Override
    public void write(char[] buffer, int offset, int length)
            throws IOException {

        for (int i = offset; i < offset + length; i++) {
            write(buffer[i]);
        }
    }

    @Override
    public void write(String text, int offset, int length)
            throws IOException {

        for (int i = offset; i < offset + length; i++) {
            write(text.charAt(i));
        }
    }
}
