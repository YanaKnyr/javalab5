import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileMethods {

    public static String findLineWithMostWords(String fileName)
            throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String result = "";
        int maxWords = 0;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (!line.isEmpty()) {
                String[] words = line.split("\\s+");

                if (words.length > maxWords) {
                    maxWords = words.length;
                    result = line;
                }
            }
        }

        reader.close();

        if (maxWords == 0) {
            return "File is empty";
        }

        return "Line: " + result + "\nNumber of words: " + maxWords;
    }
}
