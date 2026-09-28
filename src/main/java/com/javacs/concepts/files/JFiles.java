package com.javacs.concepts.files;

import com.javacs.annotions.review;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

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

        /**
         * <p>real engagement with filesystem</p>
         * @return realPath
         * @throws IOException
         */
        @review public Path pathToReal() throws IOException {
            return path.toRealPath();
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

        /**
         * <p>Concat a serial of paths onto one.</p>
         * <p><b>Note:</b> concatenation paths using String is wrong.
         * it's much better and safer to user {@code p.resolve()} for it.</p>
         * @param paths
         * @return new set of path
         */
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

    static class LearnFiles {

        public boolean filesExist(Path path) {
            return Files.exists(path); // == !Files.notExists(path)
        } // checks whether the file exists at the specified address

        /*
         * TOCTOU (not to be misunderstood with Hawk Tuah)
         *      — Time Of Check To Time Of Use
         *
         * The file might be deleted by another process.
         */

        public void filesReadSmallFiles(Path path) throws IOException {
            /*
             * It's better to explicitly specify the charset.
             * encoding is part of files contract.
             */
            String content = Files.readString(
                    path,
                    StandardCharsets.UTF_8
            );

            System.out.printf(content);
        }

        public void filesWriteString(Path path, String content) throws IOException {
            /*
             * [ALERT] -> Risk of data loss
             *
             * This method deletes all existing content and data from the file (if any)
             * and overwrites it with the new content and data.
             */
            Files.writeString(
                    path,
                    content,
                    StandardCharsets.UTF_8
            );
        }

        public void filesWriteStringAppend(Path path,
                                           String content) throws IOException {

            /*
             * Here, new information is added to the existing file without deleting the old data.
             * WRITE    -> replace
             * APPEND   -> add to end
             */
            Files.writeString(
                    path,
                    content,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            /*
             * @review
             * CREATE               -> create new file, if it doesn't exist
             * CREATE_NEW           -> create new file under any circumstanced
             *                      -> throw [FileAlreadyExistsException] if exist
             * TRUNCATE_EXISTING    -> clear files information, if it exists
             * APPEND               ->
             * WRITE                ->
             * READ                 ->
             * DELETE_ON_CLOSE      ->
             * SYNC                 ->
             * DSYNC                ->
             * SPARSE               ->
             */
        }

        public void filesReadLines(Path path) throws IOException {
            List<String> lines = Files.readAllLines(
                    path,
                    StandardCharsets.UTF_8
            );
            for (String line : lines)
                System.out.printf(line);
        }

        public void filesReadLinesLambda(Path path) throws IOException {
            List<String> lines = Files.readAllLines(
                    path,
                    StandardCharsets.UTF_8
            );
            lines.forEach(System.out::println);
        }

        /*
         * [ALERT] -> Loading the entire file into memory completely and simultaneously
         *
         * `readAllLines` It reads the entire file and loads it all into memory,
         *  which is risky and represents poor design for large, heavy files.
         *
         * For large files, it is better to use `Files.lines()`, as it loads
         * data into memory incrementally using a stream.
         */

        public void filesReadLinesMassive(Path path) throws IOException {
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(System.out::println);
            }
        }

        /*
         * Reading byte files (small files)
         *      [1] small image
         *      [2] small encrypted file
         *      [3] small binary file
         *      [4] hash input
         *
         * for larger files -> readAllBytes()
         */

        public void filesReadBytes(Path path) throws IOException {
            byte[] data = Files.readAllBytes(path);
        }

        public void filesWriteBytes(Path path, byte[] data) throws IOException {
            // no encoding needed because we are working with binary
            Files.write(
                    path,
                    data
            );
        }

        public void filesStreamInput(Path path) throws IOException {
            try (InputStream inputStream = Files.newInputStream(path)) {

                byte[] buffer = new byte[8192];
                int byteRead;

                while ((byteRead = inputStream.read(buffer)) != -1) {
                    // TODO
                }
            }
        }

        /*
         * Atomic-ish / transactional file update pattern
         *
         *     config.tmp
         *     ↓
         *     write completely
         *     ↓
         *     move
         *     ↓
         *     config.properties
        */

        public void filesCopy(Path source, Path target) throws IOException {
            Files.copy(
                    source,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        public void filesMove(Path source, Path target) throws IOException {
            Files.move(
                    source,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        public void filesDirectory(Path path, int choice) throws IOException {
            if (choice == 0) Files.createDirectory(path);
            else Files.createDirectories(path);
        }

        public void filesDelete(Path path, boolean notSureExist) throws IOException {
            if (notSureExist) Files.deleteIfExists(path);
            else Files.delete(path);
        }
    }
}
