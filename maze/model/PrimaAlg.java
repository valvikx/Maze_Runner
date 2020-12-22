package maze.model;

import maze.domain.Edge;
import maze.domain.MazeMatrix;
import maze.domain.Node;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PrimaAlg extends MinSpanningTree{

    public PrimaAlg(MazeMatrix maze) {

        super(maze);

    }

    @Override
    public List<Edge> generate() {

        Node[][] adjacencyMatrix = graph.getAdjacencyMatrix();

        Node currentNode = adjacencyMatrix[0][0];

        nodesOfGraph.add(currentNode);

        currentNode.setChecked(true);

        Edge minEdge = currentNode.getMinEdge();

        while (nodesOfGraph.size() < adjMatrixRows * adjMatrixCols) {

            Node nextNode = minEdge.getUncheckedNode();

            if (nextNode != null) {

                minSpanningTree.add(minEdge);

                nodesOfGraph.add(nextNode);

                nextNode.setChecked(true);

            }

            minEdge.setChecked(true);

            minEdge = getMinEdgeFromNodesOfSpanningTree();

        }

        return minSpanningTree;

    }

    private Edge getMinEdgeFromNodesOfSpanningTree() {

        return nodesOfGraph
                           .stream()
                           .map(Node::getMinEdge)
                           .filter(Objects::nonNull)
                           .min(Comparator.comparing(Edge::getWeight))
                           .get();

    }

}
