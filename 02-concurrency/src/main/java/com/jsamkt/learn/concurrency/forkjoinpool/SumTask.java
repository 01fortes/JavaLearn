package com.jsamkt.learn.concurrency.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD = 100;
    private final long start;
    private final long end;

    SumTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Base case: compute directly
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // Recursive case: split the task
            long mid = start + (end - start) / 2;
            SumTask leftTask = new SumTask(start, mid);
            SumTask rightTask = new SumTask(mid + 1, end);

            leftTask.fork(); // Submit left task
            long rightResult = rightTask.compute(); // Compute right task
            long leftResult = leftTask.join(); // Wait for left task

            return leftResult + rightResult;
        }
    }
}
