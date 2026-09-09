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
     * <p>evaluates an input and returns a boolean result.</p>
     *
     * <p>{@code Pipeline<T>} is also returned, ensuring that the
     * filtered value is passed back into the pipeline and
     * allowing methods to be chained together.</p>
     * @param predicate
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
