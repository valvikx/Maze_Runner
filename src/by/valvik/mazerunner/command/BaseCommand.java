package by.valvik.mazerunner.command;

import by.valvik.mazerunner.context.Holder;

import static java.util.Objects.nonNull;

public abstract class BaseCommand {

    public static final String ERROR_MAZE_NOT_GENERATE = "The maze has not been generated yet!";

    public boolean hasMaze(Holder holder) {

        return nonNull(holder.getMaze());

    }

}
