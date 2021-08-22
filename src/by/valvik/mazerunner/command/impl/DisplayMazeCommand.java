package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.command.BaseCommand;
import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;
import by.valvik.mazerunner.view.impl.MazePage;

public class DisplayMazeCommand extends BaseCommand implements Command {

    @Override
    public Page execute(Holder holder) {

        if (hasMaze(holder)) {

             return MazePage.getInstance();

        }

        holder.setMessage(ERROR_MAZE_NOT_GENERATE);

        return InfoPage.getInstance();

    }

}
