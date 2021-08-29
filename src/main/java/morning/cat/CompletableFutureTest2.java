package morning.cat;

import java.util.concurrent.CompletableFuture;

/**
 * 【Java并发·03】CompletableFuture入门 {https://www.bilibili.com/video/BV1nA411g7d2}
 *
 * @author: morningcat.zhang
 * @date: 2021/8/30 上午2:40
 * @see CompletableFuture
 */
public class CompletableFutureTest2 {

    public static void main(String[] args) {

        ToolUtils.printInfo("张三进入餐馆");
        ToolUtils.printInfo("张三点餐：番茄炒蛋 + 米饭");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ToolUtils.printInfo("厨师炒菜");
            ToolUtils.sleep(3000);
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            //ToolUtils.throwError("饭店没饭了");
            ToolUtils.printInfo("服务员盛饭");
            ToolUtils.sleep(1000);
            return dish + " 米饭 端上来了";
        })).exceptionally(throwable -> {
            // log.error
            return throwable.getMessage();
        });

        ToolUtils.printInfo("张三刷B站等饭");
        ToolUtils.printInfo(String.format("%s，张三刷着B站...", future.join())); // join 阻塞等待结果
    }
}
