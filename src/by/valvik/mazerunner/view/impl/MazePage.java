package by.valvik.mazerunner.view.impl;

import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.model.Cell;
import by.valvik.mazerunner.view.Page;

public class MazePage implements Page {

    private static final Page INSTANCE = new MazePage();

    private MazePage() {

    }

    @Override
    public void display(Holder holder) {

        Cell[][] matrix = holder.getMaze().getMatrix();

        for (Cell[] rows : matrix) {

            for (Cell cell : rows) {

                System.out.print(cell.getStatus().getSign());

            }

            System.out.println();

        }

    }

    public static Page getInstance() {

        return INSTANCE;

    }

}
