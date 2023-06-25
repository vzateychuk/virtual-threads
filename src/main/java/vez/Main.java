package vez;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int TASK_AMOUNT = 100_000;

        System.out.printf("Creating %d tasks%n", TASK_AMOUNT);
        List<MyTask> tasks = IntStream.range(0, TASK_AMOUNT)
                .mapToObj(MyTask::new)
                .toList();

        System.out.printf("Starting %d tasks%n", TASK_AMOUNT);

        /**
         * Uncomment for usage classical FixedThreadPool
         */
        // ExecutorService executor = Executors.newFixedThreadPool(256);

        /**
         * Uncomment for usage of new Virtual Thread-Per-Task
         */
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        long start = System.currentTimeMillis();
        List<Future<Integer>> taskFutures = executor.invokeAll(tasks);

        System.out.printf("Waiting %d tasks completed%n", TASK_AMOUNT);
        long sum = 0;
        for (Future<Integer> taskFuture: taskFutures) {
            sum += taskFuture.get();
        }
        long time = System.currentTimeMillis() - start;

        System.out.println("Completed, sum = " + sum + "; time = " + time + " ms");

        executor.shutdown();
    }
}