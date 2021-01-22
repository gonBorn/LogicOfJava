package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class AddCopyRightMain {
    public static final String JAVA = "java";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the directory path:");
        String path = in.nextLine();
        System.out.println("Please enter the copyright:");
        String copyright = in.nextLine();
        System.out.println("###### Adding copyright task start. ######");
        AddCopyRightToJavaFile(path, copyright);
    }

    private static void AddCopyRightToJavaFile(String path, String copyright) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Path " + file + " does not exist");
        }
        FileUtil.setFileWritable(file);
        traverseFiles(file, copyright);
    }

    private static void traverseFiles(final File directory, String copyright) {
        if (directory.isFile()) {
            setCopyRight(directory, copyright);
        } else {
            // FileNameFilter比FileFilter多一个参数：name子文件名
            FilenameFilter filter = (dir, name) -> dir.isDirectory() || name.endsWith("." + JAVA);
            File[] files = directory.listFiles(filter);
            for (File file: files) {
                traverseFiles(file, copyright);
            }
        }
    }

    private static void setCopyRight(File file, String copyright) {
        final boolean isCopyRightExist = checkIfCopyRightExist(file);
        if (isCopyRightExist) {
            System.out.println(file + " already has copyright.");
            return;
        }
        try {
            // 创建临时文件
            File tempFile = File.createTempFile("TempFile4Copyright", null);
            // 在临时文件中写入copyright, 覆盖之前的临时文件
            FileUtils.writeStringToFile(tempFile, copyright, FileUtil.UTF_8, false);
            FileUtil.insertNewLine(tempFile);
            // 再将原来没有copyright的文件逐行写入临时文件
            FileUtil.copyFile(file, tempFile, true);
            // 将临时文件覆盖原文件
            FileUtils.copyFile(tempFile, file);
            System.out.println("### Success to write copyright to " + file);
        } catch (IOException exception) {
            System.out.println("### Fail to write copyright to file: " + file);
        }
    }

    private static boolean checkIfCopyRightExist(File file) {
        try(LineIterator iterator = FileUtils.lineIterator(file, FileUtil.UTF_8)) {
            while (iterator.hasNext()) {
                final String line = iterator.nextLine().trim();
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                return !(StringUtils.startsWith(line, "package") || StringUtils.startsWith(line, "import"));
            }
        } catch (IOException e) {
            System.out.printf(Locale.ENGLISH, "### Fail to check file <%s> ###", file.getName());
            System.out.println();
        }
        return false;
    }
}