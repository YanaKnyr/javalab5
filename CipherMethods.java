import java.io.*;

public class CipherMethods {

    public static void encrypt(String inputFile, String outputFile, char key) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Cipher writer = new Cipher(new FileWriter(outputFile), key);
        char[] buffer = new char[1024];
        int count;

        while ((count = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, count);
        }

        reader.close();
        writer.close();
    }

    public static void decrypt(String inputFile, String outputFile, char key) throws IOException {
        Decipher reader = new Decipher(new FileReader(inputFile), key);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        char[] buffer = new char[1024];
        int count;

        while ((count = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, count);
        }

        reader.close();
        writer.close();
    }
}
