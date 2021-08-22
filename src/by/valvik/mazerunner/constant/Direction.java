package by.valvik.mazerunner.constant;

public enum Direction {

    TOP(0),
    LEFT(1),
    RIGHT(2),
    BOTTOM(3);

    private final int idx;

    Direction(int idx) {

        this.idx = idx;

    }

    public int getIdx() {

        return idx;

    }

}
