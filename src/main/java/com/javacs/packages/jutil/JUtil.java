package com.javacs.packages.jutil;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class JUtil {

    public void turnInputIntoInt(String input) {
        try {
            int numbers = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
class MyArrays {
    public <T> String arrayToString(T @NotNull [] array) {
        return Arrays.toString(array);
    }

    private <T extends Comparable<? super T>> T[] arraySorter(T @NotNull [] arrayList) {
        Arrays.sort(arrayList);
        return arrayList;
    } // array sorter factory
    public <T extends Comparable<? super T>> T[] sortArray(T @NotNull[] arrayList) {
        return arraySorter(arrayList);
    } // convert input into clean int[]

    public <T extends Comparable<? super T>> void sortAndPrintArray(T @NotNull [] arrayList) {
        System.out.println(Arrays.toString(sortArray(arrayList)));
    } // name itself is pretty self-explanatory

    public <T extends Comparable<? super T>> int findIndex(T @NotNull [] arrayList,
                                                           T toFind) {
        validate(toFind);
        return Arrays.binarySearch(
                sortArray(arrayList), toFind
        );
    } // return the index of value

    public <T> void fillUp(T @NotNull [] arrayList,
                           T with) {
        validate(with);
        Arrays.fill(arrayList, with);
    } // creampie's array with you favorite value

    @SafeVarargs private static <T> void validate(T... items) {
        for (T item : items)
            Objects.requireNonNull(
                    item,
                    "Item [" + item + "] is null");
    }

    public <T> T[] copyRange(T @NotNull [] arrayList,
                                            int from,
                                            int to) {
        validate(from, to);
        return Arrays.copyOfRange(arrayList, from, to);
    } // copy a specific range of arrays

    public <T> T[] fullCopy(T @NotNull [] arrayList) {
        return copyRange(arrayList, 0, arrayList.length);
    } // copies whole array; used copyRange() for cleaner code

    public <T> boolean areEquals(T @NotNull [] list1,
                                                T @NotNull [] list2) {
        return Arrays.equals(list1, list2);
    }
}