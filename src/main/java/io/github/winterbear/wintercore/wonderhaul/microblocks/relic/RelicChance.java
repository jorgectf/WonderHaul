package io.github.winterbear.wintercore.wonderhaul.microblocks.relic;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;

/**
 * Created by WinterBear on 28/07/2020.
 */
public class RelicChance {

    private double identifyChance;

    private double upgradeChance;

    private double doubleUpgradeChance = 0.0f;

    public RelicChance(double identifyChance, double upgradeChance) {
        this.identifyChance = identifyChance;
        this.upgradeChance = upgradeChance;
    }

    public RelicChance(double identifyChance, double upgradeChance, double doubleUpgradeChance) {
        this.identifyChance = identifyChance;
        this.upgradeChance = upgradeChance;
        this.doubleUpgradeChance = doubleUpgradeChance;
    }

    public Chance getIdentifyChance() {
        return new Chance(identifyChance);
    }

    public Chance getUpgradeChance() {
        return new Chance(upgradeChance);
    }

    public Chance getDoubleUpgradeChance() {
        return new Chance(doubleUpgradeChance);
    }
}
