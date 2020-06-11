package io.github.winterbear.wintercore.wonderhaul.Equipment.Decorations;

import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.Dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Generators.Generator;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Tier;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class StuffedToyGenerator implements Generator {

    public enum Toys {

        RABBIT("Rabbit", Tier.ORDINARY, TexturedHeads.TOY_RABBIT),
        FROG("Frog", Tier.ORDINARY, TexturedHeads.TOY_FROG),
        BIRD("Bird", Tier.ORDINARY, TexturedHeads.TOY_BIRD),
        DOG("Dog", Tier.ORDINARY, TexturedHeads.TOY_DOG),
        CAT("Cat", Tier.ORDINARY, TexturedHeads.TOY_CAT),
        FOX("Fox", Tier.ORDINARY, TexturedHeads.TOY_FOX),
        CHICKEN("Chicken", Tier.ORDINARY, TexturedHeads.TOY_CHICKEN),
        PIG("Pig", Tier.ORDINARY, TexturedHeads.TOY_PIG),
        MOUSE("Mouse", Tier.ORDINARY, TexturedHeads.TOY_MOUSE),
        HEDGEHOG("Hedgehog", Tier.ORDINARY, TexturedHeads.TOY_HEDGEHOG),
        BEE("Bee", Tier.ORDINARY, TexturedHeads.TOY_BEE),
        HAMSTER("Hamster", Tier.ORDINARY, TexturedHeads.TOY_HAMSTER),
        TIGER("Tiger", Tier.UNUSUAL, TexturedHeads.TOY_TIGER),
        LION("Lion", Tier.UNUSUAL, TexturedHeads.TOY_LION),
        HUSKY("Husky", Tier.UNUSUAL, TexturedHeads.TOY_DOG_HUSKY),
        YORKIE("Yorkie", Tier.UNUSUAL, TexturedHeads.TOY_DOG_YORKIE),
        COLLIE("Collie", Tier.UNUSUAL, TexturedHeads.TOY_DOG_COLLIE),
        PUG("Pug", Tier.UNUSUAL, TexturedHeads.TOY_DOG_PUG),
        TABBY_CAT("Tabby Cat", Tier.UNUSUAL, TexturedHeads.TOY_CAT_TABBY),
        OWL("Owl", Tier.UNUSUAL, TexturedHeads.TOY_OWL),
        TORTOISE("Tortoise", Tier.UNUSUAL, TexturedHeads.TOY_TORTOISE),
        HERMIT_CRAB("Hermit Crab", Tier.UNUSUAL, TexturedHeads.TOY_HERMIT_CRAB),
        WARTHOG("Warthog", Tier.UNUSUAL, TexturedHeads.TOY_WARTHOG),
        WHITE_TIGER("White Tiger", Tier.RARE, TexturedHeads.TOY_TIGER_WHITE),
        SHIBU("Shibu", Tier.RARE, TexturedHeads.TOY_DOG_SHIBU),
        PENGUIN("Penguin", Tier.RARE, TexturedHeads.TOY_PENGUIN),
        JACKALOPE("Jackalope", Tier.RARE, TexturedHeads.TOY_JACKALOPE),
        PANDA("Panda", Tier.RARE, TexturedHeads.TOY_PANDA),
        WHALE("Whale", Tier.RARE, TexturedHeads.TOY_WHALE),
        CTHULHU("Cthulhu", Tier.RARE, TexturedHeads.TOY_CTHULHU);

        public static Set<Toys> getByTier(Tier tier){
            return Arrays.stream(Toys.values())
                    .filter(toy -> toy.tier.equals(tier))
                    .collect(Collectors.toSet());
        }

        public static Toys getRandomToy(Tier tier){
            return RandomUtils.getRandomElementOf(getByTier(tier));
        }

        private Supplier<ItemStack> texture;

        private String name;

        private Tier tier;

        Toys(String name, Tier tier, Supplier<ItemStack> texture){
            this.name = name;
            this.tier = tier;
            this.texture = texture;
        }

        ItemStack generate(){
            return format(texture.get(), name, tier);
        }

    }

    @Override
    public ItemStack create() {
        if(Chance.roll(90)){
            return Toys.getRandomToy(Tier.ORDINARY).generate();
        } else if (Chance.roll(70)){
            return Toys.getRandomToy(Tier.UNUSUAL).generate();
        } else {
            return Toys.getRandomToy(Tier.RARE).generate();
        }
    }

    private static ItemStack format(ItemStack baseItem, String name, Tier tier){
        ItemBuilder.setDisplayName(baseItem, tier.getColorCode() + "Stuffed " + name + " Toy");
        LoreUtils.addLoreLine(baseItem, tier.getColouredLore("Decoration", "Stuffed Toy"));
        return baseItem;
    }

    @Override
    public String getName() {
        return "Stuffed Toy Generator";
    }
}
