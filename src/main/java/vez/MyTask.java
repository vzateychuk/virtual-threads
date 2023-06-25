package vez;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Integer> {

    private final int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public Integer call() {
        System.out.printf("Thread %s - Task %d waiting...%n", Thread.currentThread().getName(), taskId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s - Task %d canceled.%n", Thread.currentThread().getName(), taskId);
            return -1;
        }

        System.out.printf("Thread %s - Task %d finished.%n", Thread.currentThread().getName(), taskId);
        return taskId;
    }
}
