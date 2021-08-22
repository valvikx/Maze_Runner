package by.valvik.mazerunner.factory;

import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.command.impl.*;
import by.valvik.mazerunner.constant.CommandOption;

import static by.valvik.mazerunner.constant.CommandOption.*;

public class CommandFactory extends AbstractFactory<CommandOption, Command> {

    @Override
    public Factory<CommandOption, Command> getFactory() {

        Factory<CommandOption, Command> factory = Factory.of();

        factory.add(EXIT, new ExitCommand());

        factory.add(NEW, new NewMazeCommand());

        factory.add(LOAD, new LoadMazeCommand());

        factory.add(SAVE, new SaveMazeCommand());

        factory.add(DISPLAY, new DisplayMazeCommand());

        factory.add(SOLVE, new SolveMazeCommand());

        factory.add(CHOOSE, new ChooseAlgorithmCommand());

        return factory;

    }

}
