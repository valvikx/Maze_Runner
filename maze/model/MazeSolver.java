package maze.model;

import maze.constant.MazeConstants;
import maze.domain.MazeMatrix;

import java.util.ArrayDeque;
import java.util.Deque;

import static maze.constant.MazeConstants.*;

public class MazeSolver {

    private final int[][] maze;

    private final int mazeRows;

    private final int mazeCols;

    private final Cell[][] mazeSolver;

    private int idxColFirstEntrance;

    private int idxRowFirstEntrance;

    private int idxColSecondEntrance;

    private int idxRowSecondEntrance;

    public MazeSolver(MazeMatrix mazeMatrix) {

        this.mazeRows = mazeMatrix.getMazeRows();

        this.mazeCols = mazeMatrix.getMazeCols();

        this.maze = mazeMatrix.getMaze();

        this.mazeSolver = new Cell[mazeMatrix.getMazeRows()][mazeMatrix.getMazeCols()];

        initMazeSolver();

    }

    public void findPath() {

        Deque<Cell> path = new ArrayDeque<>();

        hasNextMove(idxRowFirstEntrance, idxColFirstEntrance, path);

        path.forEach(c -> maze[c.getIdxRow()][c.getIdxCol()] = PATH);

    }

    private boolean hasNextMove(int idxRow, int idxCol, Deque<Cell> path) {

        if (idxRow == idxRowSecondEntrance && idxCol == idxColSecondEntrance) {

            path.offerLast(mazeSolver[idxRow][idxCol]);

            return true;

        }

        if (mazeSolver[idxRow][idxCol].status == WALL) {

            return false;

        }

        if (mazeSolver[idxRow][idxCol].isVisited) {

            path.pollLast();

            return false;

        }

        mazeSolver[idxRow][idxCol].setVisited(true);

        if (idxRow != 0 && hasNextMove(idxRow - 1, idxCol, path)) {

            path.offerLast(mazeSolver[idxRow][idxCol]);

            return true;

        }

        if (idxRow != mazeRows - 1 && hasNextMove(idxRow + 1, idxCol, path)) {

            path.offerLast(mazeSolver[idxRow][idxCol]);

            return true;

        }

        if (idxCol != 0 && hasNextMove(idxRow, idxCol - 1, path)) {

            path.offerLast(mazeSolver[idxRow][idxCol]);

            return true;

        }

        if (idxCol != mazeCols - 1 && hasNextMove(idxRow, idxCol + 1, path)) {

            path.offerLast(mazeSolver[idxRow][idxCol]);

            return true;

        }

        return false;

    }

    private void initMazeSolver() {

        for (int idxRow = 0; idxRow < mazeRows; idxRow++) {

            for (int idxCol = 0; idxCol < mazeCols; idxCol++) {

                mazeSolver[idxRow][idxCol] =
                        maze[idxRow][idxCol] == PASSAGE ?
                                new Cell(idxRow, idxCol, PASSAGE) : new Cell(idxRow, idxCol, WALL);

                getEntrances(idxRow, idxCol, maze[idxRow][idxCol]);

            }

        }

    }

    private void getEntrances(int idxRow, int idxCol, int value) {

        if (value == PASSAGE) {

            if (idxCol == 0) {

                idxColFirstEntrance = idxCol;

                idxRowFirstEntrance = idxRow;

            } if (idxCol == mazeCols - 1) {

                idxColSecondEntrance = idxCol;

                idxRowSecondEntrance = idxRow;

            }

            if (idxRow == 0) {

                idxColFirstEntrance = idxCol;

                idxRowFirstEntrance = idxRow;


            } else if (idxRow == mazeRows - 1) {

                idxColSecondEntrance = idxCol;

                idxRowSecondEntrance = idxRow;

            }

        }

    }
    
    private static class Cell {

        final int idxRow;

        final int idxCol;

        final int status;

        boolean isVisited;

        public Cell(int idxRow, int idxCol, int status) {

            this.idxRow = idxRow;

            this.idxCol = idxCol;

            this.status = status;

        }

        public int getIdxCol() {

            return idxCol;

        }

        public int getIdxRow() {

            return idxRow;

        }

        public int getStatus() {

            return status;

        }

        public boolean isVisited() {

            return isVisited;

        }

        public void setVisited(boolean visited) {

            isVisited = visited;

        }

    }

}
