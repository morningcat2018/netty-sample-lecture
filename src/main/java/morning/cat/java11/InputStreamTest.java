package morning.cat.java11;


import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamTest {

    @Test
    public void test() throws Exception {
        var cl = this.getClass().getClassLoader();
        try (InputStream is = cl.getResourceAsStream("1.txt");
             OutputStream os = new FileOutputStream("2.txt")) {
            // @since 9
            is.transferTo(os); // 把输入流中的所有数据直接自动地复制到输出流中
        }
    }
}
