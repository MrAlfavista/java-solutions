import java.util.ArrayList;

public class NodeDefault extends Node {

    public NodeDefault(String data) {
        super(data);
    }

    @Override
    public String toBBCode() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Node> children = transform(splitIntoTokens(data));
        if (children.size() == 0) return "";
        if (children.size() == 1) return children.get(0).data;
        for (Node child : children) {
            sb.append(child.toBBCode());
        }
        return sb.toString();
    }
}