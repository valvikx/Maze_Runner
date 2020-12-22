package maze.view;

import maze.domain.MazeMatrix;
import maze.exception.MazeException;

import java.util.Scanner;

import static maze.constant.MazeConstants.*;

public class Console {

    private final Scanner scanner = new Scanner(System.in);

    private static final String ENTER_SIZE_OF_MAZE = "Enter the size of a new maze";

    private static final String DOUBLE_SPACE = "  ";

    private static final String DOUBLE_BLOCK = "\u2588\u2588";

    private static final String DOUBLE_SLASH = "//";

    private static final String MENU = "=== Menu ===";

    private static final String GENERATE_A_NEW_MAZE = "1. Generate a new maze";

    private static final String LOAD_A_MAZE = "2. Load a maze";

    private static final String SAVE_THE_MAZE = "3. Save the maze";

    private static final String DISPLAY_THE_MAZE = "4. Display the maze";

    private static final String FIND_THE_ESCAPE = "5. Find the escape";

    private static final String EXIT = "0. Exit";

    private static final String ERROR_INCORRECT_OPTION = "Incorrect option. Please try again";

    private static final String ERROR_SIZE_OF_MAZE = "Size of maze should be greater than or equal to 5";

    private static final String ERROR_FILE_DOES_NOT_EXIST = "The file %s does not exist";

    private static final String BYE = "Bye!";

    public int enterSizeOfMaze() {

        System.out.println(ENTER_SIZE_OF_MAZE);

        int minSizeOfMaze = 5;

        int sizeOfMaze = -1;

        try {

            sizeOfMaze = Integer.parseInt(scanner.nextLine());

            if (sizeOfMaze < minSizeOfMaze) {

                sizeOfMaze = -1;

                throw new MazeException();

            }

        } catch (NumberFormatException | MazeException e) {

            System.out.println(ERROR_SIZE_OF_MAZE);

            System.out.println();

        }

        return sizeOfMaze;

    }

    public String enterNameOfFile() {

        return scanner.nextLine();

    }

    public void displayMaze(MazeMatrix maze) {

        for (int[] rows : maze.getMaze()) {

            for (int value : rows) {

                switch (value) {

                    case WALL:
                        System.out.print(DOUBLE_BLOCK);
                        break;

                    case PASSAGE:
                        System.out.print(DOUBLE_SPACE);
                        break;

                    case PATH:
                        System.out.print(DOUBLE_SLASH);
                        break;
                }

            }

            System.out.println();

        }

    }

    public int displayMenu(boolean isFullMenu) {

        System.out.println(MENU);

        System.out.println(GENERATE_A_NEW_MAZE);

        System.out.println(LOAD_A_MAZE);

        if (isFullMenu) {

            System.out.println(SAVE_THE_MAZE);

            System.out.println(DISPLAY_THE_MAZE);

            System.out.println(FIND_THE_ESCAPE);

        }

        System.out.println(EXIT);

        int menuItemValue = -1;

        int minItemValueOfStartMenu = 2;

        int minMenuItemValueOfFullMenu = 5;

        try {

            String menuItem = scanner.nextLine();

            menuItemValue = Integer.parseInt(menuItem);

            int minItemValue = isFullMenu ? minMenuItemValueOfFullMenu : minItemValueOfStartMenu;

            if (menuItemValue < 0 || menuItemValue > minItemValue) {

                throw new MazeException();

            }

        } catch (NumberFormatException | MazeException e) {

            System.out.println(ERROR_INCORRECT_OPTION);

            displayEmptyLine();

        }

        return menuItemValue;

    }

    public void displayBye() {

        displayMessage(BYE);

    }

    public void displayFileDoesNotExist (String fileName) {

        System.out.printf(ERROR_FILE_DOES_NOT_EXIST, fileName);

    }

    public void displayEmptyLine() {

        displayMessage("");

    }

    public void displayMessage(String message) {

        System.out.println(message);

    }

}
