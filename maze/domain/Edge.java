package maze.domain;

public class Edge {

    private final int weight;

    private final Node firstNode;

    private final Node secondNode;

    private boolean isChecked;

    public Edge(Node firstNode, Node secondNode, int weight) {

        this.firstNode = firstNode;

        this.secondNode = secondNode;

        this.weight = weight;

    }

    public int getWeight() {

        return weight;

    }

    public Node getUncheckedNode() {

        if (firstNode.isChecked() && secondNode.isChecked()) {

            return null;

        }

        return firstNode.isChecked() ? secondNode : firstNode;

    }

    public boolean isChecked() {

        return isChecked;

    }

    public void setChecked(boolean checked) {

        isChecked = checked;

    }

    public Node getFirstNode() {

        return firstNode;

    }

    public Node getSecondNode() {

        return secondNode;

    }

}
