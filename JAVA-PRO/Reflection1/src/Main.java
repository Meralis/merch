import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Class<?> cls = TestClass.class;
        TestClass t = new TestClass();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                try {
                    method.invoke(t, annotation.param1(), annotation.param2());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}