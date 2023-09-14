package com.kunning.javase.thread;

import com.google.common.collect.Lists;
import com.kunning.javase.pojo.MyRole;
import com.kunning.javase.pojo.MyTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest {
    //执行2个不相关的业务（两个业务没有依赖关系）
    @Test
    public void testRunAsync() throws ExecutionException, InterruptedException {
        long startT = System.currentTimeMillis();

        MyRole myRole = new MyRole();
        // runAsync 是不带返回值的任务，supplyAsync是带返回值的任务
        CompletableFuture<Void> roleNameC = CompletableFuture.runAsync(() -> {
            System.out.println("线程名称：" + Thread.currentThread().getName());
            try {
                Thread.sleep(6000);
                String roleName = "admin";
                myRole.setRoleName(roleName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> myTasksC = CompletableFuture.runAsync(() -> {
            System.out.println("线程名称：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
                ArrayList<MyTask> myTasks = Lists.newArrayList(new MyTask("上班"));
                myRole.setTaskList(myTasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //等待 roleNameC, myTasksC 这两个异步任务完成
        CompletableFuture.allOf(roleNameC, myTasksC).get();
        long costTime = System.currentTimeMillis() - startT;
        System.out.println("costTime = " + costTime);
        System.out.println(myRole);
    }

    @Test
    public void testSupplyAsync() throws ExecutionException, InterruptedException {
        long startT = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello";
        }, executorService);

        System.out.println("异步任务的返回值" + task.get());
    }


    /**
     * 功能描述：有顺序的任务
     * 参考：<a href="https://www.bilibili.com/video/BV1AN411S72m/?spm_id_from=333.337.search-card.all.click&vd_source=f2fb919142ce62e6571426a12817634e" />
     */
    @Test
    public void testSupplyAsyncOrder() throws ExecutionException, InterruptedException {
        long startT = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Supplier<String> task1 = () -> {
            System.out.println("任务1：" + Thread.currentThread().getName());
            sleep(3000);
            return "任务1的返回值";
        };

        Function<String, String> task2 = s -> {
            System.out.println("任务2：" + Thread.currentThread().getName());
            return s + "任务2的返回值";
        };

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(task1, executorService);
        sleep(1000); // 这行注释掉，任务1 和 任务2 是用的同一个线程执行的。
//        future1.thenApply(task2); // 这行代码的额意思是：task2 一定是在 task1 任务后执行，且是 task1 任务执行结束以后再执行task1。
//        future1.thenApplyAsync(task2); // 指定线程池来执行 task2
        future1.thenApplyAsync(task2, executorService); // 指定线程池来执行 task2

        System.out.println("主线程结束");
        sleep(5000);
    }

    @Test
    public void testCompose() {
        System.out.println("小白进入餐厅");
        System.out.println("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("厨师炒菜");
            sleep(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> { // dish 是上一个任务的输出，即：任务有先后顺序，第一个执行完了，出参作为第二个任务的入参。
            System.out.println("服务员打饭");
            sleep(100);
            return dish + " + 米饭";
        }));

        System.out.println("小白在打王者");
        System.out.println(String.format("%s 好了,小白开吃", cf1.join()));
    }

    @Test
    public void testComb() throws ExecutionException, InterruptedException {
        long startT = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1：" + Thread.currentThread().getName());
            sleep(3000);
            return "任务1的返回值";
        }, executorService);

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1：" + Thread.currentThread().getName());
            sleep(3000);
            return "任务2的返回值";
        }, executorService);

        // task1 和 task2 都执行完了，才会执行后面的118行的代码
        CompletableFuture<String> result = task1.thenCombine(task2, (result1, result2) -> {
            System.out.println("整合结果");
            return result1 + result2;
        });

        task1.runAfterEither(task2, () -> System.out.println("task1 和 task2 两个任务只要执行完了一个，后面的代码就会继续运行"));
        task1.runAfterBoth(task2, () -> System.out.println("task1 和 task2 两个任务全都执行完了，后面的代码就会继续运行"));

        System.out.println("主线程结束：" + result.get());
    }

    @Test
    public void testCombine() {
        System.out.println("小白进入餐厅");
        System.out.println("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("厨师炒菜");
            sleep(200);
            return "番茄炒蛋";
        }).thenCombine(CompletableFuture.supplyAsync(() -> { // 针对上一个方法的不同的写法
            System.out.println("服务员蒸饭");
            sleep(300);
            return "米饭";
        }), (dish, rice) -> {
            System.out.println("服务员打饭");
            sleep(100);
            return String.format("%s + %s 好了", dish, rice);
        });

        System.out.println("小白在打王者");
        System.out.printf("%s ,小白开吃%n", cf1.join());
    }


    //for循环执行异步操作 业务场景需要循环调用同一个接口 例如调用第三方接口，第三方接口每次只能返回100条数据，需调用10次才能拿到需要的数据还要求速度
    @Test
    public void testForRelation() {
        long startT = System.currentTimeMillis();

        List<CompletableFuture<?>> futures = new ArrayList<>();
        ArrayList<Object> objects = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            int j = i;
            futures.add(CompletableFuture.runAsync(() ->
                    {
                        try {
                            Thread.sleep(1000);
                            objects.add(j);
                            System.out.println("线程名称" + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            ));
        }
        // 等待全部完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join(); // join()必须写上，才有效果
        // 只要任何一个执行完了，就能往下执行
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).join(); // join()必须写上，才有效果
        long costTime = System.currentTimeMillis() - startT;
        System.out.println("costTime = " + costTime);
        System.out.println("objects = " + objects);
    }

    public void sleep(int num) {
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
