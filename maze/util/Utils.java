package maze.util;

import java.util.Random;

public class Utils {

    public static int getRandomElement(int[] array) {

        int rnd = new Random().nextInt(array.length);

        return array[rnd];

    }

}
