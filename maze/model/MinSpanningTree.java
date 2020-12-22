package maze.model;

import maze.domain.Edge;
import maze.domain.MazeMatrix;
import maze.domain.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class MinSpanningTree {

    protected final List<Edge> minSpanningTree = new ArrayList<>();

    protected final List<Node> nodesOfGraph = new ArrayList<>();

    protected final Graph graph;

    protected final int adjMatrixRows;

    protected final int adjMatrixCols;

    public MinSpanningTree(MazeMatrix maze) {

        this.adjMatrixRows = (maze.getMazeRows() - 1) / 2;

        this.adjMatrixCols = (maze.getMazeCols() - 1) / 2;

        graph = new Graph(adjMatrixRows, adjMatrixCols);

        graph.generate();

    }

    public abstract List<Edge> generate();

}
