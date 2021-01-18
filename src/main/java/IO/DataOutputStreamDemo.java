package IO;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// DataOutputStream 是装饰类基类FilterOutputStream的子类，它接受一个已有的OutputStream，可以将各种基本数据类型和字符串写入数据
public class DataOutputStreamDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student("duzeyan", "female", 23), new Student("zeyan", "female", 18));
        try (DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/main/resources/dataOutput.txt"))) {
            dataOutput.writeUTF("count of students: " + students.size() + File.separator);
            for (Student student: students) {
                dataOutput.writeUTF(student.getName());
                dataOutput.writeUTF(student.getGender());
                // writeInt写的是二进制
                dataOutput.writeInt(student.getAge());
            }
        } catch (IOException exception) {
            System.out.println("do nothing");
        }
    }
}
