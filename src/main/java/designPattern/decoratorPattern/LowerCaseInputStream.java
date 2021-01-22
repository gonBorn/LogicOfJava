package designPattern.decoratorPattern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// 装饰者和被装饰者有相同的父类
// 装饰者模式动态地将责任附加到对象上，提供了比继承更弹性的扩展方案
public class LowerCaseInputStream extends FileInputStream {
    public LowerCaseInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toLowerCase((char) c);
    }

    @Override
    public int read(byte[] b) throws IOException {
        int result = super.read(b);
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}