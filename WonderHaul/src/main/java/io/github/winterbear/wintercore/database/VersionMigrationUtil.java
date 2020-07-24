package io.github.winterbear.wintercore.database;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

public class VersionMigrationUtil {

    private static final List<String> MIGRATION = Arrays.asList("1.0");


    public void doMigration(){
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
        List<String> migrationPlan = MIGRATION.subList(startingStep, MIGRATION.size());
        migrationPlan.forEach(this::migrate);
    }

    private String getCurrentVersion(){


        return "";
    }

    private void migrate(String version){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("BEGIN " + getSQLScript(version) + " END;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private String getSQLScript(String version){

        return "";
    }



}
