package io.github.winterbear.wintercore.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionUtils {

    public static <T> T getRandomElementOf(List<T> collection){
        int index = ThreadLocalRandom.current()
                .nextInt(collection.size());
        return collection.get(index);
    }

}
