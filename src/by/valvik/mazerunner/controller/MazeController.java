package by.valvik.mazerunner.controller;

import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.constant.CommandOption;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.factory.CommandFactory;
import by.valvik.mazerunner.factory.Factory;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.MenuPage;

import java.util.Objects;

import static by.valvik.mazerunner.constant.AlgorithmOption.PRIM;
import static by.valvik.mazerunner.constant.CommandOption.EXIT;
import static java.util.Objects.nonNull;

public class MazeController {

    public void run() {

        Holder holder = new Holder();

        CommandFactory commandFactory = new CommandFactory();

        Factory<CommandOption, Command> factory = commandFactory.getFactory();

        holder.setAlgorithmOption(PRIM);

        CommandOption commandOption;

        do {

            MenuPage.getInstance().display(holder);

            commandOption = holder.getCommandOption();

            if (nonNull(commandOption)) {

                Command command = factory.get(commandOption);

                Page commandPage = command.execute(holder);

                commandPage.display(holder);

            }

        } while (!Objects.equals(EXIT, commandOption));

    }

}
