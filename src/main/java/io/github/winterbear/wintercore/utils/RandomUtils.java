package io.github.winterbear.wintercore.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class RandomUtils {

    public static int getIntegerBetween(int min, int max){
        double randomDouble = Math.random();
        randomDouble = randomDouble * (max-min);
        return (int) randomDouble + min;
    }

    public static double getDoubleBetween(double min, double max){
        double randomDouble = Math.random();
        randomDouble = randomDouble * (max-min);
        return randomDouble + min;
    }

    public static float getFloatBetween(float min, float max){

        float randomFloat = (float) Math.random();
        randomFloat = randomFloat * (max-min);
        return randomFloat + min;
    }


    public static <T> T getRandomElementOf(Collection<T> collection){
        int index = ThreadLocalRandom.current()
                .nextInt(collection.size());
        Iterator<T> iterator = collection.iterator();
        T result = iterator.next();
        while(index > 0){
            index--;
            result = iterator.next();
        }

        return result;
    }


}
