package com.baeldung.features.records;

// sealed interface     --> NOT require default | switch expression
public sealed interface ILocation permits Location {
    default String getName() {
        return switch (this) {
            case Location(var name, var ignored) -> name;
        };
    }
}