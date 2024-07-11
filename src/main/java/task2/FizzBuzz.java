package task2;

public class FizzBuzz {
    private final int n;
    private int current = 1;
    private final Object lock = new Object();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 != 0 || current % 5 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) return;
                System.out.println("fizz");
                current++;
                lock.notifyAll();
            }
        }
    }

    public void buzz() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 5 != 0 || current % 3 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) return;
                System.out.println("buzz");
                current++;
                lock.notifyAll();
            }
        }
    }

    public void fizzbuzz() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 != 0 || current % 5 != 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) return;
                System.out.println("fizzbuzz");
                current++;
                lock.notifyAll();
            }
        }
    }

    public void number() {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 == 0 || current % 5 == 0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (current > n) return;
                System.out.println(current);
                current++;
                lock.notifyAll();
            }
        }
    }
}
