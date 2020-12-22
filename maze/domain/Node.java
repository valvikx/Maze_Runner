package maze.domain;

import java.util.*;

public class Node {

    private final int idxRow;

    private final int idxCol;

    private boolean isChecked;

    private Node parent;

    private int size;

    // Direction of edges at a node: 0-TOP, 1-LEFT, 2-RIGHT, 3-BOTTOM
    private final List<Edge> edges = new ArrayList<>(Arrays.asList(null, null, null, null));

    public Node(int idxRow, int idxCol) {

        this.idxRow = idxRow;

        this.idxCol = idxCol;

        parent = this;

        size = 1;

    }

    public void setEdge (Edge edge, int direction){

        edges.set(direction, edge);

    }

    public Edge getMinEdge() {

        return edges
                    .stream()
                    .filter(e -> e != null && !e.isChecked())
                    .min(Comparator.comparing(Edge::getWeight))
                    .orElse(null);

    }

    public boolean isChecked() {

        return isChecked;

    }

    public void setChecked(boolean checked) {

        isChecked = checked;

    }

    public int getIdxRow() {

        return idxRow;

    }

    public int getIdxCol() {

        return idxCol;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return idxRow == node.idxRow && idxCol == node.idxCol && isChecked == node.isChecked && size == node.size && Objects.equals(parent, node.parent) && Objects.equals(edges, node.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idxRow, idxCol, isChecked, parent, size, edges);
    }

}
