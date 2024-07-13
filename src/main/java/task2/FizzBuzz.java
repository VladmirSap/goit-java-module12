package task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FizzBuzz {
    private static final int n = 30; // Змінити на потрібне значення n
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(n);
    private static final CyclicBarrier barrier = new CyclicBarrier(4);

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(Fizz());
        Thread threadB = new Thread(Buzz());
        Thread threadC = new Thread(FizzBuzz());
        Thread threadD = new Thread(Number());

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
    }

    private static Runnable Fizz() {
        return () -> {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    try {
                        queue.put("fizz");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                awaitBarrier();
            }
        };
    }

    private static Runnable Buzz() {
        return () -> {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0 && i % 3 != 0) {
                    try {
                        queue.put("buzz");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                awaitBarrier();
            }
        };
    }

    private static Runnable FizzBuzz() {
        return () -> {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    try {
                        queue.put("fizzbuzz");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                awaitBarrier();
            }
        };
    }

    private static Runnable Number() {
        return () -> {
            for (int i = 1; i <= n; i++) {
                try {
                    if (i % 3 != 0 && i % 5 != 0) {
                        queue.put(Integer.toString(i));
                    }
                    awaitBarrier();
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

    private static void awaitBarrier() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}



