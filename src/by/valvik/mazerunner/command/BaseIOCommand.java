package by.valvik.mazerunner.command;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseIOCommand {

    public static final String DIR_STORE = "store";

    public static final String TXT = ".txt";

    private static final String FILE_TEMPLATE = "%s" + TXT;

    public Path getPath(String fileName) {

        return Paths.get(DIR_STORE, FILE_TEMPLATE.formatted(fileName));

    }

    public Path getPath() {

        return Paths.get(DIR_STORE);

    }

}
