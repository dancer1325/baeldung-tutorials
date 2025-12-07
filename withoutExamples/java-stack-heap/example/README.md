# Stack memory | Java
## uses
* | [StaticMemoryAllocationExample.java](src/StaticMemoryAllocationExample.java)
  * set a breakpoint
  * run -- as -- debug
### static memory allocation
* check variables
### execute a thread
* check stack trace
### references to objects / referred from the method / is | heap
* | [StaticMemoryAllocationExample.java](src/StaticMemoryAllocationExample.java)
  * set a breakpoint
  * run -- as -- debug
  * TODO:
## way to access: follows Last-In-First-Out (LIFO) order
* `javac LIFOStackExample.java` && `java LIFOStackExample`
* check the logs
## 's features
### | call methods -> grows
* | [src](src)
  * `javac StackGrowthExample.java && java StackGrowthExample`
  * check logs
### | return methods -> shrinks
* | [src](src)
  * `javac StackGrowthExample.java && java StackGrowthExample`
  * check logs
### requirements to exist variables | stack: method / created them, is running
* `javac VariableLifetimeExample.java && java VariableLifetimeExample`
* check ALSO the commentaries | [VariableLifetimeExample.java](src/VariableLifetimeExample.java)
### | method execution,
#### AUTOMATICALLY allocated & deallocated
* [AutomaticAllocationExample.java](src/AutomaticAllocationExample.java)
  * run as debug
### if this memory is full -> Java throws java.lang.StackOverFlowError
* `javac StackOverflowExample.java` && `java StackOverflowExample`
### threadsafe: == EACH thread operates | its own stack
* `javac ThreadStackExample.java` && `java ThreadStackExample`
### vs heap memory, faster
* TODO:

# TODO: 