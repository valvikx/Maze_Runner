package by.valvik.mazerunner.maze;


import by.valvik.mazerunner.exception.MazeException;
import by.valvik.mazerunner.model.Cell;
import by.valvik.mazerunner.model.Maze;
import by.valvik.mazerunner.model.Position;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

import static by.valvik.mazerunner.constant.Status.*;
import static java.util.stream.Collectors.toCollection;


public class MazeSolver {

    private static final String ERROR_IMPOSSIBLE_TO_FIND_ESCAPE = "Impossible to find the escape of the maze!";

    private final Cell[][] matrix;

    private final int rowCount;

    private final int colCount;

    private Position secondEntrance;

    public MazeSolver(Maze maze) {

        rowCount = maze.getRowCount();

        colCount = maze.getColCount();

        matrix = maze.getMatrix();

    }

    public void findEscape() throws MazeException {

        Deque<Cell> entrances = Arrays.stream(matrix)
                                      .flatMap(Arrays::stream)
                                      .filter(Cell::isEntrance)
                                      .collect(toCollection(ArrayDeque::new));

        Position firstEntrance = entrances.pollFirst().getPosition();

        secondEntrance = entrances.pollLast().getPosition();

        if (!hasNextMove(firstEntrance.rowIdx(), firstEntrance.colIdx())) {

            throw new MazeException(ERROR_IMPOSSIBLE_TO_FIND_ESCAPE);

        }

    }

    private boolean hasNextMove(int rowIdx, int colIdx) {

        if (rowIdx == secondEntrance.rowIdx() && colIdx == secondEntrance.colIdx()) {

            matrix[rowIdx][colIdx].setStatus(PATH);

            return true;

        }

        if (Objects.equals(matrix[rowIdx][colIdx].getStatus(), WALL)) {

            return false;

        }

        if (matrix[rowIdx][colIdx].isVisited()) {

            matrix[rowIdx][colIdx].setStatus(PASSAGE);

            return false;

        }

        matrix[rowIdx][colIdx].setVisited(true);

        if (rowIdx != 0 && hasNextMove(rowIdx - 1, colIdx)) {

            matrix[rowIdx][colIdx].setStatus(PATH);

            return true;

        }

        if (rowIdx != rowCount - 1 && hasNextMove(rowIdx + 1, colIdx)) {

            matrix[rowIdx][colIdx].setStatus(PATH);

            return true;

        }

        if (colIdx != 0 && hasNextMove(rowIdx, colIdx - 1)) {

            matrix[rowIdx][colIdx].setStatus(PATH);

            return true;

        }

        if (colIdx != colCount - 1 && hasNextMove(rowIdx, colIdx + 1)) {

            matrix[rowIdx][colIdx].setStatus(PATH);

            return true;

        }

        return false;

    }

}