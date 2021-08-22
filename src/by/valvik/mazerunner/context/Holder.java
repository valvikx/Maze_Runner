package by.valvik.mazerunner.context;

import by.valvik.mazerunner.constant.AlgorithmOption;
import by.valvik.mazerunner.constant.CommandOption;
import by.valvik.mazerunner.model.Maze;

public class Holder {

    private Maze maze;

    private CommandOption commandOption;

    private String message;

    private AlgorithmOption algorithmOption;

    public Maze getMaze() {

        return maze;

    }

    public void setMaze(Maze maze) {

        this.maze = maze;

    }

    public CommandOption getCommandOption() {

        return commandOption;

    }

    public void setCommandOption(CommandOption commandOption) {

        this.commandOption = commandOption;

    }

    public String getMessage() {

        return message;

    }

    public void setMessage(String message) {

        this.message = message;

    }

    public AlgorithmOption getAlgorithmOption() {

        return algorithmOption;

    }

    public void setAlgorithmOption(AlgorithmOption algorithmOption) {

        this.algorithmOption = algorithmOption;

    }

}
