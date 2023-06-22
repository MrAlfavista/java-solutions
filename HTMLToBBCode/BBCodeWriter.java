import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class BBCodeWriter {
    public static void write(final String content,final String outputFileName){
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8);
            String s = new NodeDefault(content).toBBCode();
            out.append(s);
            out.close();
        } catch (IOException e) {
            System.err.println("Problems with the output file");
        }
    }
}
