import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.Annotations.SpigotPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by WinterBear on 30/05/2020.
 */
@SpigotPlugin(version = "1.0.0")
public class Handy extends JavaPlugin {

    @Override
    public void onEnable() {
        ChatUtils.info("Loading Handy");
        loadCommands();
    }

    private void loadCommands() {
        Set<Method> methods = getCommands();
        for (Method method : methods){
            try {
                CommandRegistry.register(this, (CommandWrapper) method.invoke(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Set<Method> getCommands() {
        return new Reflections("io.github.winterbear.handy", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(Command.class);
    }

}
