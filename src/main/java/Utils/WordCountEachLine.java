package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordCountEachLine {
    public static void main(String[] args) throws IOException {
        // 获取要修改的文件
        File file = getFile();
        FileUtil.setFileWritable(file);

        // 获取迭代器
        LineIterator lineIterator = FileUtils.lineIterator(file, FileUtil.UTF_8);
        lineIterator.forEachRemaining(StringUtils::lowerCase);

        // 写入临时文件
        File tempFile = new File(file.getParent(), file.getName() + ".tmp");
        System.out.println();
        while (lineIterator.hasNext()) {
            FileUtils.writeStringToFile(tempFile, lineIterator.nextLine(), FileUtil.UTF_8, true);
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
