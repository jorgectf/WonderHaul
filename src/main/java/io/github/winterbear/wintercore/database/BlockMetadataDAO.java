package io.github.winterbear.wintercore.database;

import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BlockMetadataDAO {



    public void save(List<BlockMetadata> metadata) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            metadata.stream()
                    //.peek(b -> ChatUtils.info(b.getLocationReference() + ": " + System.identityHashCode(b)))
                    .forEach(session::saveOrUpdate);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }

    public void delete(List<BlockMetadata> metadata){

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            metadata.forEach(session::delete);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }


    }



    public List<BlockMetadata> getMetadata(){
        List<BlockMetadata> blockMetadata;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            blockMetadata = session.createQuery("from BlockMetadata", BlockMetadata.class).list();
        }

        //blockMetadata.stream().forEach(b -> ChatUtils.info(b.getLocationReference() + ": " + System.identityHashCode(b)));

        return blockMetadata;

    }



}
