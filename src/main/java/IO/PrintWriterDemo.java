package IO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static java.util.Arrays.asList;

public class PrintWriterDemo {
    public static void main(String[] args) {
        List<Student> students = asList(new Student("stu1", "male", 11), new Student("stu2", "female", 22));
        try(PrintWriter printWriter = new PrintWriter("src/main/resources/student.txt", "utf8")) {
            for (Student s: students) {
                printWriter.printf("Stu name: %s, Stu gender: %s, Stu age: %d", s.getName(), s.getGender(), s.getAge());
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
