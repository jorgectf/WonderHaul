package io.github.winterbear.wintercore.utils;

import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.bukkit.Location;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;
import ru.beykerykt.lightapi.chunks.ChunkInfo;

import java.util.Optional;

/**
 * Created by WinterBear on 16/04/2021.
 */
public class LightUtils {

    public static boolean toggleLight(BlockMetadata blockMetadata){

        Optional<String> lit = blockMetadata.getProperty("light");
        boolean isLit = false;

        if(!lit.isPresent()){
            blockMetadata.getProperties().put("light", "true");
            LightAPI.createLight(blockMetadata.getInternalLocation(), LightType.BLOCK, 15, true);
            isLit = true;
        } else {
            if(lit.get().equalsIgnoreCase("true")){
                blockMetadata.setProperty("light", "false");
                LightAPI.deleteLight(blockMetadata.getInternalLocation(), LightType.BLOCK, true);
                isLit = false;
            } else {
                blockMetadata.setProperty("light", "true");
                LightAPI.createLight(blockMetadata.getInternalLocation(), LightType.BLOCK, 15, true);
                isLit = true;
            }
        }
        updateLighting(blockMetadata.getInternalLocation());
        return isLit;

    }

    public static void updateLighting(Location location){
        for(ChunkInfo info: LightAPI.collectChunks(location, LightType.BLOCK, 15)){
            LightAPI.updateChunk(info, LightType.BLOCK);
        }
    }

    public static void removeLight(BlockMetadata blockMetadata){
        LightAPI.deleteLight(blockMetadata.getInternalLocation(), LightType.BLOCK, true);
        blockMetadata.setProperty("light", "false");
        updateLighting(blockMetadata.getInternalLocation());
    }


}
