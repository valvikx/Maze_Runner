package maze.model;

import maze.domain.Edge;
import maze.domain.Node;
import maze.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private static final int[] WEIGHT_VALUES = {5, 10, 15, 20, 25};

    private final int adjMatrixRows;

    private final int adjMatrixCols;

    private final Node[][] adjacencyMatrix;

    private final List<Edge> edgesOfGraph = new ArrayList<>();

    public Graph(int adjMatrixRows, int adjMatrixCols) {

        this.adjMatrixRows = adjMatrixRows;

        this.adjMatrixCols = adjMatrixCols;

        adjacencyMatrix = new Node[adjMatrixRows][adjMatrixCols];

    }

    public void generate() {

        for (int idxRow = 0; idxRow < adjMatrixRows; idxRow++) {

            for (int idxCol = 0; idxCol < adjMatrixCols; idxCol++) {

                adjacencyMatrix[idxRow][idxCol] = new Node(idxRow, idxCol);

            }

        }

        for (int idxRow = 0; idxRow < adjMatrixRows - 1; idxRow++) {

            for (int idxCol = 0; idxCol < adjMatrixCols - 1; idxCol++) {

                generateEdges(idxRow, idxCol);

            }

        }

    }

    private void generateEdges(int idxRow, int idxCol) {

        Node currentNode = adjacencyMatrix[idxRow][idxCol];

        Node nextColNode = adjacencyMatrix[idxRow][idxCol + 1];

        Node nextRowNode = adjacencyMatrix[idxRow + 1][idxCol];

        int weightOfEdge = getWeightOfEdge();

        Edge edge = new Edge(currentNode, nextColNode, weightOfEdge);

        // Direction of edges at a node: 0-TOP, 1-LEFT, 2-RIGHT, 3-BOTTOM
        currentNode.setEdge(edge, 2);

        nextColNode.setEdge(edge, 1);

        edgesOfGraph.add(edge);

        weightOfEdge = getWeightOfEdge();

        edge = new Edge(currentNode, nextRowNode, weightOfEdge);

        currentNode.setEdge(edge, 3);

        nextRowNode.setEdge(edge, 0);

        edgesOfGraph.add(edge);

        if (idxRow + 1 == adjMatrixRows - 1) {

            Node currentNodeLastRow = adjacencyMatrix[idxRow + 1][idxCol];

            Node nextColNodeLastRow = adjacencyMatrix[idxRow + 1][idxCol + 1];

            weightOfEdge = getWeightOfEdge();

            edge = new Edge(currentNodeLastRow, nextColNodeLastRow, weightOfEdge);

            currentNodeLastRow.setEdge(edge, 2);

            nextColNodeLastRow.setEdge(edge, 1);

            edgesOfGraph.add(edge);

        }

        if (idxCol + 1 == adjMatrixCols - 1) {

            Node currentNodeLastCol = adjacencyMatrix[idxRow][idxCol + 1];

            Node nextRowNodeLastCol = adjacencyMatrix[idxRow + 1][idxCol + 1];

            weightOfEdge = getWeightOfEdge();

            edge = new Edge(currentNodeLastCol, nextRowNodeLastCol, weightOfEdge);

            currentNodeLastCol.setEdge(edge, 3);

            nextRowNodeLastCol.setEdge(edge, 0);

            edgesOfGraph.add(edge);

        }

    }

    private int getWeightOfEdge() {

        return Utils.getRandomElement(WEIGHT_VALUES);

    }

    public Node[][] getAdjacencyMatrix() {

        return adjacencyMatrix;

    }

    public List<Edge> getEdgesOfGraph() {

        return edgesOfGraph;

    }

}
