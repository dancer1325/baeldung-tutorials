public class StackOverflowExample {
    private static int depth = 0;

    public static void main(String[] args) {
        try {
            recursiveMethod();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError at depth: " + depth);    // Reason: variables were stored | stack memory
        }
    }

    private static void recursiveMethod() {
        depth++;
        recursiveMethod();
    }
}
