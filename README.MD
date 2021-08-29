

# java.util.concurrent.CompletableFuture

## supplyAsync

开启异步线程，执行任务

## thenCompose

thenCompose 会把前一步任务执行的结果交给下一个异步任务；
`等待`前一个任务执行完成后`才会触发`执行当前任务

```
2021-08-30 02:43:54	|	线程id:  1	|	线程:                             main	|	张三进入餐馆
2021-08-30 02:43:54	|	线程id:  1	|	线程:                             main	|	张三点餐：番茄炒蛋 + 米饭
2021-08-30 02:43:54	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	厨师炒菜
2021-08-30 02:43:54	|	线程id:  1	|	线程:                             main	|	张三刷B站等饭
2021-08-30 02:43:57	|	线程id: 15	|	线程: ForkJoinPool.commonPool-worker-5	|	服务员盛饭
2021-08-30 02:43:58	|	线程id:  1	|	线程:                             main	|	番茄炒蛋 米饭 端上来了，张三刷着B站...
```

## thenCombine

thenCombine 把上一个任务和当前任务`同时`异步执行，并将两个任务的结果都出来后再进行整合然后返回

```
2021-08-30 02:52:45	|	线程id:  1	|	线程:                             main	|	张三进入餐馆
2021-08-30 02:52:45	|	线程id:  1	|	线程:                             main	|	张三点餐：番茄炒蛋 + 米饭
2021-08-30 02:52:45	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	厨师炒菜
2021-08-30 02:52:45	|	线程id: 14	|	线程: ForkJoinPool.commonPool-worker-5	|	服务员使用电饭煲蒸饭
2021-08-30 02:52:45	|	线程id:  1	|	线程:                             main	|	张三刷B站等饭
2021-08-30 02:52:50	|	线程id: 14	|	线程: ForkJoinPool.commonPool-worker-5	|	服务员盛饭
2021-08-30 02:52:51	|	线程id:  1	|	线程:                             main	|	番茄炒蛋 米饭 都好了，张三刷着B站...
```

## thenApply

thenApply 等待前一步的异步结果，然后进行处理

```
2021-08-30 03:09:43	|	线程id:  1	|	线程:                             main	|	张三吃完饭
2021-08-30 03:09:43	|	线程id:  1	|	线程:                             main	|	张三结账，要求开发票
2021-08-30 03:09:43	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	收银员收款30元
2021-08-30 03:09:43	|	线程id:  1	|	线程:                             main	|	张三刷B站等发票
2021-08-30 03:09:44	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	收银员开发票
2021-08-30 03:09:46	|	线程id:  1	|	线程:                             main	|	张三拿到了30元的发票，回家了
```

thenApplyAsync 再开启一个异步线程

thenApplyAsync 与 thenCompose 类似，此处可以使用 thenCompose 替换

```
2021-08-30 03:14:11	|	线程id:  1	|	线程:                             main	|	张三吃完饭
2021-08-30 03:14:11	|	线程id:  1	|	线程:                             main	|	张三结账，要求开发票
2021-08-30 03:14:11	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	收银员收款30元
2021-08-30 03:14:11	|	线程id:  1	|	线程:                             main	|	张三刷B站等发票
2021-08-30 03:14:12	|	线程id: 15	|	线程: ForkJoinPool.commonPool-worker-5	|	收银员B 开发票
2021-08-30 03:14:14	|	线程id:  1	|	线程:                             main	|	张三拿到了30元的发票，回家了
```


## applyToEither

applyToEither 上个任务和这个任务同时执行，哪一个先执行完成就获取哪个任务的结果

```
2021-08-30 03:23:42	|	线程id:  1	|	线程:                             main	|	张三需要坐公交车回家
2021-08-30 03:23:42	|	线程id:  1	|	线程:                             main	|	打开手机导航，发现在本站坐车回家 333 路和 340 路都可以
2021-08-30 03:23:42	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	333 路正在赶来
2021-08-30 03:23:42	|	线程id: 14	|	线程: ForkJoinPool.commonPool-worker-5	|	340 路正在赶来
2021-08-30 03:23:43	|	线程id:  1	|	线程:                             main	|	333 路到了，张三上车准备回家了
```

## exceptionally

异常处理

```
2021-08-30 03:34:01	|	线程id:  1	|	线程:                             main	|	张三需要坐公交车回家
2021-08-30 03:34:01	|	线程id:  1	|	线程:                             main	|	打开手机导航，发现在本站坐车回家 333 路和 340 路都可以
2021-08-30 03:34:01	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	333 路正在赶来
2021-08-30 03:34:01	|	线程id: 14	|	线程: ForkJoinPool.commonPool-worker-5	|	340 路正在赶来
2021-08-30 03:34:01	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	333 路到了
2021-08-30 03:34:01	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	java.lang.RuntimeException: 公交车撞树了
2021-08-30 03:34:01	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	打开手机呼叫快的
2021-08-30 03:34:01	|	线程id:  1	|	线程:                             main	|	快的到了，张三上车准备回家了
```

```
2021-08-30 03:35:11	|	线程id:  1	|	线程:                             main	|	张三需要坐公交车回家
2021-08-30 03:35:11	|	线程id:  1	|	线程:                             main	|	打开手机导航，发现在本站坐车回家 333 路和 340 路都可以
2021-08-30 03:35:11	|	线程id: 13	|	线程: ForkJoinPool.commonPool-worker-3	|	333 路正在赶来
2021-08-30 03:35:11	|	线程id: 14	|	线程: ForkJoinPool.commonPool-worker-5	|	340 路正在赶来
2021-08-30 03:35:11	|	线程id: 14	|	线程: ForkJoinPool.commonPool-worker-5	|	340 路到了
2021-08-30 03:35:11	|	线程id:  1	|	线程:                             main	|	340 路到了，张三上车准备回家了
```


## 【Java并发·05】CompletableFuture扩展 --- 总结

- supplyAsync           开启异步任务
- thenCompose           连接两个异步任务 
- thenComposeAsync      ??
- thenCombine           合并两个异步任务 
- thenCombineAsync      ??
- thenApply             做任务的后置处理
- thenApplyAsync        做任务的后置处理，异步执行
- applyToEither         获取最先执行完成的任务





