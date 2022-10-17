import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Class<?> cls = TextContainer.class;
        TextContainer tc = new TextContainer();
        if (!cls.isAnnotationPresent(SaveTo.class)) {
            System.out.println("Annotation not found");
            return;
        }
        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        String pathTo = saveTo.path();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Saver.class)) {
                try {
                    method.invoke(tc, pathTo);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}