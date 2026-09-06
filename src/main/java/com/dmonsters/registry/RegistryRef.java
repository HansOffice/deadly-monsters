package com.dmonsters.registry;

public record RegistryRef<T>(T value) {
    public T get() {
        return this.value;
    }
}
