* goal
  * Java source-level annotation processing
* Java source-level annotation processing
  * := technique / 
    * allows
      * 👁️generating ADDITIONAL source files | compilation👁️
        * ADDITIONAL == NOT modify EXISTING ones
          * Lombok modify existing ones, BUT annotation processing -- is used as -- bootstrapping mechanism
        * 👁️source files can be 👁️
          * ".java"
            * NOT only these -- next possibilities --
          * description
          * metadata
          * documentation
          * resources
          * ...
  * uses
    * own Java libraries
      * generate metaclasses | QueryDSL & JPA
      * augment classes | Lombok
  * Java v5+
* Annotation Processing API
  * happens -- via -- multiple rounds
  * steps / round
    * compiler
      * searches for the annotations | source files
      * chooses the annotations processors / BEST suited
    * if annotation processor 
      * generates additional source files -> another round again / recently generated files -- as -- inputs
      * does NOT generate files -> process ends
  * "javax.annotation.processing"  
    * `Processor.java`
      * main interface / you need to implement
      * `AbstractProcessor.java`
        * partial implementation
* check [annotation-processing](../annotation-processing/README.md)
* TODO: From https://www.baeldung.com/java-annotation-processing-builder#1-creating-an-abstractprocessor-subclass