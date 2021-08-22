package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.command.BaseCommand;
import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.exception.MazeException;
import by.valvik.mazerunner.maze.MazeSolver;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;
import by.valvik.mazerunner.view.impl.MazePage;

public class SolveMazeCommand extends BaseCommand implements Command {

    @Override
    public Page execute(Holder holder) {

        if (hasMaze(holder)) {

            MazeSolver mazeSolver = new MazeSolver(holder.getMaze());

            try {

                mazeSolver.findEscape();

                return MazePage.getInstance();

            } catch (MazeException e) {

                holder.setMessage(e.getMessage());

            }

            return InfoPage.getInstance();

        }

        holder.setMessage(ERROR_MAZE_NOT_GENERATE);

        return InfoPage.getInstance();

    }

}
