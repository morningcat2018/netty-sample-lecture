package morning.cat;

import java.util.concurrent.CompletableFuture;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/30 下午11:01
 */
public class ApiTest {

    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> {
            System.out.println("helo");
        });
        CompletableFuture.supplyAsync(() -> "hello");

        CompletableFuture.supplyAsync(() -> "hello").thenApplyAsync(t -> t + "w");
        CompletableFuture.supplyAsync(() -> "hello").thenAcceptAsync(t -> {
        });
        CompletableFuture.supplyAsync(() -> "hello").thenRunAsync(() -> {
        });

        CompletableFuture.supplyAsync(() -> "hello").thenCombine(CompletableFuture.completedFuture("world"), (x, y) -> x + y);
        CompletableFuture.supplyAsync(() -> "hello").thenAcceptBoth(CompletableFuture.supplyAsync(() -> "world"), (x, y) -> {
        });
        CompletableFuture.supplyAsync(() -> "hello").runAfterBoth(CompletableFuture.supplyAsync(() -> "world"), () -> {
        });

        CompletableFuture.supplyAsync(() -> "hello").applyToEitherAsync(CompletableFuture.completedFuture("world"), x -> x);
        CompletableFuture.supplyAsync(() -> "hello").acceptEitherAsync(CompletableFuture.supplyAsync(() -> "world"), x -> {
        });
        CompletableFuture.supplyAsync(() -> "hello").runAfterEitherAsync(CompletableFuture.supplyAsync(() -> "world"), () -> {
        });

        CompletableFuture.supplyAsync(() -> "hello").exceptionally(e -> null);
        CompletableFuture.supplyAsync(() -> "hello").handle((t, e) -> {
            if (e != null) {
                e.printStackTrace();
                return e.getMessage();
            } else {
                return t + "world";
            }
        });
        CompletableFuture.supplyAsync(() -> "hello").whenComplete((t, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                System.out.println(t);
            }

        });
    }
}
