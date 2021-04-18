package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.*;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class StuffedToyGenerator implements Generator {

    public enum Toys {

        RABBIT("Rabbit", Tier.ORDINARY, TexturedHeads.TOY_RABBIT, Sound.ENTITY_HORSE_EAT),
        FROG("Frog", Tier.ORDINARY, TexturedHeads.TOY_FROG, Sound.ENTITY_VILLAGER_NO),
        BIRD("Bird", Tier.ORDINARY, TexturedHeads.TOY_BIRD, Sound.ENTITY_PARROT_AMBIENT),
        DOG("Dog", Tier.ORDINARY, TexturedHeads.TOY_DOG, Sound.ENTITY_WOLF_PANT),
        CAT("Cat", Tier.ORDINARY, TexturedHeads.TOY_CAT, Sound.ENTITY_CAT_PURR),
        FOX("Fox", Tier.ORDINARY, TexturedHeads.TOY_FOX, Sound.ENTITY_FOX_AMBIENT),
        CHICKEN("Chicken", Tier.ORDINARY, TexturedHeads.TOY_CHICKEN, Sound.ENTITY_CHICKEN_AMBIENT),
        PIG("Pig", Tier.ORDINARY, TexturedHeads.TOY_PIG, Sound.ENTITY_PIG_AMBIENT),
        MOUSE("Mouse", Tier.ORDINARY, TexturedHeads.TOY_MOUSE, Sound.ENTITY_BAT_AMBIENT),
        HEDGEHOG("Hedgehog", Tier.ORDINARY, TexturedHeads.TOY_HEDGEHOG, Sound.ENTITY_FOX_SLEEP),
        BEE("Bee", Tier.ORDINARY, TexturedHeads.TOY_BEE, Sound.ENTITY_BEE_POLLINATE),
        HAMSTER("Hamster", Tier.ORDINARY, TexturedHeads.TOY_HAMSTER, Sound.ENTITY_FOX_SLEEP),
        TIGER("Tiger", Tier.UNUSUAL, TexturedHeads.TOY_TIGER, Sound.ENTITY_POLAR_BEAR_WARNING),
        LION("Lion", Tier.UNUSUAL, TexturedHeads.TOY_LION, Sound.ENTITY_POLAR_BEAR_WARNING),
        HUSKY("Husky", Tier.UNUSUAL, TexturedHeads.TOY_DOG_HUSKY, Sound.ENTITY_WOLF_HOWL),
        YORKIE("Yorkie", Tier.UNUSUAL, TexturedHeads.TOY_DOG_YORKIE, Sound.ENTITY_WOLF_GROWL),
        COLLIE("Collie", Tier.UNUSUAL, TexturedHeads.TOY_DOG_COLLIE, Sound.ENTITY_WOLF_AMBIENT),
        PUG("Pug", Tier.UNUSUAL, TexturedHeads.TOY_DOG_PUG, Sound.ENTITY_WOLF_WHINE),
        TABBY_CAT("Tabby Cat", Tier.UNUSUAL, TexturedHeads.TOY_CAT_TABBY, Sound.ENTITY_CAT_PURREOW),
        OWL("Owl", Tier.UNUSUAL, TexturedHeads.TOY_OWL, Sound.ENTITY_PARROT_AMBIENT),
        TORTOISE("Tortoise", Tier.UNUSUAL, TexturedHeads.TOY_TORTOISE, Sound.ENTITY_PANDA_EAT),
        HERMIT_CRAB("Hermit Crab", Tier.UNUSUAL, TexturedHeads.TOY_HERMIT_CRAB, Sound.ENTITY_SILVERFISH_AMBIENT),
        WARTHOG("Warthog", Tier.UNUSUAL, TexturedHeads.TOY_WARTHOG, Sound.ENTITY_PIGLIN_CELEBRATE),
        WHITE_TIGER("White Tiger", Tier.RARE, TexturedHeads.TOY_TIGER_WHITE, Sound.ENTITY_RAVAGER_ATTACK),
        SHIBU("Shibu", Tier.RARE, TexturedHeads.TOY_DOG_SHIBU, Sound.ENTITY_WOLF_PANT),
        PENGUIN("Penguin", Tier.RARE, TexturedHeads.TOY_PENGUIN, Sound.ENTITY_PARROT_AMBIENT),
        JACKALOPE("Jackalope", Tier.RARE, TexturedHeads.TOY_JACKALOPE, Sound.ENTITY_FOX_SNIFF),
        PANDA("Panda", Tier.RARE, TexturedHeads.TOY_PANDA, Sound.ENTITY_PANDA_AMBIENT),
        WHALE("Whale", Tier.RARE, TexturedHeads.TOY_WHALE, Sound.ENTITY_PLAYER_SPLASH),
        CTHULHU("Cthulhu", Tier.RARE, TexturedHeads.TOY_CTHULHU, Sound.ENTITY_PHANTOM_AMBIENT);

        public static Set<Toys> getByTier(Tier tier){
            return Arrays.stream(Toys.values())
                    .filter(toy -> toy.tier.equals(tier))
                    .collect(Collectors.toSet());
        }

        public static Toys getRandomToy(Tier tier){
            return RandomUtils.getRandomElementOf(getByTier(tier));
        }

        public static Optional<Sound> getSound(BlockMetadata metadata){
            String textureRef = MicroblockUtils.getTextureUrl(metadata.getInternalItem());
            ChatUtils.info(textureRef);
            return Arrays.stream(Toys.values())
                    .filter(toy -> toy.texture.getTextureURL().equals(textureRef))
                    .map(toy -> toy.sound)
                    .findFirst();

        }

        private TexturedHead texture;

        private String name;

        private Tier tier;

        private Sound sound;

        Toys(String name, Tier tier, TexturedHead texture, Sound sound){
            this.name = name;
            this.tier = tier;
            this.texture = texture;
            this.sound = sound;
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
        ItemBuilder.setDisplayName(baseItem, tier.getColor() + "Stuffed " + name + " Toy");
        LoreUtils.addLoreLine(baseItem, tier.getColouredLore("Decoration", "Stuffed Toy"));
        return baseItem;
    }

    @Override
    public String getName() {
        return "Stuffed Toy Generator";
    }
}
