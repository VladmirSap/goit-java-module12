package task2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FizzBuzz {
    private int n;
    private int current = 1;
    private CyclicBarrier barrier = new CyclicBarrier(4);

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        while (current <= n) {
            if (current % 3 == 0 && current % 5 != 0) {
                printFizz();
            } else {
                awaitBarrier();
            }
        }
    }

    public void buzz() {
        while (current <= n) {
            if (current % 5 == 0 && current % 3 != 0) {
                printBuzz();
            } else {
                awaitBarrier();
            }
        }
    }

    public void fizzbuzz() {
        while (current <= n) {
            if (current % 3 == 0 && current % 5 == 0) {
                printFizzBuzz();
            } else {
                awaitBarrier();
            }
        }
    }

    public void number() {
        while (current <= n) {
            if (current % 3 != 0 && current % 5 != 0) {
                printNumber();
            } else {
                awaitBarrier();
            }
        }
    }

    private void printFizz() {
        synchronized (this) {
            System.out.println("fizz");
            current++;
        }
    }

    private void printBuzz() {
        synchronized (this) {
            System.out.println("buzz");
            current++;
        }
    }

    private void printFizzBuzz() {
        synchronized (this) {
            System.out.println("fizzbuzz");
            current++;
        }
    }

    private void printNumber() {
        synchronized (this) {
            System.out.println(current);
            current++;
        }
    }

    private void awaitBarrier() {
        try {
            barrier.await(); // Wait for other threads
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
