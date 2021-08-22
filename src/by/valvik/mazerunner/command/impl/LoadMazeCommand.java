package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.command.BaseIOCommand;
import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.dao.MazeDao;
import by.valvik.mazerunner.dao.impl.FileMazeDao;
import by.valvik.mazerunner.exception.MazeException;
import by.valvik.mazerunner.model.Maze;
import by.valvik.mazerunner.view.Console;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static by.valvik.mazerunner.util.Stores.getFileNames;

public class LoadMazeCommand extends BaseIOCommand implements Command {

    private static final String ENTER_FILE_NAME_TEMPLATE = ">Enter the file name from the following list:\n%s";

    private static final String ERROR_INVALID_FILE_NAME = "Invalid file name!";

    private static final String ERROR_SAVED_MAZES_ARE_MISSING = "The saved mazes are missing!";

    private static final String MAZE_LOADED_SUCCESSFULLY = "The maze loaded successfully.";

    private static final String DELIMITER = "\n";

    private static final String POSTFIX = "\n>";

    @Override
    public Page execute(Holder holder) {

        try {

            List<String> fileNames = getFileNames(getPath(), TXT);

            if (fileNames.isEmpty()) {

                holder.setMessage(ERROR_SAVED_MAZES_ARE_MISSING);

                return InfoPage.getInstance();

            }

            Console console = Console.getInstance();

            String commandLine = console.getCommandLine(ENTER_FILE_NAME_TEMPLATE.formatted(join(fileNames)));

            if (fileNames.stream().noneMatch(fn -> Objects.equals(fn, commandLine))) {

                holder.setMessage(ERROR_INVALID_FILE_NAME);

                return InfoPage.getInstance();

            }

            MazeDao mazeDao = new FileMazeDao(getPath(commandLine));

            Maze maze = mazeDao.load();

            holder.setMaze(maze);

            holder.setMessage(MAZE_LOADED_SUCCESSFULLY);

        } catch (IOException e) {

            holder.setMessage(ERROR_SAVED_MAZES_ARE_MISSING);

        } catch (MazeException e) {

            holder.setMessage(e.getMessage());

        }

        return InfoPage.getInstance();

    }

    private String join(List<String> fileNames) {

        return String.join(DELIMITER, fileNames) + POSTFIX;

    }

}
