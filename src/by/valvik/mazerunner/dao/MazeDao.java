package by.valvik.mazerunner.dao;

import by.valvik.mazerunner.exception.MazeException;
import by.valvik.mazerunner.model.Maze;

public interface MazeDao {

    void save(Maze maze) throws MazeException;

    Maze load() throws MazeException;

}
