import java.util.ArrayList;
import java.util.Arrays;

public abstract class Node {
    public String data;

    public Node(final String data) {
        this.data = data;
    }

    protected ArrayList<String> splitIntoTokens(final String data) {
        return new ArrayList<>(Arrays.asList(data.split("" +
                "(?<=<b>)|(?=<b>)|(?<=</b>)|(?=</b>)|" +
                "(?<=<em>)|(?=<em>)|(?<=</em>)|(?=</em>)|" +
                "(?<=<i>)|(?=<i>)|(?<=</i>)|(?=</i>)|" +
                "(?<=<s>)|(?=<s>)|(?<=</s>)|(?=</s>)|" +
                "(?<=<ol>)|(?=<ol>)|(?<=</ol>)|(?=</ol>)|" +
                "(?<=<ul>)|(?=<ul>)|(?<=</ul>)|(?=</ul>)"
        )));
    }

    protected ArrayList<Node> transform(ArrayList<String> tokens) {
        boolean nodeDefault = true;
        String currentToken = "";
        ArrayList<Node> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            if (nodeDefault) {
                if (!Main.TAGS_MAP.keySet().contains(tokens.get(i))) {
                    sb.append(tokens.get(i));
                }
                else {
                    result.add(new NodeDefault(sb.toString()));
                    sb.setLength(0);
                    nodeDefault = false;
                    currentToken = tokens.get(i);
                }
            } else {
                if (!Main.TAGS_MAP.get(currentToken).equals(tokens.get(i))) {
                    sb.append(tokens.get(i));
                } else {
                    switch (currentToken) {
                        case "<b>" -> result.add(new NodeB(sb.toString()));
                        case "<i>" -> result.add(new NodeI(sb.toString()));
                        case "<s>" -> result.add(new NodeS(sb.toString()));
                        case "<u>" -> result.add(new NodeU(sb.toString()));
                        case "<ol>" -> result.add(new NodeOl(sb.toString()));
                        case "<ul>" -> result.add(new NodeUl(sb.toString()));
                    }
                    sb.setLength(0);
                    nodeDefault = true;
                    currentToken = "";
                }
            }
        }
        if (sb.length() > 0) {
            result.add(new NodeDefault(sb.toString()));
        }
        return result;
    }

    abstract String toBBCode();
}