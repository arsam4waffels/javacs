package com.javacs.concepts.files;

import com.javacs.annotions.review;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JFiles {
    /**
     * <h5>Path</h5>
     * <p><b>A {@code Path} is just an address on the file system.</b> It doesn't touch any files
     * or read anything; it simply indicates where that path is located.</p>
     * <p>The path represents <b>location</b>, not <b>content</b>.</p>
     *
     * <p>{@code Path}  -> Where is it</p>
     * <p>{@code Files} -> What to be done with it</p>
     */
    static class LearnPath {
        Path path_1 = Path.of("notes.txt");
        Path path_2 = Path.of("documents/java/notes.txt");

        // it is recommended to initialize path with separator
        Path path_3 = Path.of(
                "documents",
                "java",
                "notes.txt"
        ); // same as path_2

        // path.get() is old, but still valid
        Path path_4 = Paths.get("notes.txt");

        // absolute path
        Path path_abs_os = Path.of("/home/arsam/documents/notes.txt");  // Linux/Mac
        Path path_abs_win = Path.of("C:\\Users\\Arsam\\notes.txt");     // Windows
        // Always use `Path.of()` — it’s more readable and modern

        Path path = Path.of("projects/java/file-manager/src/Main.java");

        public Path pathFileName() {
            return path.getFileName();
        } // Main.java

        public Path pathParent() {
            return path.getParent();
        } // projects/java/file-manager/src

        @review public Path pathRoot() {
            return path.getRoot();
        } // ?

        public Path pathNameIndex(int index) {
            return path.getName(index);
        } // example : [0] -> projects || [4] -> Main.java

        public Path pathSubPath(int start, int end) {
            if (start < end) {
                if (start < 0 || start >= path.getNameCount())
                    throw new ArrayIndexOutOfBoundsException();
                if (end >= path.getNameCount())
                    throw new ArrayIndexOutOfBoundsException();

                return path.subpath(start, end);
            }
            else throw new IllegalArgumentException(
                    "Start index cannot be higher form end index."
            );
        }

        public Path pathToAbsolute() {
            if (!path.isAbsolute())
                return path.toAbsolutePath();
            return path;
        } // convert to abs path, if not already

        public Path pathResolve(Path newPath) {
            return path.resolve(newPath);
        } // concat to paths
        public Path pathResolveNormal(Path newPath) {
            return path.resolve(newPath).normalize();
        } // concat to paths and normalize it
        public Path pathResolver(Path @NotNull ... paths) {
            if (paths.length == 0)
                throw new IllegalArgumentException(
                        "At least one path is required"
                );

            Path path = paths[0];
            for (Path value : paths) {
                path = path.resolve(value);
            }
            return path
                    .normalize()
                    .toAbsolutePath();
        }

        Path from     = Path.of("projects/java");
        Path to       = Path.of("projects/kotlin/src/Main.kt");
        Path relative = from.relativize(to).normalize();

        public boolean isJava() {
            String fileName = path.getFileName().toString();
            return fileName.endsWith(".java");
        }
    }
}
