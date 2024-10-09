package com.baeldung.annotation.processor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)     // ONLY | method level
@Retention(RetentionPolicy.SOURCE)      // == ONLY valid | source processing, NOT | runtime
public @interface BuilderProperty {
}
