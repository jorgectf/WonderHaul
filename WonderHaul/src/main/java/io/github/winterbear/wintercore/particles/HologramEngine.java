package io.github.winterbear.wintercore.particles;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 15/08/2020.
 */
public class HologramEngine {

    public static Map<BlockMetadata, Hologram> hologramMap = new HashMap<>();

    public static boolean hologramDoesNotExist(BlockMetadata metadata){
        return !hologramMap.containsKey(metadata) || hologramMap.get(metadata).isDeleted();
    }

    public static void refreshHologram(BlockMetadata metadata, Hologram hologram){
        if(hologramMap.containsKey(metadata) && hologramMap.get(metadata).isDeleted()){
            hologramMap.get(metadata).delete();
            hologramMap.remove(metadata);
        }
        if(!hologramMap.containsKey(metadata)){
            hologramMap.put(metadata, hologram);
        }
    }

    public static Hologram getHologram(BlockMetadata metadata){
        return hologramMap.get(metadata);
    }

    public static void setHologram(BlockMetadata metadata, Hologram hologram){
        if(hologramMap.containsKey(metadata)){
            hologramMap.get(metadata).delete();
        }
        hologramMap.put(metadata, hologram);
    }

    public static void removeHologram(BlockMetadata metadata){
        if(hologramMap.containsKey(metadata)){
            hologramMap.get(metadata).delete();
            hologramMap.remove(metadata);
        }
    }

}
