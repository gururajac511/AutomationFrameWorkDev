package others.unde.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTest3 {

	
	@Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public static  @interface MyAnnotation {
        int value() default 0;
    }

    public static class C {

        @MyAnnotation(value = 1)
        public void f1() { g(); }

        @MyAnnotation(value = -1)
        public void f2() {
            g();
        }
    }

    public static class X {
        @MyAnnotation(value = 1)
        public static void x1(int param) { 
        	System.out.println("Param1: " + param);}

        @MyAnnotation(value = -1)
        public static void x2(int param) { System.out.println("Param2: " + param); }
    }


    private static void g() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement traceElement = stackTraceElements[3];
        try {
            Class<?> type = Class.forName(traceElement.getClassName());
            String methodName = traceElement.getMethodName();
            Method method = type.getMethod(methodName);
            System.out.println(methodName + ": " +
                    method.getAnnotation(MyAnnotation.class).value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void boo() throws InvocationTargetException, IllegalAccessException {
        for (Method m : X.class.getDeclaredMethods()) {
            MyAnnotation annotation = m.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                m.invoke(null, annotation.value());
            }
        }
    }

    public static void main(String[] args)
            throws InvocationTargetException, IllegalAccessException {
        C c = new C();
        c.f1();
        c.f2();

        boo();
    }
	
}
