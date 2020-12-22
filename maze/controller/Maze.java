package maze.controller;

import maze.domain.Edge;
import maze.domain.MazeMatrix;
import maze.exception.MazeException;
import maze.io.MazeIO;
import maze.model.KruskalAlg;
import maze.model.MazeGenerator;
import maze.model.MazeSolver;
import maze.model.MinSpanningTree;
import maze.view.Console;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Maze {

    private final Console console = new Console();

    private MazeMatrix maze;

    public void start() {

        boolean isFullMenu = false;

        boolean isExit = false;

        while (!isExit) {

            int menuItemValue = console.displayMenu(isFullMenu);

            switch (menuItemValue) {

                case 0:

                    isExit = true;

                    console.displayBye();

                    break;

                case 1:

                    generateNewMaze();

                    isFullMenu = true;

                    break;

                case 2:

                    if (!isFullMenu) {

                        isFullMenu = loadMaze();

                    } else {

                        loadMaze();

                    }

                    break;

                case 3:

                    saveMaze();

                    isFullMenu = true;

                    break;

                case 4:

                    displayMaze();

                    isFullMenu = true;

                    break;

                case 5:

                    findPath();

                    isFullMenu = true;

                    break;


            }

        }

    }

    private void generateNewMaze() {

        int sizeOfMaze = console.enterSizeOfMaze();

        maze = new MazeMatrix(sizeOfMaze, sizeOfMaze);

        if (sizeOfMaze != -1) {

            //MinSpanningTree minSpanningTree = new PrimaAlg(maze);

            MinSpanningTree minSpanningTree = new KruskalAlg(maze);

            List<Edge> mst = minSpanningTree.generate();

            MazeGenerator mazeGenerator = new MazeGenerator(maze);

            mazeGenerator.generate(mst);

            displayMaze();

        }

    }

    private void saveMaze() {

        String fileName = console.enterNameOfFile();

        MazeIO mazeIO = new MazeIO(fileName);

        try {

            mazeIO.saveToFile(maze);

        } catch (MazeException e) {

            console.displayMessage(e.getMessage());

        }

        console.displayEmptyLine();

    }

    private boolean loadMaze() {

        String fileName = console.enterNameOfFile();

        Path path = Paths.get(fileName);

        if (Files.exists(path)) {

            MazeIO mazeIO = new MazeIO(fileName);

            try {

                if (maze == null) {

                    maze = new MazeMatrix(mazeIO.loadFromFile(path));

                } else {

                    maze.setMaze(mazeIO.loadFromFile(path));

                }

                console.displayEmptyLine();

                return true;


            } catch (MazeException e) {

                console.displayMessage(e.getMessage());

            }

        } else {

            console.displayFileDoesNotExist(fileName);

        }

        console.displayEmptyLine();

        return false;

    }

    private void displayMaze() {

        console.displayMaze(maze);

        console.displayEmptyLine();

    }

    private void findPath() {

        MazeSolver mazeSolver = new MazeSolver(maze);

        mazeSolver.findPath();

        displayMaze();

    }

}
