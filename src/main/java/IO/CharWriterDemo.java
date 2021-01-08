package IO;

import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharWriterDemo {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream("src/main/resources/hello.txt"), "utf8");
        char[] readChars = new char[1024];
        int readLen;
        CharArrayWriter writer = new CharArrayWriter();
        while ((readLen = reader.read(readChars)) != -1) {
            writer.write(readChars, 0, readLen);
        }
        System.out.println(writer.toCharArray());
    }
}
