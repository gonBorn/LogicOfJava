package cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnhancerDemo {
    public static void main(String[] args) {
        testFixedValue();
        testMethodInterceptor();
        testCallbackFilter();
    }

    private static void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue)() -> "Hello zeyan");
        PersonService proxy = (PersonService) enhancer.create();
        assertEquals("Hello zeyan", proxy.sayHello(null));
    }

    private static void testMethodInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor)(object, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello zeyan";
            } else {
                System.out.println("do nothing");
                 return proxy.invokeSuper(object, args);
            }
        });
        PersonService proxy = (PersonService) enhancer.create();
        assertEquals("Hello zeyan", proxy.sayHello(null));
        proxy.lengthOfName("zeyan");
    }

    private static void testCallbackFilter() {
        Callback[] callbacks = {(MethodInterceptor) (object, method, args, proxy) -> "Hello zeyan", NoOp.INSTANCE};
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilterImpl());
        PersonService proxy = (PersonService) enhancer.create();
        assertEquals("Hello zeyan", proxy.sayHello(null));
    }

    private static class CallbackFilterImpl implements CallbackFilter {
        @Override
        public int accept(Method method) {
            switch (method.getName()) {
                case "sayHello":
                    return 0;
                default:
                    return 1;
            }
        }
    }
}
