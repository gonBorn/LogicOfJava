package generictype;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class GuavaDemo {
    public static final Joiner joiner = Joiner.on(",").useForNull("null");

    public static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
    public static void main(String[] args) {

    }
}
