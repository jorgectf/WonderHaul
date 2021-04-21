package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 23/09/2020.
 */
public enum SlotSize {

    THREE(new Integer[]{0, 1, 7, 8}, new Integer[]{2, 6}),
    FOUR(new Integer[]{0, 8}, new Integer[]{1, 4, 7}),
    FIVE(new Integer[]{0, 8}, new Integer[]{1, 7});


    private Integer[] blankColumns;

    private Integer[] borderColumns;

    SlotSize(Integer[] blankColumns, Integer[] borderColumns){
        this.blankColumns = blankColumns;
        this.borderColumns = borderColumns;

    }

    public List<Integer> getBlankColumns(){
        return Arrays.asList(blankColumns);
    }

    public List<Integer> getBorderColumns(){
        return Arrays.asList(borderColumns);
    }




}
