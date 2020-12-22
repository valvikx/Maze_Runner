package maze.model;

import maze.domain.Edge;
import maze.domain.MazeMatrix;
import maze.domain.Node;
import maze.util.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static maze.constant.MazeConstants.PASSAGE;
import static maze.constant.MazeConstants.WALL;

public class MazeGenerator {

    private final int[][] maze;

    private final int mazeRows;

    private final int mazeCols;

    public MazeGenerator(MazeMatrix mazeMatrix) {

        this.mazeRows = mazeMatrix.getMazeRows();

        this.mazeCols = mazeMatrix.getMazeCols();

        this.maze = mazeMatrix.getMaze();

    }

    public void generate(List<Edge> spanningTree) {

        int checkedEdge = 0;

        while (checkedEdge < spanningTree.size()) {

            Edge edge = spanningTree.get(checkedEdge);

            Node firstNode = edge.getFirstNode();

            int[] coordsFirstNode = setMazePassage(firstNode);

            Node secondNode = edge.getSecondNode();

            int[] coordsSecondNode = setMazePassage(secondNode);

            connectPassages(coordsFirstNode, coordsSecondNode);

            checkedEdge++;

        }

        correctDoubleWalls();

        setMazeEntrances();

    }

    private int[] setMazePassage(Node currentNode) {

        int[] coords = {-1, -1};

        int idxRow = convertToIdxOfMaze(currentNode.getIdxRow());

        coords[0] = idxRow;

        int idxCol = convertToIdxOfMaze(currentNode.getIdxCol());

        coords[1] = idxCol;

        maze[idxRow][idxCol] = PASSAGE;

        return coords;

    }

    private void connectPassages(int[] coordsFirstNode, int[] coordsSecondNode) {

        int idxRowFirstNode = coordsFirstNode[0];

        int idxColFirstNode = coordsFirstNode[1];

        int idxRowSecondNode = coordsSecondNode[0];

        int idxColSecondNode = coordsSecondNode[1];

        if (idxRowFirstNode == idxRowSecondNode) {

            maze[idxRowFirstNode][idxColFirstNode + 1] = PASSAGE;

        } else if (idxColFirstNode == idxColSecondNode) {

            maze[idxRowFirstNode + 1][idxColFirstNode] = PASSAGE;

        }

    }

    private void correctDoubleWalls() {

        if (mazeRows % 2 == 0) {

            correctHorizontalDoubleWalls();

        }

        if (mazeCols % 2 == 0) {

            correctVerticalDoubleWalls();

        }

    }

    private void correctVerticalDoubleWalls() {

        int idxCol = mazeCols - 2;

        IntStream
                 .range(1, mazeRows - 1)
                 .filter(idxRow ->
                         maze[idxRow][idxCol - 1] == PASSAGE && maze[idxRow][idxCol - 2] == PASSAGE)
                 .forEach(idxRow -> maze[idxRow][idxCol] = PASSAGE);

    }

    private void correctHorizontalDoubleWalls() {

        int idxRow = mazeRows - 2;

        IntStream
                 .range(1, mazeCols - 1)
                 .filter(idxCol ->
                         maze[idxRow - 1][idxCol] == PASSAGE && maze[idxRow - 2][idxCol] == PASSAGE)
                 .forEach(idxCol -> maze[idxRow][idxCol] = PASSAGE);

    }

    private void setMazeEntrances() {

        int[] possibleEntrances;

        int idx;

        if (mazeRows < mazeCols) {

            possibleEntrances = getPossibleVerticalEntrances(0, 1);

            idx = Utils.getRandomElement(possibleEntrances);

            maze[idx][0] = PASSAGE;

            possibleEntrances = getPossibleVerticalEntrances(mazeCols - 1, -1);

            idx = Utils.getRandomElement(possibleEntrances);

            maze[idx][mazeCols - 1] = PASSAGE;


        } else {

            possibleEntrances = getPossibleHorizontalEntrances(0, 1);

            idx = Utils.getRandomElement(possibleEntrances);

            maze[0][idx] = PASSAGE;

            possibleEntrances = getPossibleHorizontalEntrances(mazeRows - 1, -1);

            idx = Utils.getRandomElement(possibleEntrances);

            maze[mazeRows - 1][idx] = PASSAGE;

        }

    }

    private int[] getPossibleVerticalEntrances(int idxCol, int dir) {

        return IntStream
                       .range(1, mazeRows - 1)
                       .filter(idxRow -> maze[idxRow][idxCol] != maze[idxRow][idxCol + dir])
                       .toArray();

    }

    private int[] getPossibleHorizontalEntrances(int idxRow, int dir) {

        return IntStream
                       .range(1, mazeCols - 1)
                       .filter(idxCol -> maze[idxRow][idxCol] != maze[idxRow + dir][idxCol])
                       .toArray();

    }

    private int convertToIdxOfMaze(int idxOfTree) {

        return 2 * idxOfTree + 1;

    }

}
