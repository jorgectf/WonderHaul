package io.github.winterbear.wintercore.wonderhaul.equipment.generators;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class BadInitialisationException extends Exception {

    public BadInitialisationException(String message){
        super(message);
    }

    public BadInitialisationException(String message, Exception cause){
        super(message, cause);
    }
}
