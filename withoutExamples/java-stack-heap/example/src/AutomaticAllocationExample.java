public class AutomaticAllocationExample {
    public static void main(String[] args) {
        System.out.println("=== Automatic Allocation/Deallocation ===\n");
        
        System.out.println("1. Before method call - no variables allocated");
        
        allocateVariables();
        
        System.out.println("3. After method returns - variables automatically deallocated");        // set a breakpoint to check
        System.out.println("   (no manual cleanup needed)\n");
        
        allocateVariables();
        
        System.out.println("5. Variables deallocated again automatically"); // set a breakpoint to check
    }

    private static void allocateVariables() {
        int x = 10;
        double y = 20.5;
        String str = "test";
        
        System.out.println("2. Inside method - variables AUTOMATICALLY allocated:");
        System.out.println("   x = " + x + ", y = " + y + ", str = " + str); // set a breakpoint to check
    }
}
