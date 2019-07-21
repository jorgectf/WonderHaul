package io.github.winterbear.wintercore.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class RandomUtils {

    public static int getIntegerBetween(int min, int max){
        double randomDouble = Math.random();
        randomDouble = randomDouble * max;
        return (int) randomDouble + min;
    }

    public static double getDoubleBetween(int min, int max){
        double randomDouble = Math.random();
        randomDouble = randomDouble * max;
        return randomDouble + min;
    }


    public static <T> T getRandomElementOf(List<T> collection){
        int index = ThreadLocalRandom.current()
                .nextInt(collection.size());
        return collection.get(index);
    }


}
