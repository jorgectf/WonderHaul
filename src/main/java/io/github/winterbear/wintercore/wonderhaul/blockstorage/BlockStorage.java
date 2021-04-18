package io.github.winterbear.wintercore.wonderhaul.blockstorage;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.database.BlockMetadataDAO;
import io.github.winterbear.wintercore.particles.HologramEngine;
import io.github.winterbear.wintercore.particles.ParticleEffectType;
import io.github.winterbear.wintercore.particles.ParticleEngine;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class BlockStorage {

    private BlockMetadataDAO blockMetadataDAO = new BlockMetadataDAO();

    private Map<String, Map<String, BlockMetadata>> metadata;

    private List<BlockMetadata> deleted = new ArrayList<>();

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

    public void setBlockMetadata(BlockMetadata data){
        if(data.getInternalLocation() == null){
            ChatUtils.warn("Block Metadata loaded with null location!!!!");
            deleted.add(data);
            return;
        }

        if(data.getProperties().containsKey("ParticleEffect")) {
            ParticleEngine.registerEffect(data, ParticleEffectType.valueOf(data.getProperty("ParticleEffect").get()));
        }

        String chunkRef = getChunkRef(data.getInternalLocation().getChunk());
        Map<String, BlockMetadata> chunkMeta = metadata.computeIfAbsent(chunkRef, k -> new HashMap<>());
        chunkMeta.put(getBlockRef(data.getInternalLocation()), data);
    }

    public void clearBlockMetadata(Location location){
        String chunkRef = getChunkRef(location.getChunk());
        Map<String, BlockMetadata> chunkMeta = metadata.get(chunkRef);
        if(chunkMeta != null){
            BlockMetadata data = chunkMeta.get(getBlockRef(location));
            if(data.getProperties().containsKey("ParticleEffect")){
                ParticleEngine.cancelEffect(data);
            }
            HologramEngine.removeHologram(data);
            deleted.add(data);
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
        List<BlockMetadata> loaded = blockMetadataDAO.getMetadata();
        loaded.forEach(this::setBlockMetadata);
        //loaded.forEach(l -> ChatUtils.info("loading:" + l.getLocationReference()));
        ChatUtils.info("Loaded " + loaded.size() + " block metadata");


    }

    public void saveToDB(){
        List<BlockMetadata> values = metadata.values().stream()
                .flatMap(m -> Stream.of(m.values()))
                .flatMap(Collection::stream)
                .filter(b -> b.getLocationReference() != null)
                .collect(Collectors.toList());
        //deleted.forEach(l -> ChatUtils.info("deleting:" + l.getLocationReference()));
        //values.forEach(l -> ChatUtils.info("saving:" + l.getLocationReference()));

        blockMetadataDAO.delete(deleted);
        deleted = new ArrayList<>();
        blockMetadataDAO.save(values);
    }


}
