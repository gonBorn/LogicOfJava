# cglib

java的类在运行时被动态加载，cglib利用这一特性，向运行中的java程序添加新的类

## Enhancer

既能代理接口，也能代理普通的类

PersonService.java

```java
public class PersonService {
	public String sayHello(String name) {
		return "Hello" + name;
	}

	public Integer lengthOfName(String name) {
		return name.length();
	}
} 
```

通过Enhancer类的setSuperclass()方法我们能动态创建一个继承PersonService类的代理

setCallback代理public非final方法，包括toString()，hashcode()

### FixedValue

FixedValue用于对所有拦截方法返回相同的值

如果我们只想监听sayHello方法（intercept a call to sayHello）

```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(PersonService.class);
enhancer.setCallback((FixedValue)()->"Hello Tom");
PersonService proxy = (PersonService)enhancer.create();
assertEquals("Hello Tom", proxy.sayHello(null));
```

### MethodInterceptor

使用MethodInterceptor，MethodInterceptor中没有被代理的对象，通过MethodProxy.invokeSuper方法调用被代理类的方法

**(MethodInterceptor)(obj, method, args, proxy)** ->{}

**Object res = proxy.invokeSuper(object, args);**

object是代理对象

```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(PersonService.class);
enhancer.setCallback((MethodInterceptor)(obj, method, args, proxy) -> {
  if(method.getDecleringClass() != Object.class && method.getReturnType() == String.class) {
    return "Hello Tom!";
  } else {
    return proxy.invokeSuper(obj, args);
  }
});
PersonService proxy = (PersonService) enhancer.create();
assertEquals("Hello Tom!", proxy.sayHello(null));
```

### callbackFilter

只想对特定的方法进行拦截

CallbackFilter可以实现不同的方法使用不同的回调方法

CallbackFilter中的accept方法, 根据不同的method返回不同的值i, 这个值是在callbacks中的顺序, 就是调用了callbacks[i]

**NoOp.INSTANCE**

```java
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
 
```

## BeanCreator

