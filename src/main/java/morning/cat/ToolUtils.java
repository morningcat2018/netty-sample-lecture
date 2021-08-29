package morning.cat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

/**
 * @describe: 工具类
 * @author: morningcat.zhang
 * @date: 2021/8/30 上午2:12
 */
public class ToolUtils {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(String info) {
        String str = new StringJoiner("\t|\t")
                .add(LocalDateTime.now().format(FORMATTER))
                .add("线程id:" + String.format("%3d", Thread.currentThread().getId()))
                .add("线程:" + String.format("%33s", Thread.currentThread().getName()))
                .add(info)
                .toString();
        System.out.println(str);
    }

    public static void throwError(String error) {
        throw new RuntimeException(error);
    }
}
