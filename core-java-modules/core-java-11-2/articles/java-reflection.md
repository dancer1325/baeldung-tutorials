https://www.baeldung.com/java-reflection

# Overview

* Java reflection
  * allows us to
    * inspect &/OR modify runtime 
      * [classes' attributes](#java-classes--interfaces)
      * [interfaces' attributes](#java-classes--interfaces)
      * fields' attributes
      * methods's attributes
    * instantiate NEW objects
    * invoke methods
  * uses
    * | compile time, 
      * ❌we do NOT know their names❌ 
  * `someInstance.getClass()`
    * 's return
      * runtime instance == runtime object's class representation
  * use cases
    * | database tables,
      * programmatically retrieve 
        * object name
        * field names

# Project Setup

* JDK
  * == java.lang.reflect package

    ```java
    import java.lang.reflect.*;
    ```

# _Example:_

* [ReflectionUnitTest](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)

# About
## Java Classes & interfaces

* goal
  * object’s
    * class name,
    * modifiers,
    * fields,
    * methods, 
    * implemented interfaces,
    * etc.

* `SomeObjectOrClassInstance.getClass()`
  * `.getSimpleName()`
  * `.getName()`
    * 's return
      * FULLY qualified class name
    * _Example:_
      * [ReflectionUnitTest.givenObject_whenGetsClassName_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getCanonicalName()`
    * FULLY qualified class name
    * _Example:_
      * [ReflectionUnitTest.givenObject_whenGetsClassName_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getPackage()`
    * 's return
      * some object instance's package information
    * _Example:_
      * [ReflectionUnitTest.givenClass_whenGetsPackageInfo_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getSuperclass()`
    * 's return
      * some object instance's superclass
    * _Example:_
      * [ReflectionUnitTest.givenClass_whenGetsSuperClass_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getInterfaces()`
    * 's return
      * some object instance's DIRECT (!= INDIRECTLY) implemented interfaces
    * _Example:_ 
      * [ReflectionUnitTest.givenClass_whenGetsImplementedInterfaces_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getConstructors()`
    * 's return
      * `java.lang.reflect.Constructor<?>[]`
        * == class' constructorS
    * _Example:_
      * [ReflectionUnitTest.givenClass_whenGetsConstructor_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getConstructor(constructor'sParameterTypeInDeclaredOrder)`
    * 's return
      * `java.lang.reflect.Constructor<?>`
        * == class' constructor
    * _Example:_
      * [ReflectionUnitTest.givenClass_whenGetsConstructor_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getDeclaredFields()` & 
    * 's return
      * ANY (👀EVEN private👀) `java.lang.reflect.Field[]` 
        * == class' fieldS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsDeclaredFields_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getDeclaredField(fieldName)`
    * 's return
      * ANY (👀EVEN private👀) `java.lang.reflect.Field` `fieldName`
        * == class' fieldS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsFieldsByName_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getFields()` &
    * 's return
      * ALL class & ALL superclass' ACCESSIBLE public`java.lang.reflect.Field[]`
        * == class' fieldS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsFields_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getField(fieldName)` &
    * 's return
      * class & ALL superclass' ACCESSIBLE public`java.lang.reflect.Field`'s `fieldName`
        * == class' fieldS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsFields_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getDeclaredMethods()`
    * 's return
      * class ONLY public `java.lang.reflect.Method[]`
        * == class' methodS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsMethods_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getDeclaredMethod("fieldName")`
    * 's return
      * class ONLY public `java.lang.reflect.Method`
        * == class' methodS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsMethods_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.getMethods()`
    * 's return
      * class & ALL superclass' ACCESSIBLE public `java.lang.reflect.Method[]`
        * == class' methodS
    * Example:_
      * [ReflectionUnitTest.givenClass_whenGetsMethods_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)

* `Class.forName("FULLYQualifiedClassName")`
  * 's return
    * fully qualified class' instance 
  * _Example:_
    * [ReflectionUnitTest.givenClassName_whenCreatesObject_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)

* `SomeObjectInstance.getModifiers()`
  * 's return
    * modifiers
      * == JVM constants -- for --
        * public
        * private
        * protect
      * / | `int` representation
  * can be checked -- via -- java.lang.reflect.Modifier's static methods
  * _Example:_
    * [ReflectionUnitTest.givenClass_whenRecognisesModifiers_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)

## Constructors

* `java.lang.reflect.Constructor` class
  * allows, | runtime
    * inspecting constructors
    * create class objects
      * -- via -- `.newInstance(constructor'sParameterTypeInDeclaredOrder)`;

* _Example:_
  * [ReflectionUnitTest.givenClass_whenGetsEachConstructorByParamTypes_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * [ReflectionUnitTest.givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)

## Fields

* `java.lang.reflect.Field`
  * `.getType()`
    * 's return
      * type's class
    * _Example:_ [ReflectionUnitTest.givenClassField_whenGetsType_thenCorrect()](../src/test/java/com/baeldung/reflection/ReflectionUnitTest.java)
  * `.set(instanceInWhichSet, value)`
    * modify the field's value
      * requirements
        * ⚠️PREVIOUSLY `.setAccessible(true)`⚠️
  * .get()
    * if the field is `public static` -> `.get(null)`

## Methods

* `java.lang.reflect.Method`
  * `.invoke(instanceObject, method'sArguments)`
