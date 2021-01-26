package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtil {
    public static final String WRAP = System.lineSeparator();
    public static final String CHARSET_UTF8 = UTF_8.toString();

    public static void copyFile(File source, File target, boolean append) {
        try (LineIterator iterator = FileUtils.lineIterator(source, CHARSET_UTF8)) {
            while (iterator.hasNext()) {
                final String line = iterator.nextLine();
                // writeStringToFile写文件不会换行
                FileUtils.writeStringToFile(target, line, CHARSET_UTF8, append);
                insertNewLine(target);
            }
        } catch (IOException e) {
            System.out.print("### Fail to copy " + source + " to " + target + " ###");
            System.out.println();
        }
    }

    public static void insertNewLine(File target) throws IOException {
        FileUtils.writeStringToFile(target, WRAP, CHARSET_UTF8, true);
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

    public static File isFileExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Path " + file + " does not exist");
        }
        return file;
    }
}
