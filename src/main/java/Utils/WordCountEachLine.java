package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WordCountEachLine {
    public static void main(String[] args) throws IOException {
        // 获取要修改的文件
        File file = getFile();

        // 获取迭代器
        LineIterator lineIterator = FileUtils.lineIterator(file, FileUtil.CHARSET_UTF8);
        // lineIterator.forEachRemaining(StringUtils::lowerCase);

        // 写入临时文件
        File tempFile = new File(file.getParent(), file.getName() + ".tmp");

        // tempFile.createNewFile()可以创建文件
        // 在流打开的时候也会创建文件
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            while (lineIterator.hasNext()) {
                // 每一次writeStringToFile都会关闭流，因此不推荐下面的写法
                // FileUtils.writeStringToFile(tempFile, lineIterator.nextLine(), FileUtil.UTF_8, true);
                final String nextLine = StringUtils.lowerCase(lineIterator.nextLine());
                outputStream.write(nextLine.getBytes(UTF_8));
                outputStream.write(System.lineSeparator().getBytes(UTF_8));
            }
        }
    }

    private static File getFile() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file path:");
        String path = in.nextLine();
        in.close();
        return new File(path);
    }
}
