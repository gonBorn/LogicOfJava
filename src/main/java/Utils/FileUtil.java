package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static final String WRAP = "\r\n";
    public static final String UTF_8 = "UTF-8";

    public static void copyFile(File source, File target, boolean append) {
        try(LineIterator iterator = FileUtils.lineIterator(source, UTF_8)) {
            while (iterator.hasNext()) {
                final String line = iterator.nextLine();
                // writeStringToFile写文件不会换行
                FileUtils.writeStringToFile(target, line, UTF_8, append);
                insertNewLine(target);
            }
        } catch (IOException e) {
            System.out.print("### Fail to copy " + source + " to "+ target + " ###");
            System.out.println();
        }
    }

    public static void insertNewLine(File target) throws IOException {
        FileUtils.writeStringToFile(target, WRAP, UTF_8, true);
    }

    public static void setFileWritable(File file) {
        if (!file.canWrite()) {
            boolean setWritable = file.setWritable(true);
            if (!setWritable) {
                throw new RuntimeException(file + " cannot be set writable.");
            }
        }
        System.out.println(file + " can be written.");
    }
}