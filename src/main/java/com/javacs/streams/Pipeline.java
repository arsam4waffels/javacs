package com.javacs.streams;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <b>Defines the contract for a pipeline.</b>
 *
 * <p>Any class that implements this interface must provide
 * implementations for the required operations.</p>
 *
 * @param <T> the type of elements processed by this pipeline
 */
public interface Pipeline<T> {

    /**
     * <b>Filter</b>
     * <p>Filters elements based on the given predicate.</p>
     *
     * <p>The predicate evaluates each element and returns either
     * {@code true} or {@code false}.</p>
     *
     * <p>Returns {@code Pipeline<T>} so that operations can be chained.</p>
     *
     * @param predicate the condition used to filter elements
     * @return the current pipeline
     */
    Pipeline<T> filter(Predicate<T> predicate);

    /**
     * <b>Map</b>
     * <p>It takes something of type {@code <T>} and
     * returns something of type {@code <R>}.</p>
     * @param function
     * @param <R>
     */
    <R> Pipeline<R> map(Function<T, R> function);

    /**
     * <b>toList (same as stream's toList())</b>
     * <p>It produces a {@code List} containing the elements of the pipeline.</p>
     * @return List<T>
     */
    List<T> toList();
}
