public class StaticMemoryAllocationExample {
    public static void main(String[] args) {
        System.out.println("=== Proof: Variables allocated in Stack ===\n");
        
        System.out.println("1. Variables exist:");
        testAllocation();
        
        System.out.println("\n2. Variables no longer exist (automatic deallocation)");
        // x, y, flag, letter no longer accessible here - they were deallocated
    }

    private static void testAllocation() {
        int x = 10;
        double y = 20.5;
        boolean flag = true;
        char letter = 'A';
        
        System.out.println("   x = " + x);
        System.out.println("   y = " + y);
        System.out.println("   flag = " + flag);
        System.out.println("   letter = " + letter);
    }
}
