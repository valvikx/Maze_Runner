package by.valvik.mazerunner.algorithm;

import by.valvik.mazerunner.model.Edge;
import by.valvik.mazerunner.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.comparing;

public class PrimAlg extends MSTalg {

    @Override
    public List<Edge> generate() {

        List<Node> checkedNodes = new ArrayList<>();

        Node[][] adjacencyMatrix = getGraph().getAdjacencyMatrix();

        Node currentNode = adjacencyMatrix[0][0];

        checkedNodes.add(currentNode);

        currentNode.setChecked(true);

        Edge minEdge = currentNode.getMinEdge();

        while (checkedNodes.size() < getGraph().getAdjMatrixRowCount() * getGraph().getAdjMatrixColCount()) {

            Node nextNode = minEdge.getUncheckedNode();

            if (nextNode != null) {

                getMinimumSpanningTree().add(minEdge);

                checkedNodes.add(nextNode);

                nextNode.setChecked(true);

            }

            minEdge.setChecked(true);

            minEdge = getMinEdgeFrom(checkedNodes);

        }

        return getMinimumSpanningTree();

    }

    private Edge getMinEdgeFrom(List<Node> nodes) {

        return nodes.stream()
                    .map(Node::getMinEdge)
                    .filter(Objects::nonNull)
                    .min(comparing(Edge::getWeight))
                    .get();

    }

}
