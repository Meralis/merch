import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Temp\\serialize.txt";
        Container container1 = new Container("The winter is coming", 24, 38.8);
        Container container2 = new Container();
        Serializer serializer = new Serializer();
        try {
            serializer.serialization(container1, path);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            serializer.deserialization(container2, path);
        } catch (IOException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(container1);
        System.out.println(container2);
    }
}
