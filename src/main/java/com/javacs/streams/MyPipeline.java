package com.javacs.streams;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyPipeline<T> implements Pipeline<T>{
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
