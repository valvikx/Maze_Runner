package by.valvik.mazerunner.model;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Objects.hash;
import static java.util.Objects.nonNull;

public class Node {

    private final Position position;

    private Node parent;

    private int size;

    private boolean isChecked;

    private final List<Edge> edges;

    public Node(Position position) {

        this.position = position;

        parent = this;

        size = 1;

        // List of edges belonging to the node by direction [TOP, LEFT, RIGHT, BOTTOM]
        edges = new ArrayList<>(Arrays.asList(null, null, null, null));

    }

    public void setEdge(int direction, Edge edge){

        edges.set(direction, edge);

    }

    public Position getPosition() {

        return position;

    }

    public Node getParent() {

        return parent;

    }

    public void setParent(Node parent) {

        this.parent = parent;

    }

    public int getSize() {

        return size;

    }

    public void setSize(int size) {

        this.size = size;

    }

    public boolean isChecked() {

        return isChecked;

    }

    public void setChecked(boolean checked) {

        isChecked = checked;

    }

    public Edge getMinEdge() {

        return edges.stream()
                    .filter(e -> nonNull(e) && !e.isChecked())
                    .min(comparing(Edge::getWeight))
                    .orElse(null);

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Node node)) return false;

        return position == node.position &&
               isChecked == node.isChecked &&
               size == node.size &&
               Objects.equals(parent, node.parent) &&
               Objects.equals(edges, node.edges);
    }

    @Override
    public int hashCode() {

        return hash(position, isChecked, parent, size, edges);

    }

}
