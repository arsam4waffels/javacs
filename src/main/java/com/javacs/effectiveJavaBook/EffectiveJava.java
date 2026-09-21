package com.javacs.effectiveJavaBook;

public class EffectiveJava {

    // Flyweight Pattern
    public static Boolean valueOf(boolean booleans) {
        return booleans ? Boolean.TRUE : Boolean.FALSE;
    }

    static class CoffeeOrder {

        private final String type;
        private final int size;
        private final boolean hot;

        private CoffeeOrder(String type,
                           int size,
                           boolean hot) {
            this.type = type;
            this.size = size;
            this.hot = hot;
        }

        public static CoffeeOrder espresso() {
            return new CoffeeOrder("espresso", 30, true);
        }

        public static CoffeeOrder latte(int size) {
            return new CoffeeOrder("latte", size, true);
        }

        public static CoffeeOrder iceLatte(int size) {
            return new CoffeeOrder("latte", size, false);
        }

        private static final CoffeeOrder DEFAULT_ESPRESSO
                = new CoffeeOrder("espresso", 30, true);
        public static CoffeeOrder defaultEspresso() {
            return DEFAULT_ESPRESSO;
        }

        @Override public String toString() {
            return (hot ? "Hot" : "Iced")
                    + " " + type
                    + "(" + size + ")";
        }
    }
}
