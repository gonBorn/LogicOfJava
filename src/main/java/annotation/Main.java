package annotation;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static final String COLON = " : ";

    public static void main(String[] args) throws IllegalAccessException {
        SampleEntity entity = new SampleEntity("duzeyan", new Date());
        System.out.println(entityToString(entity));
    }

    public static String entityToString(Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : declaredFields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Label label = field.getAnnotation(Label.class);
            DatePattern datePattern = field.getAnnotation(DatePattern.class);
            Object value = field.get(obj);
            String fieldString = value.toString();

            if (datePattern != null && value instanceof Date) {
                fieldString = getFormattedTime(datePattern.value(), value);
            }

            getNewLine(label != null ? label.value() : field.getName(), fieldString, stringBuilder);
        }
        return stringBuilder.toString();
    }

    private static void getNewLine(String key, String value, StringBuilder stringBuilder) {
        stringBuilder.append(key).append(COLON).append(value).append(System.lineSeparator());
    }

    private static String getFormattedTime(String format, Object value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(value);
    }
}
