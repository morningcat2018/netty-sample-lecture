package morning.cat;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * 【Java并发·05】CompletableFuture扩展 {https://www.bilibili.com/video/BV1wZ4y1A7PK}
 *
 * @author: morningcat.zhang
 * @date: 2021/8/30 上午3:37
 * @see CompletableFuture
 */
public class CompletableFutureTest6 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "x");
        CompletableFuture.supplyAsync(() -> "x").thenCompose(t -> CompletableFuture.supplyAsync(() -> t + "y"));
        CompletableFuture.supplyAsync(() -> "x").thenComposeAsync(t -> CompletableFuture.supplyAsync(() -> t + "y")).exceptionally(throwable -> throwable.getMessage());
        CompletableFuture.supplyAsync(() -> "x").thenCombine(CompletableFuture.supplyAsync(() -> "y"), (x, y) -> x + y);
        CompletableFuture.supplyAsync(() -> "x").thenApply(t -> t + "y");
        CompletableFuture.supplyAsync(() -> "x").applyToEither(CompletableFuture.supplyAsync(() -> "y"), Function.identity());
    }
}
