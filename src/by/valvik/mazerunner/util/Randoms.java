package by.valvik.mazerunner.util;

import java.util.Random;

public class Randoms {

    private static final Random RANDOM = new Random();

    private Randoms() {

    }

    public static int getRandomValue(int[] array) {

        int rnd = RANDOM.nextInt(array.length);

        return array[rnd];

    }

    public static <T> T getRandomElement(T[] array) {

        int rnd = RANDOM.nextInt(array.length);

        return array[rnd];

    }

}
