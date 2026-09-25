# String Class cheat sheet

String s = "  Hello Java World  ";

s.length()              // 20
s.isEmpty()             // false
s.isBlank()             // false (Java 11+)
s.contains("Java")      // true
s.startsWith("  H")     // true
s.endsWith("d  ")       // true
s.equals("...")         // compare
s.equalsIgnoreCase("  hello java world  ")  // true

s.trim()                // "Hello Java World"
s.strip()               // "Hello Java World"
s.stripLeading()        // "Hello Java World  "
s.stripTrailing()       // "  Hello Java World"
s.substring(8, 12)      // "Java"  (8 inclusive, 12 exclusive)

s.indexOf("Java")       // 8
s.lastIndexOf("o")      // 17
s.charAt(8)             // 'J'

s.toUpperCase()         // "  HELLO JAVA WORLD  "
s.toLowerCase()         // "  hello java world  "
s.replace("Java", "Cat")     // "  Hello Cat World  "
s.replaceAll("\\s+", "_")    // works with regex
s.split(" ")            // array of words
s.strip().split("\\s+") // ["Hello", "Java", "World"]

String.valueOf(42)      // "42"  (object → String)
String.join(", ", "a", "b", "c")  // "a, b, c"
"Java ".repeat(3)       // "Java Java Java "  (Java 11+)