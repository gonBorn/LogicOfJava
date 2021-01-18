package IO;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class ByteOutputDemo {
    public static void main(String[] args) {
        // 通过ByteOutputArray改进通过FileInputStream读文件的代码
        try(FileInputStream fileInputStream = new FileInputStream("src/main/resources/student.txt"); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            int readLength = 0;
            while ((readLength = fileInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, readLength);
            }
            // toString可以指定编码，默认使用系统默认编码
            String s = byteArrayOutputStream.toString();
            System.out.println(s);
            // ByteArrayOutputStream的数据也可以方便地写入其他OutputStream
//            byteArrayOutputStream.writeTo(new FileOutputStream(""));
            // size 可以返回当前写入的字节数
            int size = byteArrayOutputStream.size();
            System.out.println(String.format(Locale.ENGLISH, "output stream has read %d bytes", size));
            // reset重置字节个数为0
            byteArrayOutputStream.reset();
        } catch (IOException exception) {
            System.out.println("do nothing");
        }
    }
}
