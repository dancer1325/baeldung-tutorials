https://www.baeldung.com/java-ee-cdi

* goal
  * CDI’s features
  * inject dependencies | client classes

* CDI (Contexts and Dependency Injection)
  * == standard [dependency injection framework](https://en.wikipedia.org/wiki/Dependency_injection)/
    * included | Java EE v6+
  * allows us to
    * manage the lifecycle of stateful components -- via -- domain-specific lifecycle contexts
    * inject components (services) | client objects -- via -- type-safe way

# DYDI (Do-it-Yourself Dependency Injection)

* == implement DI 
  * WITHOUT using any framework
  * 👀business logic is separated -- , via factories OR builders, from -- object creation👀
  * use cases
    * simple
  * limitations
    * | grow (size & complexity) OUR application,
      * -> COMPLEX object graph factories == ❌NOT FULLY-scalable solution❌
  * _Example:_ [here](../src/main/java/com/baeldung/dydependencyinjection)

# CDI's steps

## “beans.xml” | "src/main/resources/META-INF/"

* 👀ALTHOUGH this file does NOT contain any specific DI directives -> required for getting CDI up & running👀

```xml
<beans xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
    http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
</beans>
```

## Service Classes

* _Example:_
  * [imagefileeditors folder](../src/main/java/com/baeldung/dependencyinjection/imagefileeditors)

## Client Class

* `@Inject` 
  * ALLOWED uses |
    * constructor
      * ⭐️avoids defining factory classes⭐️
    * fields (== [field injection](#field-injection)) & setters (== [setter injection](#setter-injection))

* _Example:_
  * [ImageFileProcessor.java](../src/main/java/com/baeldung/dependencyinjection/imageprocessors/ImageFileProcessor.java)
    * vs | DYDI, [ImageProcessorFactory.java](../src/main/java/com/baeldung/dydependencyinjection/ImageProcessorFactory.java) + [ImageFileProcessorDYDI.java](../src/main/java/com/baeldung/dydependencyinjection/ImageFileProcessorDYDI.java)

## Building the ImageFileProcessor Object Graph With Weld
* TODO:
Of course, we need to make sure that CDI will inject the right ImageFileEditor implementation into the ImageFileProcessor class constructor.

To do so, first, we should get an instance of the class.

As we won’t rely on any Java EE application server for using CDI, we’ll do this with Weld, the CDI reference implementation in Java SE:

public static void main(String[] args) {
Weld weld = new Weld();
WeldContainer container = weld.initialize();
ImageFileProcessor imageFileProcessor = container.select(ImageFileProcessor.class).get();

    System.out.println(imageFileProcessor.openFile("file1.png"));
 
    container.shutdown();
}

Here, we’re creating a WeldContainer object, then getting an ImageFileProcessor object, and finally calling its openFile() method.

As expected, if we run the application, CDI will complain loudly by throwing a DeploymentException:

Unsatisfied dependencies for type ImageFileEditor with qualifiers @Default at injection point...

We’re getting this exception because CDI doesn’t know what ImageFileEditor implementation to inject into the ImageFileProcessor constructor.

In CDI’s terminology, this is known as an ambiguous injection exception

## `@Default` and `@Alternative` Annotations

Solving this ambiguity is easy
CDI, by default, annotates all the implementations of an interface with the @Default annotation.

So, we should explicitly tell it which implementation should be injected into the client class:

@Alternative
public class GifFileEditor implements ImageFileEditor { ... }

@Alternative
public class JpgFileEditor implements ImageFileEditor { ... }

public class PngFileEditor implements ImageFileEditor { ... }

In this case, we’ve annotated GifFileEditor and JpgFileEditor with the @Alternative annotation, so CDI now knows that PngFileEditor (annotated by default with the @Default annotation) is the implementation that we want to inject.

If we rerun the application, this time it’ll be executed as expected:

Opening PNG file file1.png

Furthermore, annotating PngFileEditor with the @Default annotation and keeping the other implementations as alternatives will produce the same above result.

This shows, in a nutshell, how we can very easily swap the run-time injection of implementations by simply switching the @Alternative annotations in the service classes.

# Field Injection

CDI supports both field and setter injection out of the box.

Here’s how to perform field injection (the rules for qualifying services with the @Default and @Alternative annotations remain the same):

@Inject
private final ImageFileEditor imageFileEditor;

# Setter Injection

Similarly, here’s how to do setter injection:

@Inject
public void setImageFileEditor(ImageFileEditor imageFileEditor) { ... }

# `@Named` Annotation

So far, we’ve learned how to define injection points in client classes and inject services with the @Inject, @Default , and @Alternative annotations, which cover most of the use cases.

Nevertheless, CDI also allows us to perform service injection with the @Named annotation.

This method provides a more semantic way of injecting services, by binding a meaningful name to an implementation:

@Named("GifFileEditor")
public class GifFileEditor implements ImageFileEditor { ... }

@Named("JpgFileEditor")
public class JpgFileEditor implements ImageFileEditor { ... }

@Named("PngFileEditor")
public class PngFileEditor implements ImageFileEditor { ... }

Now, we should refactor the injection point in the ImageFileProcessor class to match a named implementation:

@Inject
public ImageFileProcessor(@Named("PngFileEditor") ImageFileEditor imageFileEditor) { ... }

It’s also possible to perform field and setter injection with named implementations, which looks very similar to using the @Default and @Alternative annotations:

@Inject
private final @Named("PngFileEditor") ImageFileEditor imageFileEditor;

@Inject
public void setImageFileEditor(@Named("PngFileEditor") ImageFileEditor imageFileEditor) { ... }

# `@Produces` Annotation

Sometimes, a service requires some configuration to be fully-initialized before it gets injected to handle additional dependencies.

CDI provides support for these situations, through the @Produces annotation.

@Produces allows us to implement factory classes, whose responsibility is the creation of fully-initialized services.

To understand how the @Produces annotation works, let’s refactor the ImageFileProcessor class, so it can take an additional TimeLogger service in the constructor.

The service will be used for logging the time at which a certain image file operation is performed:

@Inject
public ImageFileProcessor(ImageFileEditor imageFileEditor, TimeLogger timeLogger) { ... }

public String openFile(String fileName) {
return imageFileEditor.openFile(fileName) + " at: " + timeLogger.getTime();
}

// additional image file methods

In this case, the TimeLogger class takes two additional services, SimpleDateFormat and Calendar:

public class TimeLogger {

    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    
    // constructors
    
    public String getTime() {
        return dateFormat.format(calendar.getTime());
    }
}

How do we tell CDI where to look at for getting a fully-initialized TimeLogger object?

We just create a TimeLogger factory class and annotate its factory method with the @Produces annotation:

public class TimeLoggerFactory {

    @Produces
    public TimeLogger getTimeLogger() {
        return new TimeLogger(new SimpleDateFormat("HH:mm"), Calendar.getInstance());
    }
}

Whenever we get an ImageFileProcessor instance, CDI will scan the TimeLoggerFactory class, then call the getTimeLogger() method (as it’s annotated with the @Produces annotation), and finally inject the Time Logger service.

If we run the refactored sample application with Weld, it’ll output the following:

Opening PNG file file1.png at: 17:46

# Custom Qualifiers

CDI supports the use of custom qualifiers for qualifying dependencies and solving ambiguous injection points.

Custom qualifiers are a very powerful feature. They not only bind a semantic name to a service, but they bind injection metadata too
Metadata such as the RetentionPolicy and the legal annotation targets (ElementType).

Let’s see how to use custom qualifiers in our application:

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface GifFileEditorQualifier {}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface JpgFileEditorQualifier {}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface PngFileEditorQualifier {}

Now, let’s bind the custom qualifiers to the ImageFileEditor implementations:

@GifFileEditorQualifier
public class GifFileEditor implements ImageFileEditor { ... }

@JpgFileEditorQualifier
public class JpgFileEditor implements ImageFileEditor { ... }

@PngFileEditorQualifier
public class PngFileEditor implements ImageFileEditor { ... }

Lastly, let’s refactor the injection point in the ImageFileProcessor class:

@Inject
public ImageFileProcessor(@PngFileEditorQualifier ImageFileEditor imageFileEditor, TimeLogger timeLogger) { ... }

If we run our application once again, it should generate the same output shown above.

Custom qualifiers provide a neat semantic approach for binding names and annotation metadata to implementations.

In addition, custom qualifiers allow us to define more restrictive type-safe injection points 
(outperforming the functionality of the @Default and @Alternative annotations).

If only a subtype is qualified in a type hierarchy, then CDI will only inject the subtype, not the base type.

10. Conclusion

Unquestionably, CDI makes dependency injection a no-brainer, 
the cost of the extra annotations is very little effort for the gain of organized dependency injection.

There are times when DYDI does still have its place over CDI
Like when developing fairly simple applications that only contain simple object graphs.

The code backing this article is available on GitHub
Once you're logged in as a Baeldung Pro Member, start learning and coding on the project.
