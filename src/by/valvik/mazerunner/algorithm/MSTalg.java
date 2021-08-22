package by.valvik.mazerunner.algorithm;

import by.valvik.mazerunner.model.Edge;
import by.valvik.mazerunner.model.Graph;

import java.util.ArrayList;
import java.util.List;

public abstract class MSTalg {

    private List<Edge> minimumSpanningTree;

    private Graph graph;

    public abstract List<Edge> generate();

    public void init(int mazeRowSize, int mazeColSize) {

        int adjMatrixRowCount = (mazeRowSize - 1) / 2;

        int adjMatrixColCount = (mazeColSize - 1) / 2;

        minimumSpanningTree = new ArrayList<>();

        graph = new Graph(adjMatrixRowCount, adjMatrixColCount);

        graph.generate();

    }

    public List<Edge> getMinimumSpanningTree() {

        return minimumSpanningTree;

    }

    public Graph getGraph() {

        return graph;

    }

}
