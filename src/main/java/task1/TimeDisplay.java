package task1;

public class TimeDisplay {
    public static void main(String[] args) {
        TimeDisplay program = new TimeDisplay();

        Thread timeThread = new Thread(new TimePrinter());
        timeThread.start();

        Thread messageThread = new Thread(new MessagePrinter());
        messageThread.start();
    }

    private static class TimePrinter implements Runnable {
        @Override
        public void run() {
            try {
                int seconds = 0;
                while (true) {
                    Thread.sleep(1000);
                    seconds++;
                    System.out.println("Пройшло: " + seconds + " сек");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MessagePrinter implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
