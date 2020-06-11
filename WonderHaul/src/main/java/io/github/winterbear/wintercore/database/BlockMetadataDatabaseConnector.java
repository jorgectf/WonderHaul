package io.github.winterbear.wintercore.database;

import io.github.winterbear.wintercore.wonderhaul.BlockStorage.BlockMetadata;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 30/05/2020.
 */
public class BlockMetadataDatabaseConnector {


    private DataExecutor executor;

    public BlockMetadataDatabaseConnector(DataExecutor executor){
        this.executor = executor;
    }


    public void createTableIfNotExists(){
        String sql = "CREATE TABLE IF NOT EXISTS BlockMetaData(Long id, ChunkRef varchar(64), BlockRef varchar(64), Item varchar(2000), DropEnabled bit, Type varchar(64), Counter integer(255))";
    }

    public void save(Map<String, Map<String, BlockMetadata>> map){

        for(Map.Entry<String, Map<String, BlockMetadata>> chunkMap : map.entrySet()){
            for(Map.Entry<String, BlockMetadata> blockMetadataEntry : chunkMap.getValue().entrySet()){
                String chunkRef = chunkMap.getKey();
                String blockRef = blockMetadataEntry.getKey();
                BlockMetadata blockMetadata = blockMetadataEntry.getValue();

                String serialisedInternalItem = ItemStackSerializer.serialiseItem(blockMetadata.getInternalItem());
                Boolean drops = blockMetadata.drops();
                String type = blockMetadata.getType();
                Map<String, String> customInventories = blockMetadata.getCustomInventories().entrySet()
                        .stream().collect(Collectors.toMap(e -> e.getKey(), e -> ItemStackSerializer.serialiseItems(e.getValue())));
                Integer counter = blockMetadata.getCounter();





            }


        }


    }


    public Map<String, Map<String, BlockMetadata>> load(){


        Map<String, Map<String, BlockMetadata>> map = new HashMap<>();


        ResultSet results = executor.executeResults("");



        return map;


    }








}
