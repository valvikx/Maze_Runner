package by.valvik.mazerunner.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Stores {

    private static final String REGEX = "\\.";

    private Stores() {

    }

    public static List<String> getFileNames(Path path, String fileExt) throws IOException {

        return Files.list(path)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(fileExt))
                    .map(fn -> fn.split(REGEX)[0])
                    .toList();

    }
}
