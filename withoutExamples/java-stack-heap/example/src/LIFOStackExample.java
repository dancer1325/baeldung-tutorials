public class LIFOStackExample {
    public static void main(String[] args) {
        System.out.println("=== LIFO Stack Order Demo ===\n");
        method1();
    }

    private static void method1() {
        int var1 = 1;
        System.out.println("PUSH: method1 (var1=" + var1 + ")");
        method2();
        System.out.println("POP: method1");
    }

    private static void method2() {
        int var2 = 2;
        System.out.println("PUSH: method2 (var2=" + var2 + ")");
        method3();
        System.out.println("POP: method2");
    }

    private static void method3() {
        int var3 = 3;
        System.out.println("PUSH: method3 (var3=" + var3 + ")");
        System.out.println("POP: method3");
    }
}
