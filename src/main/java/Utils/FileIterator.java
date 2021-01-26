package Utils;

import java.io.*;
import java.util.Iterator;

public class FileIterator implements Iterator<String>, Serializable {
    private File file;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    public FileIterator(File file) throws FileNotFoundException {
        this.file = file;
    }


}
