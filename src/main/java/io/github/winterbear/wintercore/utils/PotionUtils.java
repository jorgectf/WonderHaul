package io.github.winterbear.wintercore.utils;

import com.google.common.collect.Sets;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class PotionUtils {

    private static Set<PotionEffectType> NEGATIVE_EFFECTS = Sets.newHashSet(
            PotionEffectType.SLOW,
            PotionEffectType.SLOW_DIGGING,
            PotionEffectType.HARM,
            PotionEffectType.CONFUSION,
            PotionEffectType.BLINDNESS,
            PotionEffectType.WEAKNESS,
            PotionEffectType.HUNGER,
            PotionEffectType.POISON,
            PotionEffectType.WITHER,
            PotionEffectType.UNLUCK,
            PotionEffectType.BAD_OMEN,
            PotionEffectType.LEVITATION);


    public static boolean isNegative(@Nullable PotionEffect prior, @Nullable PotionEffect effect){
        if(effect == null){
            return false;
        }
        if(prior != null && prior.getType().equals(effect.getType())){
            if(prior.getAmplifier() > effect.getAmplifier()){
                return false;
            } else if (prior.getDuration() > effect.getDuration()){
                return false;
            }
        }
        return NEGATIVE_EFFECTS.contains(effect.getType());
    }

}
