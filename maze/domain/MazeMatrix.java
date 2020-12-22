package maze.domain;

import java.util.Arrays;

import static maze.constant.MazeConstants.WALL;

public class MazeMatrix {

    private final int mazeRows;

    private final int mazeCols;

    private int[][] maze;

    public MazeMatrix(int mazeRows, int mazeCols) {

        this.mazeRows = mazeRows;

        this.mazeCols = mazeCols;

        maze = new int[mazeRows][mazeCols];

        mazeInit();

    }

    public MazeMatrix(int[][] maze) {

        this.maze = maze;

        this.mazeRows = maze.length;

        this.mazeCols = maze[0].length;

    }

    public int getMazeRows() {

        return mazeRows;

    }

    public int getMazeCols() {

        return mazeCols;

    }

    public int[][] getMaze() {

        return maze;

    }

    public void setMaze(int[][] maze) {

        this.maze = maze;

    }

    private void mazeInit() {

        Arrays
              .stream(maze)
              .forEach(rows -> Arrays.fill(rows, WALL));

    }

}
