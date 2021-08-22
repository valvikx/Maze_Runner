package by.valvik.mazerunner.model;

import by.valvik.mazerunner.constant.Status;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static by.valvik.mazerunner.constant.Status.*;
import static java.util.stream.Collectors.joining;

public class Maze {

    private static final String DELIMITER = "";

    private final int rowCount;

    private final int colCount;

    private final Cell[][] matrix;

    private String[][] signArray;

    public Maze(int rowCount, int colCount) {

        this.rowCount = rowCount;

        this.colCount = colCount;

        matrix = new Cell[rowCount][colCount];

        init();

    }

    public Maze(String[][] signArray) {

        this.rowCount = signArray.length;

        this.colCount = signArray[0].length;

        this.signArray = signArray;

        matrix = new Cell[rowCount][colCount];

        init();

    }

    public int getRowCount() {

        return rowCount;

    }

    public int getColCount() {

        return colCount;

    }

    public Cell[][] getMatrix() {

        return matrix;

    }

    public void fromSignArray() {

        for (int rowIdx = 0; rowIdx < rowCount; rowIdx++) {

            for (int colIdx = 0; colIdx < colCount; colIdx++) {

                Status status = forSign(signArray[rowIdx][colIdx]);

                matrix[rowIdx][colIdx].setStatus(status);

                Position position = matrix[rowIdx][colIdx].getPosition();

                if ((Objects.equals(status, PASSAGE) || Objects.equals(status, PATH)) &&
                    (position.rowIdx() == 0 || position.rowIdx() == rowCount - 1 ||
                     position.colIdx() == 0 || position.colIdx() == colCount - 1)) {

                    matrix[rowIdx][colIdx].setEntrance(true);

                }

            }

        }

    }

    public List<String> toLines() {

         return Arrays.stream(matrix)
                      .map(cells -> Arrays.stream(cells)
                                          .map(cell -> cell.getStatus().getSign())
                                          .collect(joining(DELIMITER)))
                      .toList();

    }

    private void init() {

        for (int rowIdx = 0; rowIdx < rowCount; rowIdx++) {

            for (int colIdx = 0; colIdx < colCount; colIdx++) {

                matrix[rowIdx][colIdx] = new Cell(new Position(rowIdx, colIdx));

            }

        }

    }

}
