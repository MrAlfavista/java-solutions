import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    //Main - последний пункт задания
    //ol & ul считаю просто токенами, так как не было сказано включать в разбор токены <li>

    static final Map<String, String> TAGS_MAP = initMap();

    static Map<String, String> initMap() {
        Map<String, String> map = new HashMap<>();
        map.put("<b>", "</b>");
        map.put("<u>", "</u>");
        map.put("<i>", "</i>");
        map.put("<s>", "</s>");
        map.put("<ol>", "</ol>");
        map.put("<ul>", "</ul>");
        return map;
    }

    public static void main(String[] args) {
        try {
            fileHandler(args[0], args[1]);
        } catch (IOException e) {
            System.err.println("Error in input/output file, or in path to them.");
        }
    }

    private static void fileHandler(String inputFileName, String outputFileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(inputFileName)), StandardCharsets.UTF_8);
        BBCodeWriter.write(content, outputFileName);
    }
}