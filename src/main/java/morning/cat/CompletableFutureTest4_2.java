package morning.cat;

import java.util.concurrent.CompletableFuture;

/**
 * 【Java并发·04】CompletableFuture进阶 {https://www.bilibili.com/video/BV1ui4y1T7xf/}
 *
 * @author: morningcat.zhang
 * @date: 2021/8/30 上午2:58
 * @see CompletableFuture
 */
public class CompletableFutureTest4_2 {

    public static void main(String[] args) {

        ToolUtils.printInfo("张三吃完饭");
        ToolUtils.printInfo("张三结账，要求开发票");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ToolUtils.printInfo("收银员收款30元");
            ToolUtils.sleep(1000);
            return 30;
        }).thenApplyAsync((money) -> {
            ToolUtils.printInfo("收银员B 开发票");
            ToolUtils.sleep(2000);
            return String.format("%d元的发票", money);
        }).exceptionally(throwable -> throwable.getMessage());

        ToolUtils.printInfo("张三刷B站等发票");
        ToolUtils.printInfo(String.format("张三拿到了%s，回家了", future.join())); // join 阻塞等待结果
    }
}
