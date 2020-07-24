package io.github.winterbear.wintercore.database;

import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BlockMetadataDAO {



    public void save(List<BlockMetadata> metadata) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            metadata.forEach(session::saveOrUpdate);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(List<String> references){

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from BlockMetadata where location in (:references)")
                    .setParameter("references", references)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }



    public List<BlockMetadata> getMetadata(){
        List<BlockMetadata> blockMetadata = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            blockMetadata = session.createQuery("from BlockMetadata", BlockMetadata.class).list();
        }

        return blockMetadata;

    }



}
