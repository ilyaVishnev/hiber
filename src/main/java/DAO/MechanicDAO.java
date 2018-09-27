package DAO;

import cars_annot.*;
import cars_xml.Car;
import cars_xml.CarBody;
import cars_xml.Engine;
import cars_xml.Gearbox;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import todolist.Item;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class MechanicDAO {
    private static final MechanicDAO mechanicDAO = new MechanicDAO();
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static MechanicDAO getMechanicDAO() {
        return mechanicDAO;
    }

    private Logger logger = Logger.getLogger(MechanicDAO.class.getName());

    public <T> T func(final Function<Session, T> command) {
        T t = null;
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            t = command.apply(session);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            logger.info(ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
        return t;
    }

    public Holder isCredential(String login, String password) {
        return this.func(session -> {
            Criteria userCriteria = session.createCriteria(Holder.class);
            userCriteria.add(Restrictions.eq("login", login));
            userCriteria.add(Restrictions.eq("password", password));
            Holder holder = (Holder) userCriteria.uniqueResult();
            return holder;
        });
    }

    public <T> void saveEntyty(T t) {
        this.func(session -> {
            session.save(t);
            return t;
        });
    }

    public <T> void deleteCar(T t) {
        this.func(session -> {
            session.delete(t);
            return null;
        });
    }

    public <T> void updateCar(T t) {
        this.func(session -> {
            session.saveOrUpdate(t);
            return t;
        });
    }
}
