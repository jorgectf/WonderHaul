package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class RandomTieredDictionary {

    private Map<Tier, List<String>> dictionary;

    public RandomTieredDictionary(String dictionaryType, YamlConfiguration config){
        Map<Tier, List<String>> tiers = new HashMap<>();
        for(Tier tier : Tier.values()){
            tiers.put(tier, config.getStringList(tier.getName().toLowerCase()));
            if(tiers.get(tier).isEmpty()){
                ChatUtils.warn("Tier " + tier.getName() + " has no configured " + dictionaryType + ".");
            }
        }
        this.dictionary = tiers;
    }

    public Optional<String> getRandomValue(Tier tier){
        if(dictionary.containsKey(tier) && !dictionary.get(tier).isEmpty()) {
            return Optional.of(RandomUtils.getRandomElementOf(dictionary.get(tier)));
        } else {
            return Optional.empty();
        }
    }

}
