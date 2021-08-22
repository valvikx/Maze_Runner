package by.valvik.mazerunner.model;

public class Edge {

    private final Node firstNode;

    private final Node secondNode;

    private final int weight;

    private boolean isChecked;

    public Edge(Node firstNode, Node secondNode, int weight) {

        this.firstNode = firstNode;

        this.secondNode = secondNode;

        this.weight = weight;

    }

    public Node getFirstNode() {

        return firstNode;

    }

    public Node getSecondNode() {

        return secondNode;

    }

    public int getWeight() {

        return weight;

    }

    public boolean isChecked() {

        return isChecked;

    }

    public void setChecked(boolean checked) {

        isChecked = checked;

    }

    public Node getUncheckedNode() {

        if (firstNode.isChecked() && secondNode.isChecked()) {

            return null;

        }

        return firstNode.isChecked() ? secondNode : firstNode;

    }

}
