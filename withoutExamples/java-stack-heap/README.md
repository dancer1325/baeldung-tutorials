https://www.baeldung.com/java-stack-heap

* goal
  * stack memory & heap memory
  * how to store | RAM

# Introduction

* JVM's memory types
  * are
    * stack memory
    * heap memory
  * allows
    * run an application / optimal way
  * uses
    * | 
      * declare new variables and objects,
      * call a new method,

# Stack memory | Java

* uses
  * static memory allocation
  * execute a thread
  * references to objects / referred from the method / is | heap

* way to access
  * follows Last-In-First-Out (LIFO) order

* 's features
  * | call methods -> grows
  * | return methods -> shrinks
  * requirements to exist variables | stack
    * method / created them, is running
  * | method execution,
    * AUTOMATICALLY allocated
    * AUTOMATICALLY deallocated
  * if this memory is full -> Java throws java.lang.StackOverFlowError
  * threadsafe
    * == EACH thread operates | its own stack
  * vs heap memory
    * faster

# Heap Space | Java

* TODO: Heap space is used for the dynamic memory allocation of Java objects and JRE classes at runtime
* New objects are always created in heap space, and the references to these objects are stored in stack memory.

These objects have global access and we can access them from anywhere in the application.

We can break this memory model down into smaller parts, called generations, which are:

    Young Generation – this is where all new objects are allocated and aged. A minor Garbage collection occurs when this fills up.
    Old or Tenured Generation – this is where long surviving objects are stored. When objects are stored in the Young Generation, a threshold for the object’s age is set, and when that threshold is reached, the object is moved to the old generation.
    Permanent Generation – this consists of JVM metadata for the runtime classes and application methods.

These different portions are also discussed in the article Difference Between JVM, JRE, and JDK.

We can always manipulate the size of heap memory as per our requirement. For more information, visit this linked Baeldung article.
3.1. Key Features of Java Heap Memory

Some other features of heap space include:

    It’s accessed via complex memory management techniques that include the Young Generation, Old or Tenured Generation, and Permanent Generation.
    If heap space is full, Java throws java.lang.OutOfMemoryError.
    Access to this memory is comparatively slower than stack memory
    This memory, in contrast to stack, isn’t automatically deallocated. It needs Garbage Collector to free up unused objects so as to keep the efficiency of the memory usage.
    Unlike stack, a heap isn’t threadsafe and needs to be guarded by properly synchronizing the code.

# Example

Based on what we’ve learned so far, let’s analyze a simple Java code to assess how memory is managed:





Let’s analyze this step-by-step:

    When we enter the main() method, a space in stack memory is created to store primitives and references of this method.
        Stack memory directly stores the primitive value of the integer id.
        The reference variable person of type Person will also be created in stack memory, initially pointing to null and later updated to point to the actual object in the heap.
    The main method is calling the buildPerson() static method, for which allocation will take place in stack memory on top of the previous one.
    The buildPerson() calls the parameterized constructor Person(int, String) which will allocate further memory on top of the previous stack. This will store:
        The this object reference of the calling object in stack memory
        The primitive value id in the stack memory
        The reference variable of String argument name, which will point to the actual string from string pool in heap memory
    However, heap memory will store all instance variables for the newly created object person of type Person.

Let’s look at this allocation in the diagram below:
java heap stack diagram

# Summary

Before we conclude this article, let’s quickly summarize the differences between the Stack Memory and the Heap Space:
Parameter 	Stack Memory 	Heap Space
Application 	Stack is used in parts, one at a time during execution of a thread 	The entire application uses Heap space during runtime
Size 	Stack has size limits depending upon OS, and is usually smaller than Heap 	There is no size limit on Heap
Storage 	Stores only primitive variables and references to objects that are created in Heap Space 	All the newly created objects are stored here
Order 	It’s accessed using Last-in First-out (LIFO) memory allocation system 	This memory is accessed via complex memory management techniques that include Young Generation, Old or Tenured Generation, and Permanent Generation.
Life 	Stack memory only exists as long as the current method is running 	Heap space exists as long as the application runs
Efficiency 	Much faster to allocate when compared to heap 	Slower to allocate when compared to stack
Allocation/Deallocation 	This Memory is automatically allocated and deallocated when a method is called and returned, respectively 	Heap space is allocated when new objects are created and deallocated by Gargabe Collector when they’re no longer referenced

# Conclusion

Stack and heap are two ways in which Java allocates memory. In this article, we learned how they work, and when to use them for developing better Java programs.

To learn more about Memory Management in Java, have a look at this article here. We also touched on the JVM Garbage Collector, which is discussed briefly over in this article.
