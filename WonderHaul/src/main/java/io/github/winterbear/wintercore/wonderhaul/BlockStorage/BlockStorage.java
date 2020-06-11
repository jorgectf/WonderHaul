package io.github.winterbear.wintercore.wonderhaul.BlockStorage;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class BlockStorage {

    private Map<String, Map<String, BlockMetadata>> metadata;

    public BlockStorage(){
        metadata = new ConcurrentHashMap<>();
    }

    public Optional<BlockMetadata> get(Location location){

        Map<String, BlockMetadata> chunkMeta = metadata.get(getChunkRef(location.getChunk()));

        if(chunkMeta != null){
            return Optional.ofNullable(chunkMeta.get(getBlockRef(location)));
        }

        return Optional.empty();
    }

    public void setBlockMetadata(Location location, BlockMetadata data){
        String chunkRef = getChunkRef(location.getChunk());
        Map<String, BlockMetadata> chunkMeta = metadata.get(chunkRef);

        if(chunkMeta == null){
            chunkMeta = new HashMap<>();
            metadata.put(chunkRef, chunkMeta);
        }

        chunkMeta.put(getBlockRef(location), data);
    }

    public void clearBlockMetadata(Location location){
        String chunkRef = getChunkRef(location.getChunk());
        Map<String, BlockMetadata> chunkMeta = metadata.get(chunkRef);
        if(chunkMeta != null){
            chunkMeta.remove(getBlockRef(location));
        }
    }

    private String getChunkRef(Chunk chunk){

        String world = chunk.getWorld().getName();
        String x_ref = String.valueOf(chunk.getX());
        String z_ref = String.valueOf(chunk.getZ());

        return world + "_" + x_ref + "_" + z_ref;

    }

    private String getBlockRef(Location location){

        return location.getBlockX() + "_" + location.getBlockY() + "_" + location.getBlockZ();
    }



    public void loadFromDB(){

    }

    public void saveToDB(){

    }


}
