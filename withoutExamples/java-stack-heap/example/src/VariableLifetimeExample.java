public class VariableLifetimeExample {
    public static void main(String[] args) {
        System.out.println("=== Variable Lifetime in Stack ===\n");
        
        System.out.println("Before calling method:");
        // int x = localVar;  // uncomment to see - ERROR: Cannot access - method not running
        
        createVariables();
        
        System.out.println("\nAfter method returns:");
        // int y = localVar;  // uncomment to see -  ERROR: Cannot access - method finished
        System.out.println("Variables no longer exist");
    }

    private static void createVariables() {
        int localVar = 100;
        String localStr = "Stack";
        
        System.out.println("Inside method - variables exist:");
        System.out.println("  localVar = " + localVar);
        System.out.println("  localStr = " + localStr);
    }
}
