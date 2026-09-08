package com.javacs.streams;

import java.util.List;

public class JStream {

    /**
     * <h5>Class structure</h5>
     * <p>I defined these as static. All the fields depend on the
     * class design system itself.</p>
     * <p> {@code integerList} An ordered list of consecutive integers </p>
     * <p> {@code duplicateIntegerList} A list of consecutive repeating integers </p>
     * <p> {@code stringNameList} A list of strings. It contains a few names. </p>
     * <p> {@code people} An array that stores objects of the {@link Person} record class. </p>
     */
    private final static List<Integer> integerList;
    private final static List<Integer> duplicateIntegerList;
    private final static List<String> stringNameList;
    private final static List<Person> people;

    static {
        integerList = List.of(1,2,3,4,5,6,7,8,9);
        duplicateIntegerList = List.of(1,1,2,3,3,4,5,6,7,7,7,8,9,9);
        stringNameList = List.of("Arsam", "Oreo", "Ali", "Eli");
        people = List.of(
                new Person("Arsam", 21),
                new Person("Farzam", 21),
                new Person("John", 18)
        );
    }

    public List<Integer> evenList() {
        return integerList.stream()
                .filter(item -> item % 2 == 0)
                .toList();
    }
    public long evenListSize() {
        return integerList.stream()
                .filter(item -> item % 2 == 0)
                .count();
    }

    /**
     * <b>Display a range of numbers</b>
     * <p>The stream filters the target sequence again: first, it removes all
     * numbers greater than the {@code start} input, and then it filters out all
     * numbers smaller than {@code end}.</p>
     * <p>It does not have a return value; at the end, it prints the items
     * of the filtered list one by one.</p>
     * <p>This function lacks robustness. I probably ought to write some safety
     * checks for it so it doesn't crash, but I won't—partly because I don't feel
     * like it, and partly because this is all just for practice.</p>
     * @param start
     * @param end
     */
    public void printSelectedRange(int start, int end) {
        integerList.stream()
                .filter(item -> item >= start)
                .filter(item -> item <= end)
                .forEach(System.out::println);
    }

    /**
     * <b>How many items in array are bigger than
     * the specific value</b>
     * @param value int
     */
    public long higherCount(int value) {
        try {
            return duplicateIntegerList.stream()
                    .filter(item -> item > value)
                    .count();
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>Stream Match</b>
     * <p>This class checks whether an item matching the requested
     * input exists in the target array.</p>
     *
     * <p> {@code matchAny} At least one item must meet the specified condition. </p>
     * <p> {@code matchAll} All items must meet the specified condition. </p>
     * <p> {@code matchNone} No item meets the specified condition. </p>
     */
    static class MatchStream {
        /**
         * <b>At least one true condition</b>
         * <p>It filters in two stages: first, it checks the length of
         * each word, and then it determines whether at least one item
         * in the list begins with the specified letter.</p>
         * @param length
         * @param value
         * @return boolean
         */
        boolean matchAny(int length, String value) {
            return stringNameList.stream()
                    .filter(item -> item.length() > length)
                    .anyMatch(item -> item.startsWith(value));
        }

        /**
         * <b>All conditions are correct.</b>
         * <p>It filters in two stages: first, it checks the length of
         * each word, and then it verifies whether all items in the list
         * begin with the specified letter.</p>
         * @param length
         * @param value
         * @return boolean
         */
        boolean matchAll(int length, String value) {
            return stringNameList.stream()
                    .filter(item -> item.length() > length)
                    .allMatch(item -> item.startsWith(value));
        }

        /**
         * <b>No item meets the condition.</b>
         * <p>It checks whether none of the items in the list satisfy
         * the specified condition. If no item meets the condition,
         * the function returns true.</p>
         * @param length
         * @param value
         * @return boolean
         */
        boolean matchNone(int length, String value) {
            return stringNameList.stream()
                    .filter(item -> item.length() > length)
                    .noneMatch(item -> item.startsWith(value));
        }

        public List<String> stringLisToUpper() {
            return stringNameList.stream()
                    .map(String::toUpperCase)
                    .toList();
        }

        static class StreamConvert {
            public List<Integer> convertStringListToInteger() {
                return stringNameList.stream()
                        .map(String::length)
                        .toList();
            }
            public List<Boolean> convertStringListToBoolean() {
                return stringNameList.stream()
                        .map(item -> item.startsWith("a"))
                        .toList();
            }
        }
    }
    void printNamesUppercase() {
        stringNameList.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    record Person (String name, int age) {
        public static List<Person> validAge(List<Person> people) {
            return people.stream()
                    .filter(item -> item.age >= 18)
                    .toList();
        }
        public static List<String> names(List<Person> people) {
            return people.stream()
                    .map(Person::name)
                    .toList();
        }
    }
    /**
     * <b>Represents a simplified user data transfer object.</b>
     *
     * @param username the name of the user
     * @param isAdult  whether the user is 18 or older
     */
    record UserDTO(String username, boolean isAdult) {
        /**
         * Converts the people list into a list of {@link UserDTO} objects.
         *
         * @return a list of {@link UserDTO}
         */
        public static List<UserDTO> convert() {
            return people.stream()
                    .map(person -> new UserDTO(
                            person.name,
                            person.age >= 18
                    ))
                    .toList();
        }
    }
}
