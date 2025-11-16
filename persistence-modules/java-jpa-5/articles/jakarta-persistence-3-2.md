https://www.baeldung.com/jakarta-persistence-3-2

* goal
  * Jakarta Persistence 3.2's NEW features (query capabilities, performance, usability, modern database support)

* Jakarta Persistence OR JPA
  * == 💡standard API -- for -- object-relational mapping💡
    * allows
      * managing relational data | Java applications
      * simplifying database interactions 
        * Java objects are mapped -- , via annotations & entity classes, to -- database tables 

# how to set up Jakarta Persistence 3.2?
* | pom.xml
  * add
    ```xml
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.2.0</version>
    </dependency>
    <dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>7.0.0.Beta1</version>
    </dependency>
    ```

# Jakarta Persistence 3.2's NEW features

database connection handling

## Persistence Configuration

* programmatic API /    
  * allows
    * 👀obtaining an instance of the `EntityManagerFactory` interface -- via -- `PersistenceConfiguration` class👀
      * ⚠️!= traditional "persistence.xml" file⚠️
      * == flexibility
      * _Example:_ [JakartaPersistenceApiTest.createEntityManagerFactory()](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

* `@Entity` annotation
  * | class
    * == persistent entity
* `@Id` annotation
  * | field
    * == primary key

## Schema Manager API

* Schema Manager API
  * enables developers to
    * 👀manage schema programmatically👀
      * == | development & production environments, simplify
        * database migrations
        * schema validation 
      * 's ALLOWED methods
        * create()
          * creates the tables / associated -- with -- entities
        * drop()
          * drops tables / associated -- with -- entities
        * validate()
          * validates the schema -- against the -- entity mappings
        * truncate()
          * clears tables' data data -- from -- tables related to entities
  * _Example:_ [`JakartaPersistenceApiTest.setUp() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## `runInTransaction()` & `callInTransaction()`

* allows
  * improving the handling of database transactions -- by -- providing an application-managed EntityManager / active transaction
    * perform operations | transaction scope
    * access the underlying database connection

* _Examples:_
  * [`JakartaPersistenceApiTest.whenRunInTransactionAndCallInTransaction_thenTheEntityManagerWorksWithTransaction() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)
  * [`JakartaPersistenceApiTest.whenUsingEnhancedJpql_thenNewFeaturesWorks() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## TypedQueryReference Interface

* `@NamedQuery(name="referByString")`
  * Problems to refer by string: 👀prone to errors
    * _Example:_ typos | query name👀

* TypedQueryReference interface
  * type 
  * allows
    * 👀solve `@NamedQuery` prone to errors👀
      * by linking named queries -- to the -- static metamodel 

* _Examples:_ [`JakartaPersistenceApiTest.givenNamedQuery_whenQueriedByDepartment_thenReturnCorrectEmployee() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## Type-safety in EntityGraph

* Jakarta Persistence’s entity graphs
  * allow
    * | execute a query,
      * eager loading of properties 
  * type-safe
    * Reason:🧠properties / referenced | graph,
      * are valid
      * exist | compile time🧠

* _Examples:_ [`JakartaPersistenceApiTest.whenFindEmployeeWithEntityGraph_thenReturnEmployeeWithDepartment() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

# Usability Enhancements

## Streamlined JPQL

* == NOT specify an alias
  * Reason:🧠AUTOMATICALLY, by default the associated table🧠
* COMMON uses
  * | Jakarta Data Query Language

* Jakarta Data Query Language
  * == subset of JPQL

* _Examples:_ [`JakartaPersistenceApiTest.whenUsingEnhancedJpql_thenNewFeaturesWorks() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## `cast()` Function

* allows us
  * cast query results

* _Examples:_ [`JakartaPersistenceApiTest.whenCastFullNameToInteger_thenReturnCorrectResult() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## `left()` and `right()` Function

* string manipulation methods
  * allows
    * extracting substrings -- via -- the index value

* _Examples:_ [`JakartaPersistenceApiTest.whenLeftAndRightFullName_thenReturnSubstring() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## `replace()` Function

* allow us to
  * replace part of the String

* _Examples:_ [`JakartaPersistenceApiTest.whenReplaceFullName_thenReturnModifiedName() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

## `id()` Function

* lets us
  * extract the database record's identifier

* _Examples:_ [`JakartaPersistenceApiTest.whenUseIdFunction_thenReturnEmployeeId() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)

##  Improved Sorting -- `lower()` & `upper()` --

* _Examples:_ [`JakartaPersistenceApiTest.whenSortEmployeesByFullName_thenReturnSortedList() `](../src/test/java/com/baeldung/jakarta/JakartaPersistenceApiTest.java)
