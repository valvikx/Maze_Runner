package by.valvik.mazerunner.command;

import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.view.Page;

public interface Command {

    Page execute(Holder holder);

}
