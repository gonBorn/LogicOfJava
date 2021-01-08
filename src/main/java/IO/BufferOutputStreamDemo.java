package IO;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class BufferOutputStreamDemo {
    public static void main(String[] args){
        final List<Student> students = asList(new Student("stu1", "male", 11), new Student("stu2", "female", 22));
        File file = new File("src/main/resources/student.txt");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for(Student s: students) {
                writer.write(s.getName() + "," + s.getAge() + "," + s.getGender());
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
