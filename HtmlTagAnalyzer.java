import java.io.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.net.URI;
import java.net.URL;

public class HtmlTagAnalyzer {

    public static Map<String, Integer> countTags(String address)
            throws IOException {

        URI uri = URI.create(address);
        URL url = uri.toURL();
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        final Map<String, Integer> tags = new TreeMap<>();

        HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {

            @Override
            public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
                addTag(tags, tag);
            }

            @Override
            public void handleEndTag(HTML.Tag tag, int position) {
                addTag(tags, tag);
            }

            @Override
            public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
                addTag(tags, tag);
            }
        };

        ParserDelegator parser = new ParserDelegator();
        parser.parse(reader, callback, true);
        reader.close();
        return tags;
    }

    private static void addTag(Map<String, Integer> tags, HTML.Tag tag) {
        String name = tag.toString().toLowerCase();

        if (tags.containsKey(name)) {
            tags.put(name, tags.get(name) + 1);
        } 
        else {
            tags.put(name, 1);
        }
    }

    public static void printAlphabetically(Map<String, Integer> tags) {
        System.out.println("\nHTML tags in alphabetical order:");

        for (String name : tags.keySet()) {
            System.out.println(name + " - " + tags.get(name));
        }
    }

    public static void printByFrequency(Map<String, Integer> tags) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(tags.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        System.out.println("\nHTML tags sorted by frequency:");

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
