package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class PrefixDictionary {

    private Map<Tier, List<String>> dictionary;

    public PrefixDictionary(YamlConfiguration config){
        Map<Tier, List<String>> tiers = new HashMap<>();
        for(Tier tier : Tier.values()){
            tiers.put(tier, config.getStringList(tier.name().toLowerCase()));
            if(tiers.get(tier).isEmpty()){
                ChatUtils.warn("Tier " + tier + " has no configured prefixes.");
            }
        }
        this.dictionary = tiers;
    }

    public Optional<String> getPrefix(Tier tier){
        if(dictionary.containsKey(tier) && !dictionary.get(tier).isEmpty()) {
            return Optional.of(RandomUtils.getRandomElementOf(dictionary.get(tier)));
        } else {
            return Optional.empty();
        }
    }

}
