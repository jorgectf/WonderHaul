package io.github.winterbear.wintercore.utils;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class EnumUtils {

    public static <T extends Enum<T>> EnumSet<T> createGenericEmptyEnumSet(Class<T> enumClass){
        return EnumSet.noneOf(enumClass);
    }

    public static <T extends Enum<T>> EnumSet<T> combine(Class<T> type, EnumSet<T>... sets){
        EnumSet<T> set = createGenericEmptyEnumSet(type);
        Arrays.stream(sets).forEach(set::addAll);
        return set;
    }


}
