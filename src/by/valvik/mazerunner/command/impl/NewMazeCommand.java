package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.algorithm.MSTalg;
import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.constant.AlgorithmOption;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.factory.AlgorithmFactory;
import by.valvik.mazerunner.factory.Factory;
import by.valvik.mazerunner.maze.MazeGenerator;
import by.valvik.mazerunner.model.Edge;
import by.valvik.mazerunner.model.Maze;
import by.valvik.mazerunner.view.Console;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;

import java.util.Arrays;
import java.util.List;

import static by.valvik.mazerunner.util.Parsers.toIntArray;

public class NewMazeCommand implements Command {

    private static final String ENTER_SIZE_OF_MAZE = """
        >Enter the [size] or [height width] of a new maze
        >(any maze size should be greater than or equal to 5):\040""";

    private static final int MIN_SIZE = 5;

    private static final int MIN_SUM_SIZES = 10;

    private static final String ERROR_SIZE_OF_MAZE = "Incorrect maze size(s)!";

    private static final String MAZE_SUCCESSFULLY_GENERATED = "The maze generated successfully.";

    private final Factory<AlgorithmOption, MSTalg> factory;

    public NewMazeCommand() {

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();

        this.factory = algorithmFactory.getFactory();

    }

    @Override
    public Page execute(Holder holder) {

        Console console = Console.getInstance();

        try {

            int[] sizes = toIntArray(console.getCommandLine(ENTER_SIZE_OF_MAZE));

            if (!isValid(sizes)) {

                holder.setMessage(ERROR_SIZE_OF_MAZE);

                return InfoPage.getInstance();

            }

            AlgorithmOption algorithmOption = holder.getAlgorithmOption();

            MSTalg mstAlg = factory.get(algorithmOption);

            mstAlg.init(sizes[0], sizes[1]);

            List<Edge> minimumSpanningTree = mstAlg.generate();

            Maze maze = new Maze(sizes[0], sizes[1]);

            MazeGenerator mazeGenerator = new MazeGenerator(maze);

            mazeGenerator.newMaze(minimumSpanningTree);

            holder.setMaze(maze);

            holder.setMessage(MAZE_SUCCESSFULLY_GENERATED);

        } catch (NumberFormatException e) {

            holder.setMessage(ERROR_SIZE_OF_MAZE);

        }

        return InfoPage.getInstance();

    }

    private boolean isValid(int[] sizes) {

        return Arrays.stream(sizes).allMatch(s -> s >= MIN_SIZE) &&
               Arrays.stream(sizes).sum() >= MIN_SUM_SIZES;

    }

}
