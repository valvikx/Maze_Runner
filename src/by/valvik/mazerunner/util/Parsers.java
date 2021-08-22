package by.valvik.mazerunner.util;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public final class Parsers {

    private static final String REGEX = "\\s+";

    private Parsers() {

    }

    public static int[] toIntArray(String line) {

        String[] tokens = line.split(REGEX);

        int[] values = Arrays.stream(tokens)
                             .mapToInt(Integer::parseInt)
                             .toArray();

        return values.length == 1 ? new int[] {values[0], values[0]} : values;

    }

    public static int toInt(String line) {

       return parseInt(line);

    }

}
