package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations;

import io.github.winterbear.wintercore.utils.ColorLoreMode;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.TexturedHead;
import io.github.winterbear.wintercore.utils.TexturedHeads;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 17/04/2021.
 */
public enum CarvedStone {

    A(TexturedHeads.CARVED_STONE_A, "Stone Carving A"),
    B(TexturedHeads.CARVED_STONE_B, "Stone Carving B"),
    C(TexturedHeads.CARVED_STONE_C, "Stone Carving C"),
    D(TexturedHeads.CARVED_STONE_D, "Stone Carving D"),
    E(TexturedHeads.CARVED_STONE_E, "Stone Carving E"),
    F(TexturedHeads.CARVED_STONE_F, "Stone Carving F"),
    G(TexturedHeads.CARVED_STONE_G, "Stone Carving G"),
    H(TexturedHeads.CARVED_STONE_H, "Stone Carving H"),
    I(TexturedHeads.CARVED_STONE_I, "Stone Carving I"),
    J(TexturedHeads.CARVED_STONE_J, "Stone Carving J"),
    K(TexturedHeads.CARVED_STONE_K, "Stone Carving K"),
    L(TexturedHeads.CARVED_STONE_L, "Stone Carving L"),
    M(TexturedHeads.CARVED_STONE_M, "Stone Carving M"),
    N(TexturedHeads.CARVED_STONE_N, "Stone Carving N"),
    O(TexturedHeads.CARVED_STONE_O, "Stone Carving O"),
    P(TexturedHeads.CARVED_STONE_P, "Stone Carving P"),
    Q(TexturedHeads.CARVED_STONE_Q, "Stone Carving Q"),
    R(TexturedHeads.CARVED_STONE_R, "Stone Carving R"),
    S(TexturedHeads.CARVED_STONE_S, "Stone Carving S"),
    T(TexturedHeads.CARVED_STONE_T, "Stone Carving T"),
    U(TexturedHeads.CARVED_STONE_U, "Stone Carving U"),
    V(TexturedHeads.CARVED_STONE_V, "Stone Carving V"),
    W(TexturedHeads.CARVED_STONE_W, "Stone Carving W"),
    X(TexturedHeads.CARVED_STONE_X, "Stone Carving X"),
    Y(TexturedHeads.CARVED_STONE_Y, "Stone Carving Y"),
    Z(TexturedHeads.CARVED_STONE_Z, "Stone Carving Z"),
    ZERO(TexturedHeads.CARVED_STONE_0, "Stone Carving 0"),
    ONE(TexturedHeads.CARVED_STONE_1, "Stone Carving 1"),
    TWO(TexturedHeads.CARVED_STONE_2, "Stone Carving 2"),
    THREE(TexturedHeads.CARVED_STONE_3, "Stone Carving 3"),
    FOUR(TexturedHeads.CARVED_STONE_4, "Stone Carving 4"),
    FIVE(TexturedHeads.CARVED_STONE_5, "Stone Carving 5"),
    SIX(TexturedHeads.CARVED_STONE_6, "Stone Carving 6"),
    SEVEN(TexturedHeads.CARVED_STONE_7, "Stone Carving 7"),
    EIGHT(TexturedHeads.CARVED_STONE_8, "Stone Carving 8"),
    NINE(TexturedHeads.CARVED_STONE_9, "Stone Carving 9"),
    RIGHT(TexturedHeads.CARVED_STONE_RIGHT, "Stone Carving Right Arrow"),
    LEFT(TexturedHeads.CARVED_STONE_LEFT, "Stone Carving Left Arrow"),
    UP(TexturedHeads.CARVED_STONE_UP, "Stone Carving Up Arrow"),
    DOWN(TexturedHeads.CARVED_STONE_DOWN, "Stone Carving Down Arrow"),
    UP_LEFT(TexturedHeads.CARVED_STONE_UP_LEFT, "Stone Carving Up Left Arrow"),
    UP_RIGHT(TexturedHeads.CARVED_STONE_UP_RIGHT, "Stone Carving Up Right Arrow"),
    DOWN_LEFT(TexturedHeads.CARVED_STONE_DOWN_LEFT, "Stone Carving Down Left Arrow"),
    DOWN_RIGHT(TexturedHeads.CARVED_STONE_DOWN_RIGHT, "Stone Carving Down Right Arrow"),
    EXCLAMATION_MARK(TexturedHeads.CARVED_STONE_EXCLAMATION_MARK, "Stone Carving !"),
    QUESTION_MARK(TexturedHeads.CARVED_STONE_QUESTION_MARK, "Stone Carving ?"),
    PLUS(TexturedHeads.CARVED_STONE_PLUS, "Stone Carving +"),
    HASH(TexturedHeads.CARVED_STONE_HASH, "Stone Carving #"),
    LESS_THAN(TexturedHeads.CARVED_STONE_LESS_THAN, "Stone Carving <"),
    GREATER_THAN(TexturedHeads.CARVED_STONE_GREATER_THAN, "Stone Carving >");

    private TexturedHead texture;

    private String displayName;

    CarvedStone(TexturedHead texture, String displayName){
        this.texture = texture;
        this.displayName = displayName;
    }

    public TexturedHead getTexture() {
        return texture;
    }

    public ItemStack generate() {

        return ItemBuilder.newMicroblock("Stone Carving",
                ItemCategory.DECORATION,
                ChatColor.of("#3a3a52"),
                ColorLoreMode.SAME,
                texture)
                .withDescription("A decorative stone carving.")
                .withDisplayName(displayName)
                .build();

    }

    public String getDisplayName() {
        return displayName;
    }
}
