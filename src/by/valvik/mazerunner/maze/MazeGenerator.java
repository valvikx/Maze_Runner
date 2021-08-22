package by.valvik.mazerunner.maze;

import by.valvik.mazerunner.model.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static by.valvik.mazerunner.constant.Status.PASSAGE;
import static by.valvik.mazerunner.util.Randoms.getRandomElement;

public class MazeGenerator {

    private final Cell[][] matrix;

    private final int rowCount;

    private final int colCount;

    public MazeGenerator(Maze maze) {

        rowCount = maze.getRowCount();

        colCount = maze.getColCount();

        matrix = maze.getMatrix();

    }

    public void newMaze(List<Edge> minimumSpanningTree) {

        minimumSpanningTree.forEach(e -> {

            Node firstNode = e.getFirstNode();

            Node secondNode = e.getSecondNode();

            connectPassages(getPassagePosition(firstNode), getPassagePosition(secondNode));

        });

        correctDoubleWalls();

        setEntrances();

    }

    private Position getPassagePosition(Node node) {

        int rowIdx = convertToMatrixIdx(node.getPosition().rowIdx());

        int colIdx = convertToMatrixIdx(node.getPosition().colIdx());

        matrix[rowIdx][colIdx].setStatus(PASSAGE);

        return new Position(rowIdx, colIdx);

    }

    private void connectPassages(Position firstPassage, Position secondPassage) {

        if (firstPassage.rowIdx() == secondPassage.rowIdx()) {

            matrix[firstPassage.rowIdx()][firstPassage.colIdx() + 1].setStatus(PASSAGE);

        } else if (firstPassage.colIdx() == secondPassage.colIdx()) {

            matrix[firstPassage.rowIdx() + 1][firstPassage.colIdx()].setStatus(PASSAGE);

        }

    }

    private void correctDoubleWalls() {

        if (colCount % 2 == 0) {

            correctVerticalDoubleWall();

        }

        if (rowCount % 2 == 0) {

            correctHorizontalDoubleWall();

        }

    }

    private void correctVerticalDoubleWall() {

        int colIdx = colCount - 2;

        IntStream.range(1, rowCount - 1)
                 .filter(rowIdx -> Objects.equals(matrix[rowIdx][colIdx - 1].getStatus(), PASSAGE) &&
                                   Objects.equals(matrix[rowIdx][colIdx - 2].getStatus(), PASSAGE))
                 .forEach(rowIdx -> matrix[rowIdx][colIdx].setStatus(PASSAGE));

    }

    private void correctHorizontalDoubleWall() {

        int rowIdx = rowCount - 2;

        IntStream.range(1, colCount - 1)
                 .filter(colIdx -> Objects.equals(matrix[rowIdx - 1][colIdx].getStatus(), PASSAGE) &&
                                   Objects.equals(matrix[rowIdx - 2][colIdx].getStatus(), PASSAGE))
                 .forEach(colIdx -> matrix[rowIdx][colIdx].setStatus(PASSAGE));

    }

    private void setEntrances() {

        Cell[] possibleEntrances;

        if (rowCount < colCount) {

            possibleEntrances = getPossibleEntrancesInVerticalWall(0, 1);

            setEntrance(possibleEntrances);

            possibleEntrances = getPossibleEntrancesInVerticalWall(colCount - 1, -1);

        } else {

            possibleEntrances = getPossibleEntrancesInHorizontalWall(0, 1);

            setEntrance(possibleEntrances);

            possibleEntrances = getPossibleEntrancesInHorizontalWall(rowCount - 1, -1);

        }

        setEntrance(possibleEntrances);

    }

    private Cell[] getPossibleEntrancesInVerticalWall(int colIdx, int offset) {

        return IntStream.range(1, rowCount - 1)
                        .filter(rowIdx -> !Objects.equals(matrix[rowIdx][colIdx],
                                                          matrix[rowIdx][colIdx + offset]))
                        .mapToObj(rowIdx -> matrix[rowIdx][colIdx])
                        .toArray(Cell[]::new);

    }

    private Cell[] getPossibleEntrancesInHorizontalWall(int rowIdx, int offset) {

        return IntStream.range(1, colCount - 1)
                        .filter(colIdx -> !Objects.equals(matrix[rowIdx][colIdx],
                                                          matrix[rowIdx + offset][colIdx]))
                        .mapToObj(colIdx -> matrix[rowIdx][colIdx])
                        .toArray(Cell[]::new);

    }

    private int convertToMatrixIdx(int minimumSpanningTreeIdx) {

        return 2 * minimumSpanningTreeIdx + 1;

    }

    private void setEntrance(Cell[] possibleEntrances) {

        Cell entrance = getRandomElement(possibleEntrances);

        entrance.setStatus(PASSAGE);

        entrance.setEntrance(true);

    }

}
