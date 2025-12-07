public class ThreadStackExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int threadVar = 100;    // Thread1's value
            process(threadVar);
        });

        Thread thread2 = new Thread(() -> {
            int threadVar = 200;    // Thread2's value
            process(threadVar);
        });

        thread1.start();
        thread2.start();
    }

    private static void process(int value) {
        int localVar = value * 2;
    }
}
