package com.javacs.projects.pipeline;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyPipeline<T> implements Pipeline<T> {

    private final List<T> elements;

    public MyPipeline(List<T> elements) {
        this.elements = elements;
    }

    public static <T> MyPipeline<T> of(List<T> elements) {
        return new MyPipeline<>(elements);
    }

    @Override
    public Pipeline<T> filter(Predicate<T> predicate) {
        return null;
    }

    @Override
    public <R> Pipeline<R> map(Function<T, R> function) {
        return null;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }
}
