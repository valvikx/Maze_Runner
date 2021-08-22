package by.valvik.mazerunner.dao.impl;

import by.valvik.mazerunner.constant.Status;
import by.valvik.mazerunner.dao.MazeDao;
import by.valvik.mazerunner.exception.MazeException;
import by.valvik.mazerunner.model.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.*;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.util.stream.Collectors.toSet;

public record FileMazeDao(Path path) implements MazeDao {

    private static final String ERROR_SAVE_MAZE = "Unable to save the maze!";

    private static final String ERROR_LOAD_MAZE = "Cannot load the maze. It has an invalid format!";

    private static final String REGEX = "(?<=\\G.{2})";

    public void save(Maze maze) throws MazeException {

        createFileInStore();

        try {

            write(path, maze.toLines(), UTF_8);

        } catch (IOException e) {

            throw new MazeException(ERROR_SAVE_MAZE);

        }

    }

    public Maze load() throws MazeException {

        try {
            
            String[][] array = Files.lines(path)
                                    .map(l -> Arrays.stream(l.trim().split(REGEX))
                                                    .toArray(String[]::new))
                                    .toArray(String[][]::new);

            if (!isValid(array)) {

                throw new MazeException(ERROR_LOAD_MAZE);

            }

            Maze maze = new Maze(array);

            maze.fromSignArray();

            return maze;

        } catch (IOException e) {

            throw new MazeException(e.getMessage());

        }

    }

    private void createFileInStore() throws MazeException {

        try {

            if (!exists(path, NOFOLLOW_LINKS)) {

                createDirectories(path.getParent());

                createFile(path);

            }

        } catch (IOException e) {

            throw new MazeException(e.getMessage());

        }

    }

    private boolean isValid(String[][] array) {

        Set<String> statusSigns = Arrays.stream(Status.values())
                                        .map(Status::getSign)
                                        .collect(toSet());

        Set<String> fileSigns = Arrays.stream(array)
                                      .flatMap(Arrays::stream)
                                      .collect(toSet());

        return statusSigns.containsAll(fileSigns);

    }

}
