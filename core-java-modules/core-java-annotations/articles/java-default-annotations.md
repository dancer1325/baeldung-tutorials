https://www.baeldung.com/java-default-annotations

* goal
  * annotations
    * provided | JDK
    * == Java language's core feature

* Annotation
  * == 💡Java types / preceded by an “@” symbol💡
    * allows
      * 👀assigning EXTRA metadata | source code👀 /
        * inform the compiler -- about -- warnings & errors
        * | compilation time,
          * manipulate source code 
        * | runtime,
          * modify or examine behavior 
  * EXIST | Java 1.5
  * use cases
    * Spring
    * Hibernate
  * uses |
    * method,
    * interface,
    * class,
    * field

# Java Built-in Annotations

## / inform compilation

* allows
  * 👀generate OR suppress compiler warnings & errors👀
* are
    ```
    @Override
    @SuppressWarnings
    @Deprecated
    @SafeVarargs
    @FunctionalInterface
    @Native
    ```
  * `@SafeVarargs`
    * skip warnings -- related to use -- varargs
* recommendations
  * use them 

### `@FunctionalInterface`

* | Java 8
* allows
  * 💡| compile time, checking💡
    * == ERROR ONLY | interface definition
    * ⚠️OTHERWISE, error | ANY lambda usage⚠️
* uses 
  * 👀| interface / used -- as -- functional interface + lambda expressions👀
    * == 1! Abstract Method interfaces

* _Example:_
  * [FunctionalInterfaceUnitTest](../src/test/java/com/baeldung/annotations/FunctionalInterfaceUnitTest.java)

### `@Native`

* | Java 8,
* allows
  * indicating that, field == constant /
    * may be referenced -- from the -- native code (== JNI)
* uses
  * ONLY | fields
* use cases
  * hint for the tools / generate AUXILIARY header files

* _Example:_
  * [NativeExampleTest](../src/test/java/com/baeldung/annotations/NativeExampleTest.java)

# Meta-annotations
* == annotations / can be applied | OTHER annotations
* are
  ```
  @Target
  @Retention
  @Inherited
  @Documented
  @Repeatable
  ```
* uses
  * 👀OTHER annotation configuration👀

## `@Target`

* allows
  * specifying annotations' scope
* annotations' scope
  * methods,
  * constructor
  * field declarations

* _Examples:_
  * `SafeVarargs` definition

  ```
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
  public @interface SafeVarargs {
  }
  ```

## `@Retention`

* TODO:
Some annotations are meant to be used as hints for the compiler, while others are used at runtime.

We use the @Retention annotation to say where in our program’s lifecycle our annotation applies.

To do this, we need to configure @Retention with one of three retention policies:

    RetentionPolicy.SOURCE – visible by neither the compiler nor the runtime
    RetentionPolicy.CLASS – visible by the compiler
    RetentionPolicy.RUNTIME – visible by the compiler and the runtime

If no @Retention annotation is present on the annotation declaration, then the retention policy defaults to RetentionPolicy.CLASS.

If we have an annotation that should be accessible at runtime:

@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface RetentionAnnotation {
}

Then, if we add some annotations to a class:

@RetentionAnnotation
@Generated("Available only on source code")
public class AnnotatedClass {
}

Now we can reflect on AnnotatedClass to see how many annotations are retained:

@Test
public void whenAnnotationRetentionPolicyRuntime_shouldAccess() {
AnnotatedClass anAnnotatedClass = new AnnotatedClass();
Annotation[] annotations = anAnnotatedClass.getClass().getAnnotations();
assertThat(annotations.length, is(1));
}

The value is 1 because @RetentionAnnotation has a retention policy of RUNTIME, while @Generated doesn’t.
## `@Inherited`

In some situations, we may need a subclass to have the annotations bound to a parent class.

We can use the @Inherited annotation to make our annotation propagate from an annotated class to its subclasses.

If we apply @Inherited to our custom annotation and then apply it to BaseClass:

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {
}

@InheritedAnnotation
public class BaseClass {
}

public class DerivedClass extends BaseClass {
}

Then, after extending the BaseClass, we should see that DerivedClass appears to have the same annotation at runtime:

@Test
public void whenAnnotationInherited_thenShouldExist() {
DerivedClass derivedClass = new DerivedClass();
InheritedAnnotation annotation = derivedClass.getClass()
.getAnnotation(InheritedAnnotation.class);

    assertThat(annotation, instanceOf(InheritedAnnotation.class));
}

Without the @Inherited annotation, the above test would fail.

## `@Documented`

By default, Java doesn’t document the usage of annotations in Javadocs.

But we can use the @Documented annotation to change Java’s default behavior.

If we create a custom annotation that uses @Documented:

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCell {
int value();
}

And, apply it to the appropriate Java element:

public class Employee {
@ExcelCell(0)
public String name;
}

Then, the Employee Javadoc will reveal the annotation usage:
Field Detail

## `@Repeatable`

Sometimes it can be useful to specify the same annotation more than once on a given Java element.

Before Java 7, we had to group annotations together into a single container annotation:

@Schedules({
@Schedule(time = "15:05"),
@Schedule(time = "23:00")
})
void scheduledAlarm() {
}

However, Java 7 brought a cleaner approach
* With the @Repeatable annotation, we can make an annotation repeatable:

@Repeatable(Schedules.class)
public @interface Schedule {
String time() default "09:00";
}

To use @Repeatable, we need to have a container annotation, too
* In this case, we’ll reuse @Schedules:

public @interface Schedules {
Schedule[] value();
}

Of course, this looks a lot like what we had before Java 7
* But, the value now is that the wrapper @Schedules isn’t specified anymore when we need to repeat @Schedule:

@Schedule
@Schedule(time = "15:05")
@Schedule(time = "23:00")
void scheduledAlarm() {
}

Because Java requires the wrapper annotation, it was easy for us to migrate from pre-Java 7 annotation lists to repeatable annotations.
