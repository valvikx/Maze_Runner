package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;

public class ExitCommand implements Command {

    private static final String BYE = "Bye!";

    @Override
    public Page execute(Holder holder) {

        holder.setMessage(BYE);

        return InfoPage.getInstance();

    }

}
