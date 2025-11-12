package com.baeldung.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ReflectionUnitTest {

    @Test
    public void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
        // EVEN typing as Object -> get the fields
        final Object person = new Person();
        final Field[] fields = person.getClass().getDeclaredFields();

        final List<String> actualFieldNames = getFieldNames(fields);

        assertTrue(Arrays.asList("name", "age").containsAll(actualFieldNames));
    }

    @Test
    public void givenObject_whenGetsClassName_thenCorrect() {
        final Object goat = new Goat("goat");
        final Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.baeldung.reflection.Goat", clazz.getName());
        assertEquals("com.baeldung.reflection.Goat", clazz.getCanonicalName());
    }

    // goal
    //      FROM FULLY qualified class name -> create an instance
    @Test
    public void givenClassName_whenCreatesObject_thenCorrect() throws ClassNotFoundException {
        // class' instance
        final Class<?> clazz = Class.forName("com.baeldung.reflection.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.baeldung.reflection.Goat", clazz.getName());
        assertEquals("com.baeldung.reflection.Goat", clazz.getCanonicalName());
    }

    @Test
    public void givenNOTFULLClassName_whenCreatesObject_thenThrowError() throws ClassNotFoundException {
        assertThrows(ClassNotFoundException.class,
                () -> Class.forName("Goat")
        );
    }

    // goal
    //     check EACH class' modifier
    @Test
    public void givenClass_whenRecognisesModifiers_thenCorrect() throws ClassNotFoundException {
        final Class<?> goatClass = Class.forName("com.baeldung.reflection.Goat");
        final Class<?> animalClass = Class.forName("com.baeldung.reflection.Animal");
        final int goatMods = goatClass.getModifiers();
        final int animalMods = animalClass.getModifiers();

        assertTrue(Modifier.isPublic(goatMods));
        assertTrue(Modifier.isAbstract(animalMods));
        assertTrue(Modifier.isPublic(animalMods));
    }

    //  goal
    //      get package information
    @Test
    public void givenClass_whenGetsPackageInfo_thenCorrect() {
        final Goat goat = new Goat("goat");
        final Class<?> goatClass = goat.getClass();
        final Package pkg = goatClass.getPackage();

        assertEquals("com.baeldung.reflection", pkg.getName());
    }

    // goal
    //      get super class
    @Test
    public void givenClass_whenGetsSuperClass_thenCorrect() {
        final Goat goat = new Goat("goat");
        final String str = "any string";

        final Class<?> goatClass = goat.getClass();
        final Class<?> goatSuperClass = goatClass.getSuperclass();

        assertEquals("Animal", goatSuperClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName());

    }

    // goal
    //      get DIRECT (!=INDIRECTLY) implemented interfaces
    @Test
    public void givenClass_whenGetsImplementedInterfaces_thenCorrect() throws ClassNotFoundException {
        final Class<?> goatClass = Class.forName("com.baeldung.reflection.Goat");
        final Class<?> animalClass = Class.forName("com.baeldung.reflection.Animal");
        final Class<?>[] goatInterfaces = goatClass.getInterfaces();
        final Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }

    // goal
    //      get constructorS
    @Test
    public void givenClass_whenGetsConstructor_thenCorrect() throws ClassNotFoundException {
        final Class<?> goatClass = Class.forName("com.baeldung.reflection.Goat");
        final Constructor<?>[] constructors = goatClass.getConstructors();

        assertEquals(1, constructors.length);
        assertEquals("com.baeldung.reflection.Goat", constructors[0].getName());
    }

    // goal
    //      get fieldS
    @Test
    public void givenClass_whenGetsFields_thenCorrect() throws ClassNotFoundException {
        final Class<?> animalClass = Class.forName("com.baeldung.reflection.Animal");
        final Field[] fields = animalClass.getDeclaredFields();

        final List<String> actualFields = getFieldNames(fields);

        assertEquals(2, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }

    // goal
    //      get methodS
    @Test
    public void givenClass_whenGetsMethods_thenCorrect() throws ClassNotFoundException {
        final Class<?> animalClass = Class.forName("com.baeldung.reflection.Animal");
        final Method[] methods = animalClass.getDeclaredMethods();
        final List<String> actualMethods = getMethodNames(methods);

        assertEquals(3, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("getName", "setName", "getSound")));
    }

    @Test
    public void givenClass_whenGetsAllConstructors_thenCorrect() throws ClassNotFoundException {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Constructor<?>[] constructors = birdClass.getConstructors();

        assertEquals(3, constructors.length);
    }

    // goal
    //      get specific constructor
    @Test
    public void givenClass_whenGetsEachConstructorByParamTypes_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");

        // .getConstructor(constructor'sParameterTypeInDeclaredOrder)
        birdClass.getConstructor();
        birdClass.getConstructor(String.class);
        birdClass.getConstructor(String.class, boolean.class);
    }

    // goal
    //      instantiate objects | runtime
    @Test
    public void givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");

        final Constructor<?> cons1 = birdClass.getConstructor();
        final Constructor<?> cons2 = birdClass.getConstructor(String.class);
        final Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

        // cast
        final Bird bird1 = (Bird) cons1.newInstance();
        final Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
        final Bird bird3 = (Bird) cons3.newInstance("dove", true);

        assertEquals("bird", bird1.getName());
        assertEquals("Weaver bird", bird2.getName());
        assertEquals("dove", bird3.getName());
        assertFalse(bird1.walks());
        assertTrue(bird3.walks());
    }

    // goal
    //      retrieve ONLY class & ALL superclass' ACCESSIBLE public
    @Test
    public void givenClass_whenGetsPublicFields_thenCorrect() throws ClassNotFoundException {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Field[] fields = birdClass.getFields();
        assertEquals(1, fields.length);     // ONLY class & ALL superclass' ACCESSIBLE public
        assertEquals("CATEGORY", fields[0].getName());

    }

    // goal
    //      retrieve ONLY class & ALL superclass' ACCESSIBLE public's specific
    @Test
    public void givenClass_whenGetsPublicFieldByName_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Field field = birdClass.getField("CATEGORY");
        assertEquals("CATEGORY", field.getName());

    }

    // goal
    //      retrieve class & ALL superclass' specific field
    @Test
    public void givenClass_whenGetsDeclaredFields_thenCorrect() throws ClassNotFoundException {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Field[] fields = birdClass.getDeclaredFields();
        assertEquals(1, fields.length);
        assertEquals("walks", fields[0].getName());
    }

    // goal
    //      retrieve class & ALL superclass' specific fieldName
    @Test
    public void givenClass_whenGetsFieldsByName_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Field field = birdClass.getDeclaredField("walks");
        assertEquals("walks", field.getName());

    }

    // goal
    //      get field's type
    @Test
    public void givenClassField_whenGetsType_thenCorrect() throws Exception {
        final Field field = Class.forName("com.baeldung.reflection.Bird").getDeclaredField("walks");
        final Class<?> fieldClass = field.getType();
        assertEquals("boolean", fieldClass.getSimpleName());
    }

    // goal
    //      set field's value
    @Test
    public void givenClassField_whenGetsValue_thenCorrect() throws Exception {
        final Bird bird = new Bird();
        final Field field = bird.getClass().getDeclaredField("walks");
        field.setAccessible(true);
        final boolean walks = field.getBoolean(bird);
        assertTrue(walks);
    }

    // goal
    //      set field's value
    @Test
    public void givenClassField_whenSetsAndGetsValue_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Bird bird = (Bird) birdClass.getConstructor().newInstance();
        final Field field = birdClass.getDeclaredField("walks");
        field.setAccessible(true);  // MANDATORY PREVIOUSLY to modify the value

        assertFalse(field.getBoolean(bird));
        assertFalse(bird.walks());

        field.set(bird, true);

        assertTrue(field.getBoolean(bird));
        assertTrue(bird.walks());
    }

    // goal
    //      if the field is `public static` -> `.get(null)`
    @Test
    public void givenClassField_whenGetsAndSetsWithNull_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Field field = birdClass.getField("CATEGORY");
        field.setAccessible(true);

        assertEquals("domestic", field.get(null));
    }

    // goal
    //      class & ALL superclass' ACCESSIBLE public `java.lang.reflect.Method[]`
    @Test
    public void givenClass_whenGetsAllPublicMethods_thenCorrect() throws ClassNotFoundException {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Method[] methods = birdClass.getMethods();
        final List<String> methodNames = getMethodNames(methods);

        assertTrue(methodNames.containsAll(Arrays.asList("equals", "notifyAll", "hashCode", "walks", "eats", "toString")));

    }

    // goal
    //      class ONLY public `java.lang.reflect.Method[]`
    @Test
    public void givenClass_whenGetsOnlyDeclaredMethods_thenCorrect() throws ClassNotFoundException {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final List<String> actualMethodNames = getMethodNames(birdClass.getDeclaredMethods());

        final List<String> expectedMethodNames = Arrays.asList("setWalks", "walks", "getSound", "eats");

        assertEquals(expectedMethodNames.size(), actualMethodNames.size());
        assertTrue(expectedMethodNames.containsAll(actualMethodNames));
        assertTrue(actualMethodNames.containsAll(expectedMethodNames));

    }

    // goal
    //      class ONLY public `java.lang.reflect.Method`
    @Test
    public void givenMethodName_whenGetsMethod_thenCorrect() throws Exception {
        final Bird bird = new Bird();
        final Method walksMethod = bird.getClass().getDeclaredMethod("walks");
        final Method setWalksMethod = bird.getClass().getDeclaredMethod("setWalks", boolean.class);

        assertTrue(walksMethod.canAccess(bird));
        assertTrue(setWalksMethod.canAccess(bird));
    }

    // goal
    //      invoke a method
    @Test
    public void givenMethod_whenInvokes_thenCorrect() throws Exception {
        final Class<?> birdClass = Class.forName("com.baeldung.reflection.Bird");
        final Bird bird = (Bird) birdClass.getConstructor().newInstance();
        final Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        final Method walksMethod = birdClass.getDeclaredMethod("walks");
        final boolean walks = (boolean) walksMethod.invoke(bird);

        assertFalse(walks);
        assertFalse(bird.walks());

        setWalksMethod.invoke(bird, true);
        final boolean walks2 = (boolean) walksMethod.invoke(bird);

        assertTrue(walks2);
        assertTrue(bird.walks());

    }

    private static List<String> getFieldNames(Field[] fields) {
        final List<String> fieldNames = new ArrayList<>();
        for (final Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;

    }

    private static List<String> getMethodNames(Method[] methods) {
        final List<String> methodNames = new ArrayList<>();
        for (final Method method : methods) {
            methodNames.add(method.getName());
        }
        return methodNames;
    }

}
