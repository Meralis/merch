import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Serializer {
    public void serialization(Object obj, String path) throws IOException, IllegalAccessException {
        Class<?> cls = Container.class;
        try (OutputStream os = new FileOutputStream(path)) {
            ByteBuffer buffer = null;
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Save.class)) {
                    continue;
                }
                int modifier = field.getModifiers();
                if (Modifier.isPrivate(modifier)) {
                    field.setAccessible(true);
                }
                if (field.getType() == String.class) {
                    byte[] stringBuffer = ((String) field.get(obj)).getBytes(StandardCharsets.UTF_8);
                    buffer = ByteBuffer.allocate(Integer.BYTES);
                    buffer.putInt(stringBuffer.length);
                    os.write(buffer.array());
                    buffer = ByteBuffer.wrap(stringBuffer);
                } else if (field.getType() == int.class) {
                    buffer = ByteBuffer.allocate(Integer.BYTES);
                    buffer.putInt(field.getInt(obj));
                }
                assert buffer != null;
                os.write(buffer.array());
            }
        }
    }

    public void deserialization(Object obj, String path) throws
            IOException, ClassNotFoundException, IllegalAccessException {
        Class<?> cls = Container.class;
        try (InputStream is = new FileInputStream(path)) {
            ByteBuffer buffer;
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Save.class)) {
                    continue;
                }
                int modifier = field.getModifiers();
                if (Modifier.isPrivate(modifier)) {
                    field.setAccessible(true);
                }
                if (field.getType() == String.class) {
                    int stringLength = (ByteBuffer.wrap(is.readNBytes(Integer.BYTES))).getInt();
                    field.set(obj, new String(is.readNBytes(stringLength)));
                } else if (field.getType() == int.class) {
                    buffer = ByteBuffer.wrap(is.readNBytes(Integer.BYTES));
                    field.setInt(obj, buffer.getInt());
                }
            }
        }
    }
}

