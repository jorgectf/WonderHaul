package io.github.winterbear.wintercore.database;

import io.github.winterbear.wintercore.PluginConfig;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.PersistedInventory;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.PersistedItem;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static Optional<PluginConfig> configHolder = Optional.empty();

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null && configHolder.isPresent()) {

            PluginConfig config = configHolder.get();
            try {

                Configuration configuration = new Configuration();

                // Hibernate settings

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://" + config.getDatabaseHost() + ":" + config.getDatabasePort() + "/"+ config.getDatabaseName() +"?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
                settings.put(Environment.USER, config.getDatabaseUsername());
                settings.put(Environment.PASS, config.getDatabasePassword());
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, config.getShowSQL().toString());
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                getDatabaseEntities().forEach(configuration::addAnnotatedClass);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return sessionFactory;

    }

    public static void setConfig(PluginConfig config){
        configHolder = Optional.of(config);
    }

    private static List<Class<?>> getDatabaseEntities(){
        return Arrays.asList(BlockMetadata.class, PersistedInventory.class, PersistedItem.class);


    }

}
