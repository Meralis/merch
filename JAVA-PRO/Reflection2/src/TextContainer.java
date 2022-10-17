import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "c://temp//ref.txt")
public class TextContainer {

    String text = "It's a sunny day";

    @Saver
    public void textWriter(String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(text);
        }
    }
}