import java.util.ArrayList;

public class NodeB extends Node {
    public NodeB(String data) {
        super(data);
    }

    @Override
    public String toBBCode() {
        StringBuilder sb = new StringBuilder("[b]");
        ArrayList<Node> children = transform(splitIntoTokens(data));
        if (children.size() == 0) return "";
        if (children.size() == 1) {
            sb.append(children.get(0).data);
        } else {
            for (Node child : children) {
                sb.append(child.toBBCode());
            }
        }
        sb.append("[/b]");
        return sb.toString();
    }
}