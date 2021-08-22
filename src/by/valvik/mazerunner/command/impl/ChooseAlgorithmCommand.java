package by.valvik.mazerunner.command.impl;

import by.valvik.mazerunner.command.Command;
import by.valvik.mazerunner.constant.AlgorithmOption;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.view.Console;
import by.valvik.mazerunner.view.Page;
import by.valvik.mazerunner.view.impl.InfoPage;

import static java.util.Arrays.fill;

public class ChooseAlgorithmCommand implements Command {

    private static final String CHOOSE_ALGORITHMS_TEMPLATE = """
        >Choose one of algorithms:
        [prim] - Prims algorithm %s
        [kruskal] - Kruskals algorithm %s
        >\040""";

    private static final String USED_NOW = "(used now)";

    private static final String EMPTY_ARG = "";

    private static final String ERROR_INCORRECT_ALGORITHM_NAME = "Incorrect algorithm name!";

    private static final String ALGORITHM_SELECTED_TEMPLATED = "The %ss algorithm is selected.";

    @Override
    public Page execute(Holder holder) {

        Console console = Console.getInstance();

        Object[] formattedArgs = getArgs(holder.getAlgorithmOption());

        String commandLine = console.getCommandLine(CHOOSE_ALGORITHMS_TEMPLATE.formatted(formattedArgs));

        try {

            AlgorithmOption algorithmOption = AlgorithmOption.valueOf(commandLine.toUpperCase());

            holder.setAlgorithmOption(algorithmOption);

            String selectedAlgorithm = capitalize(algorithmOption.name());

            holder.setMessage(ALGORITHM_SELECTED_TEMPLATED.formatted(selectedAlgorithm));

        } catch (IllegalArgumentException e) {

            holder.setMessage(ERROR_INCORRECT_ALGORITHM_NAME);

        }

        return InfoPage.getInstance();

    }

    private Object[] getArgs(AlgorithmOption algorithmOption) {

        String[] args = new String[AlgorithmOption.values().length];

        fill(args, EMPTY_ARG);

        args[algorithmOption.ordinal()] = USED_NOW;

        return args;

    }

    private String capitalize(String str) {

        return  str.charAt(0) +  str.substring(1).toLowerCase();

    }

}