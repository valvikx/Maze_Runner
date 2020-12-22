package maze.io;

import maze.domain.MazeMatrix;
import maze.exception.MazeException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class MazeIO {

    private static final String ERROR_SAVE_MAZE = "Unable to save the maze";

    private static final String ERROR_LOAD_MAZE = "Cannot load the maze. It has an invalid format";

    private final String fileName;

    public MazeIO(String fileName) {

        this.fileName = fileName;

    }

    public void saveToFile(MazeMatrix maze) throws MazeException {

        File file = new File(fileName);

        try (PrintWriter printWriter = new PrintWriter(file)) {

            for (int[] row : maze.getMaze()) {

                for (int value : row) {

                    printWriter.printf("%d ", value);

                }

                printWriter.println();

            }

        } catch (IOException e) {

            throw new MazeException(ERROR_SAVE_MAZE);

        }

    }

    public int[][] loadFromFile(Path path) throws MazeException {

        try {

            return Files
                    .lines(path)
                    .map(l -> Arrays
                            .stream(l.trim().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray())
                    .toArray(int[][]::new);

        } catch (IOException | NumberFormatException e) {

            throw new MazeException(ERROR_LOAD_MAZE);

        }

    }

}
