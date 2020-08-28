package io.github.winterbear.wintercore;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginConfig {

    private String configVersion;

    private String databaseHost;

    private String databasePort;

    private String databaseName;

    private String databaseUsername;

    private String databasePassword;

    private Boolean showSQL;

    public PluginConfig(JavaPlugin plugin){
        ConfigLoader.registerCustomConfig(plugin, "config", "config.yml");
        YamlConfiguration baseConfig = ConfigLoader.getConfig("config");
        configVersion = baseConfig.getString("config-version");
        databaseHost = baseConfig.getString("database.host");
        databasePort = baseConfig.getString("database.port");
        databaseName = baseConfig.getString("database.database");
        databaseUsername = baseConfig.getString("database.username");
        databasePassword = baseConfig.getString("database.password");
        showSQL = baseConfig.getBoolean("database.show-sql");
    }

    public String getConfigVersion() {
        return configVersion;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public Boolean getShowSQL() {
        return showSQL;
    }
}
