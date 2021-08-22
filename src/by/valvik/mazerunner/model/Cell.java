package by.valvik.mazerunner.model;

import by.valvik.mazerunner.constant.Status;

import java.util.Objects;

import static by.valvik.mazerunner.constant.Status.WALL;
import static java.util.Objects.hash;

public class Cell {

    private final Position position;

    private Status status;

    private boolean isVisited;

    private boolean isEntrance;

    public Cell(Position position) {

        this.position = position;

        status = WALL;

    }

    public Position getPosition() {

        return position;

    }

    public Status getStatus() {

        return status;

    }

    public void setStatus(Status status) {

        this.status = status;

    }

    public boolean isVisited() {

        return isVisited;

    }

    public void setVisited(boolean visited) {

        isVisited = visited;

    }

    public boolean isEntrance() {

        return isEntrance;

    }

    public void setEntrance(boolean entrance) {

        isEntrance = entrance;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Cell cell)) return false;

        return Objects.equals(status, cell.status);

    }

    @Override
    public int hashCode() {

        return hash(status);

    }

}
