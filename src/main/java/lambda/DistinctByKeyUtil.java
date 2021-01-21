package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DistinctByKeyUtil {
    /**
     * 按照对象的某个属性去重
     *
     * @param keyExtractor 表达式，如Person::getAge
     * @param <T> 对象或其父类的class
     * @return Predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> seen = new ConcurrentHashMap<>();
        // keyExtractor.apply(t) 用于将person对象转换成age
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person(1, "du"), new Person(1, "ze"));
        System.out.println(people);

        List<Person> collect = people.stream()
          .filter(distinctByKey(Person::getAge))
          .collect(Collectors.toList());
        System.out.println(collect);
    }
}
