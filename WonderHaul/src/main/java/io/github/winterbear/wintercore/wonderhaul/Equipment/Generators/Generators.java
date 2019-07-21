package io.github.winterbear.wintercore.wonderhaul.Equipment.Generators;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Generators {

    public static Generator JUNK = new JunkGenerator();

    public static Generator ORDINARY = new OrdinaryGenerator();

    public static Generator UNUSUAL = new UnusualGenerator();

    public static Generator RARE = new RareGenerator();

    public static Generator fromName(String name){
        if(name.equalsIgnoreCase("junk")){
            return JUNK;
        } else if (name.equalsIgnoreCase("ordinary")){
            return ORDINARY;
        } else if (name.equalsIgnoreCase("unusual")){
            return UNUSUAL;
        } else  if (name.equalsIgnoreCase("rare")){
            return RARE;
        }
        return null;
    }




}
