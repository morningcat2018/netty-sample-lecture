package morning.cat;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * 【Java并发·04】CompletableFuture进阶 {https://www.bilibili.com/video/BV1ui4y1T7xf/}
 *
 * @author: morningcat.zhang
 * @date: 2021/8/30 上午3:20
 * @see CompletableFuture
 */
public class CompletableFutureTest5 {

    public static void main(String[] args) {

        ToolUtils.printInfo("张三需要坐公交车回家");
        ToolUtils.printInfo("打开手机导航，发现在本站坐车回家 333 路和 340 路都可以");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ToolUtils.printInfo("333 路正在赶来");
            ToolUtils.sleep(new Random().nextInt(3000));
            return "333 路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            ToolUtils.printInfo("340 路正在赶来");
            ToolUtils.sleep(new Random().nextInt(3000));
            return "340 路到了";
        }), Function.identity()).exceptionally(throwable -> throwable.getMessage());

        ToolUtils.printInfo(String.format("%s，张三上车准备回家了", future.join())); // join 阻塞等待结果
    }
}
