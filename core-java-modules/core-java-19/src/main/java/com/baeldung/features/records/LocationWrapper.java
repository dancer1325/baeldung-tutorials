package com.baeldung.features.records;

// generic records
public record LocationWrapper<T>(T t, String description) { }
