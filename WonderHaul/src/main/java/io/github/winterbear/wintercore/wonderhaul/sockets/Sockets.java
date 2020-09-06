package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplicationListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 06/09/2020.
 */
public class Sockets {

    private static SocketApplicationListener listener;

    public static void registerListeners(JavaPlugin plugin){
        listener = new SocketApplicationListener(plugin);
    }

}
