package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import todolist.Item;

import java.util.List;
import java.util.function.*;

import java.util.logging.Logger;

public class ItemDAO {

    private static final ItemDAO itemDAO = new ItemDAO();
    private Logger logger = Logger.getLogger(ItemDAO.class.getName());

    public static ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void addItem(String desc) {
        this.func(session -> {
            Item item = new Item();
            item.setDesc(desc);
            session.save(item);
            return item;
        });
    }

    public Item getItemById(String id) {
        return this.func(session -> {
            final Query query = session.createQuery("from Item as item where item.id=:id");
            query.setParameter("id", Integer.parseInt(id));
            List<Item> items = query.list();
            return items.get(0);
        });
    }

    public <T> T func(final Function<Session, T> command) {
        T t = null;
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = command.apply(session);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            logger.info(ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
            sessionFactory.close();
        }
        return t;
    }
}
