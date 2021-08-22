package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.command.BaseIOCommand;
import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.dao.MazeDao;
import by.valvik.mazerunner.dao.impl.FileMazeDao;
import by.valvik.mazerunner.exception.MazeException;
import by.valvik.mazerunner.view.Console;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;

public class SaveMazeCommand extends BaseIOCommand implements Command {

    private static final String ENTER_FILE_NAME = ">Enter the file name: ";

    private static final String MAZE_SAVED_SUCCESSFULLY = "The maze saved successfully.";

    @Override
    public Page execute(Holder holder) {

        Console console = Console.getInstance();

        String fileName = console.getCommandLine(ENTER_FILE_NAME);

        MazeDao mazeDao = new FileMazeDao(getPath(fileName));

        try {

            mazeDao.save(holder.getMaze());

            holder.setMessage(MAZE_SAVED_SUCCESSFULLY);

        } catch (MazeException e) {

            holder.setMessage(e.getMessage());

        }

        return InfoPage.getInstance();

    }

}
