public class StackGrowthExample {
    public static void main(String[] args) {
        System.out.println("=== Stack Growth Demo ===\n");
        int depth = Thread.currentThread().getStackTrace().length;
        System.out.println("main - BEFORE method1() call - Stack depth: " + depth);
        method1();
        System.out.println("main - AFTER method1() call - Stack depth: " + depth);
    }

    private static void method1() {
        int depth = Thread.currentThread().getStackTrace().length;
        System.out.println("method1 - BEFORE method2() call - Stack depth: " + depth);
        method2();
        System.out.println("main - AFTER method2() call - Stack depth: " + depth);
    }

    private static void method2() {
        int depth = Thread.currentThread().getStackTrace().length;
        System.out.println("method1 - BEFORE method3() call - Stack depth: " + depth);
        method3();
        System.out.println("main - AFTER method3() call - Stack depth: " + depth);
    }

    private static void method3() {
        int depth = Thread.currentThread().getStackTrace().length;
        System.out.println("method3 - Stack depth: " + depth + " (GREW MORE)");
    }
}
