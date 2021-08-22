package by.valvik.mazerunner.view.impl;

import by.valvik.mazerunner.context.Holder;
import by.valvik.mazerunner.view.Page;

public class InfoPage implements Page {

    private static final Page INSTANCE = new InfoPage();

    private InfoPage() {

    }

    @Override
    public void display(Holder holder) {

        System.out.println(holder.getMessage());

    }

    public static Page getInstance() {

        return INSTANCE;

    }

}
