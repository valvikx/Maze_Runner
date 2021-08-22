package by.valvik.mazerunner.view.impl;

import by.valvik.mazerunner.constant.CommandOption;
import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.view.Console;
import by.valvik.mazerunner.view.Page;

import java.util.Optional;

import static by.valvik.mazerunner.constant.CommandOption.forCommandOption;
import static by.valvik.mazerunner.util.Parsers.toInt;
import static java.util.Objects.isNull;

public class MenuPage implements Page {

    private static final Page INSTANCE = new MenuPage();

    private static final int MAX_OPTION_START_MENU = 2;

    private MenuPage() {

    }

    private static final String START_MENU = """
        ****************************** MAZE RUNNER ****************************
        ================================= Menu ================================
        0. Exit
        1. Generate a new maze
        2. Load a maze
        """;

    private static final String ADVANCED_MENU = """
        3. Save the maze
        4. Display the maze
        5. Find the escape
        6. Choose algorithm      
        """;

    private static final String ENTER_COMMAND = ">Enter the command option: ";

    private static final String ERROR_INCORRECT_OPTION = "Incorrect option. Please try again.";

    @Override
    public void display(Holder holder) {

        System.out.print(isNull(holder.getMaze()) ? START_MENU : START_MENU + ADVANCED_MENU);

        Console console = Console.getInstance();

        String commandLine = console.getCommandLine(ENTER_COMMAND);

        try {

            int option = toInt(commandLine);

            if (isNull(holder.getMaze()) && option > MAX_OPTION_START_MENU) {

                System.out.println(ERROR_INCORRECT_OPTION);

                return;

            }

            Optional<CommandOption> commandOption = forCommandOption(option);

            holder.setCommandOption(commandOption.orElseGet(() -> {

                System.out.println(ERROR_INCORRECT_OPTION);

                return null;

            }));

        } catch (NumberFormatException e) {

            System.out.println(ERROR_INCORRECT_OPTION);

            holder.setCommandOption(null);

        }

    }

    public static Page getInstance() {

        return INSTANCE;

    }

}
