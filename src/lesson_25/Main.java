package lesson_25;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("c:\\windows");
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(path)) {
            for (Path p : paths)
                System.out.println(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printNames(String greeting, String... names) {
        for (String name : names) {
            System.out.println(name);
        }
    }

    private static void nioFileWriting() {
        Path path = Path.of("test.txt");
        try {
            Files.writeString(path, "Hello world...", StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void nioFileReading() {
        Path path = Path.of("cars.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
