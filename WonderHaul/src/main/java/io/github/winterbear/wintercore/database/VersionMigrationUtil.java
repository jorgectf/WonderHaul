package io.github.winterbear.wintercore.database;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VersionMigrationUtil {

    private static final String WH_SCHEMA_TABLE = "WH_SCHEMA";

    private static final List<String> MIGRATION = Arrays.asList("1.0");


    public static void doMigration(){
        String currentVersion = getCurrentVersion();
        int startingStep = 0;
        if(currentVersion != null){
            if(!MIGRATION.contains(currentVersion)){
                ChatUtils.info("Database Storage Version " + currentVersion + " not known to this version of WonderHaul. Please check you have the latest version.");
                return;
            }
            if(MIGRATION.indexOf(currentVersion) + 1 == MIGRATION.size()){
                ChatUtils.info("Latest Database Storage version " + currentVersion + " already set up.");
                return;
            }
            startingStep = MIGRATION.indexOf(currentVersion) + 1;
        }
        ChatUtils.info("Database Migration In Progress: " + (currentVersion == null ? "First Time Setup" : (currentVersion + " to " + MIGRATION.get(MIGRATION.size() - 1))));
        List<String> migrationPlan = MIGRATION.subList(startingStep, MIGRATION.size());
        migrationPlan.forEach(VersionMigrationUtil::migrate);
        ChatUtils.info("Database Migration Complete");
    }

    private static String getCurrentVersion(){

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<String> schemaTable = session.createSQLQuery("SHOW TABLES LIKE '" + WH_SCHEMA_TABLE + "'").getResultList();

            if(!schemaTable.isEmpty()){
                List<String> version = session.createSQLQuery("SELECT VERSION FROM " + WH_SCHEMA_TABLE).getResultList();
                if(!version.isEmpty()){
                    return version.get(0);
                }
            }
        }
        return null;
    }

    private static void migrate(String version){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            getSQLScript(version).forEach(q -> session.createNativeQuery(q).executeUpdate());
            session.createNativeQuery("update WH_SCHEMA set version = '" + version + "'").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private static List<String> getSQLScript(String version){
        InputStream in = VersionMigrationUtil.class.getResourceAsStream("/db-migrate/mysql/" + version + ".sql");
        return new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
    }



}
