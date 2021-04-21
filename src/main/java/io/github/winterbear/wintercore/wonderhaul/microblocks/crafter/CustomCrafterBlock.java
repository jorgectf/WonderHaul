package io.github.winterbear.wintercore.wonderhaul.microblocks.crafter;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.crafting.CustomCrafter;
import io.github.winterbear.wintercore.wonderhaul.crafting.Recipe;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

public class CustomCrafterBlock {

    private CustomCrafter crafter;

    private Block block;

    private BlockMetadata metadata;

    public CustomCrafterBlock(CustomCrafter crafter, Block block, ItemStack item){
        this.crafter = crafter;
        this.block = block;
        this.metadata = new BlockMetadata(block, ItemUtils.oneOf(item), "Custom Crafter");
    }

    public boolean initiateCrafting(Player player, List<ItemStack> ingredients){
        Optional<Recipe> recipe = crafter.getRecipes().stream()
                .filter(r -> r.canCraft(ingredients))
                .findFirst();

        if(recipe.isPresent()){

            //TODO Start Recipe


        }

        return false;


    }


}
