public class StackHeapReferenceExample {
    public static void main(String[] args) {
        Person person = new Person(1, "Alice");  // <- BREAKPOINT HERE
        modifyPerson(person);
        System.out.println("After modify: " + person.name);
    }

    private static void modifyPerson(Person p) {
        p.id = 2;  // <- BREAKPOINT HERE TOO
        p.name = "Bob";
    }
}
