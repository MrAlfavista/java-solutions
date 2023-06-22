import java.util.ArrayList;

public class NodeOl extends Node {
    public NodeOl(String data) {
        super(data);
    }

    @Override
    public String toBBCode() {
        StringBuilder sb = new StringBuilder("[ol]");
        ArrayList<Node> children = transform(splitIntoTokens(data));
        if (children.size() == 0) return "";
        if (children.size() == 1) {
            sb.append(children.get(0).data);
        } else {
            for (Node child : children) {
                sb.append(child.toBBCode());
            }
        }
        sb.append("[/ol]");
        return sb.toString();
    }
}