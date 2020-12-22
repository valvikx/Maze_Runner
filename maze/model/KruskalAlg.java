package maze.model;

import maze.domain.Edge;
import maze.domain.MazeMatrix;
import maze.domain.Node;

import java.util.Comparator;
import java.util.List;

public class KruskalAlg extends MinSpanningTree{

    public KruskalAlg(MazeMatrix maze) {

        super(maze);

    }

    @Override
    public List<Edge> generate() {

        List<Edge> edgesOfGraph = graph.getEdgesOfGraph();

        edgesOfGraph.sort(Comparator.comparing(Edge::getWeight));

        for (Edge edge : edgesOfGraph) {

            Node firstFoundNode = find(edge.getFirstNode());

            Node secondFoundNode = find(edge.getSecondNode());

            if (!firstFoundNode.equals(secondFoundNode)) {

                minSpanningTree.add(edge);

                Union(firstFoundNode, secondFoundNode);

            }

        }

        return  minSpanningTree;

    }

    private Node find(Node node) {

        if (!node.getParent().equals(node)) {

            Node parent = find(node.getParent());

            node.setParent(parent);

            return parent;

        }

        return node;

    }

    private void Union(Node firstFoundNode, Node secondFoundNode) {

        if (firstFoundNode.getSize() < secondFoundNode.getSize()) {

            Node temp = secondFoundNode;

            secondFoundNode = firstFoundNode;

            firstFoundNode = temp;

        }

        secondFoundNode.setParent(firstFoundNode);

        firstFoundNode.setSize(firstFoundNode.getSize() + secondFoundNode.getSize());

    }
    
}
