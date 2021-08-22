package by.valvik.mazerunner.model;

import java.util.ArrayList;
import java.util.List;

import static by.valvik.mazerunner.constant.Direction.*;
import static by.valvik.mazerunner.util.Randoms.getRandomValue;

public class Graph {

    private static final int[] WEIGHTS = {5, 10, 15, 20, 25};

    private final int adjMatrixRowCount;

    private final int adjMatrixColCount;

    private final Node[][] adjacencyMatrix;

    private final List<Edge> edges;

    public Graph(int adjMatrixRowCount, int adjMatrixColCount) {

        this.adjMatrixRowCount = adjMatrixRowCount;

        this.adjMatrixColCount = adjMatrixColCount;

        adjacencyMatrix = new Node[adjMatrixRowCount][adjMatrixColCount];

        adjacencyMatrixInit();

        edges = new ArrayList<>();

    }

    public void generate() {

        for (int idxRow = 0; idxRow < adjMatrixRowCount - 1; idxRow++) {

            for (int idxCol = 0; idxCol < adjMatrixColCount - 1; idxCol++) {

                generateEdges(idxRow, idxCol);

            }

        }

    }

    private void generateEdges(int idxRow, int idxCol) {

        Node currentNode = adjacencyMatrix[idxRow][idxCol];

        Node nextColNode = adjacencyMatrix[idxRow][idxCol + 1];

        Node nextRowNode = adjacencyMatrix[idxRow + 1][idxCol];

        Edge edge = new Edge(currentNode, nextColNode, getEdgeWeight());

        currentNode.setEdge(RIGHT.getIdx(), edge);

        nextColNode.setEdge(LEFT.getIdx(), edge);

        edges.add(edge);

        edge = new Edge(currentNode, nextRowNode, getEdgeWeight());

        currentNode.setEdge(BOTTOM.getIdx(), edge);

        nextRowNode.setEdge(TOP.getIdx(), edge);

        edges.add(edge);

        if (idxRow + 1 == adjMatrixRowCount - 1) {

            Node currentNodeOnLastRow = adjacencyMatrix[idxRow + 1][idxCol];

            Node nextColNodeOnLastRow = adjacencyMatrix[idxRow + 1][idxCol + 1];

            edge = new Edge(currentNodeOnLastRow, nextColNodeOnLastRow, getEdgeWeight());

            currentNodeOnLastRow.setEdge(RIGHT.getIdx(), edge);

            nextColNodeOnLastRow.setEdge(LEFT.getIdx(), edge);

            edges.add(edge);

        }

        if (idxCol + 1 == adjMatrixColCount - 1) {

            Node currentNodeOnLastCol = adjacencyMatrix[idxRow][idxCol + 1];

            Node nextRowNodeOnLastCol = adjacencyMatrix[idxRow + 1][idxCol + 1];

            edge = new Edge(currentNodeOnLastCol, nextRowNodeOnLastCol, getEdgeWeight());

            nextRowNodeOnLastCol.setEdge(TOP.getIdx(), edge);

            currentNodeOnLastCol.setEdge(BOTTOM.getIdx(), edge);

            edges.add(edge);

        }

    }

    private int getEdgeWeight() {

        return getRandomValue(WEIGHTS);

    }

    public Node[][] getAdjacencyMatrix() {

        return adjacencyMatrix;

    }

    public List<Edge> getEdges() {

        return edges;

    }

    public int getAdjMatrixRowCount() {

        return adjMatrixRowCount;

    }

    public int getAdjMatrixColCount() {

        return adjMatrixColCount;

    }

    private void adjacencyMatrixInit() {

        for (int idxRow = 0; idxRow < adjMatrixRowCount; idxRow++) {

            for (int idxCol = 0; idxCol < adjMatrixColCount; idxCol++) {

                adjacencyMatrix[idxRow][idxCol] = new Node(new Position(idxRow, idxCol));

            }

        }

    }

}
