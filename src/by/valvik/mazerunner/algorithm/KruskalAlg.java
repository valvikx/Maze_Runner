package by.valvik.mazerunner.algorithm;

import by.valvik.mazerunner.model.Edge;
import by.valvik.mazerunner.model.Node;

import java.util.List;

import static java.util.Comparator.comparing;

public class KruskalAlg extends MSTalg {

    @Override
    public List<Edge> generate() {

        List<Edge> graphEdges = getGraph().getEdges();

        graphEdges.sort(comparing(Edge::getWeight));

        graphEdges.forEach(edge -> {

                                Node firstFoundNode = find(edge.getFirstNode());

                                Node secondFoundNode = find(edge.getSecondNode());

                                if (!firstFoundNode.equals(secondFoundNode)) {

                                    getMinimumSpanningTree().add(edge);

                                    union(firstFoundNode, secondFoundNode);

                                }

                            });

        return getMinimumSpanningTree();

    }

    private Node find(Node node) {

        if (!node.getParent().equals(node)) {

            Node parent = find(node.getParent());

            node.setParent(parent);

            return parent;

        }

        return node;

    }

    private void union(Node firstFoundNode, Node secondFoundNode) {

        if (firstFoundNode.getSize() < secondFoundNode.getSize()) {

            Node temp = secondFoundNode;

            secondFoundNode = firstFoundNode;

            firstFoundNode = temp;

        }

        secondFoundNode.setParent(firstFoundNode);

        firstFoundNode.setSize(firstFoundNode.getSize() + secondFoundNode.getSize());

    }
    
}
