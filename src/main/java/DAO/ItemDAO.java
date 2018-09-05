package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import todolist.Item;

import java.util.logging.Logger;

public class ItemDAO {

    private static final ItemDAO itemDAO = new ItemDAO();

    public static ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void addItem(String desc) {
        Item item = new Item();
        item.setDesc(desc);
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public Item getItemById(String id) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Item item = (Item) session.get(Item.class, Integer.parseInt(id));
        session.close();
        sessionFactory.close();
        return item;
    }
}
