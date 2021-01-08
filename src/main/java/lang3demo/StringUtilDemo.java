package lang3demo;

import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilDemo {
    public static void main(String[] args) {
        String phone = "1582223333";
        //abbreviate
        assertEquals("158****33", StringUtils.abbreviateMiddle(phone, "****", 9));
        assertEquals("15...", StringUtils.abbreviate(phone, 5));

        //repeat
        assertEquals("****", StringUtils.repeat("*", 4));

        //containsAny
        assertTrue(StringUtils.containsAny(phone, "22", "2355"));

        //default
        String notBlank = StringUtils.defaultIfBlank(phone, "110");
        assertEquals(phone, notBlank);
        String blankString = StringUtils.defaultIfBlank(null, "110");
        assertEquals("110", blankString);

        //countMatches
        assertEquals(1, StringUtils.countMatches(phone, "22"));

        //subString
        assertEquals("zeyandu", StringUtils.substringBefore("zeyandu@163.com", "@"));
        assertEquals("zeyan", StringUtils.substring("zeyandu@163.com", 0, 5));

        //判空
        assertTrue(StringUtils.isAnyBlank("123", "   ", "432432"));

        //replace
        assertEquals("158***3333", StringUtils.replace(phone, "2", "*"));
        String replaceEach = StringUtils.replaceEach(phone, new String[]{"1", "2", "3"}, new String[]{"861", "#", "%"});
        assertEquals("86158###%%%%", replaceEach);

        //split
        String line = "a..b.c.....d";
        String[] split = StringUtils.split(line, ".");
        assertEquals(4, split.length);
    }
}
