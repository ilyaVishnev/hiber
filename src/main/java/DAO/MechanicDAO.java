package DAO;

import cars_annot.CarA;
import cars_annot.CarBodyA;
import cars_annot.EngineA;
import cars_annot.GearboxA;
import cars_xml.Car;
import cars_xml.CarBody;
import cars_xml.Engine;
import cars_xml.Gearbox;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import todolist.Item;

import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class MechanicDAO {

    private Logger logger = Logger.getLogger(MechanicDAO.class.getName());

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

    public static void main(String[] args) {

        MechanicDAO mechanicDAO = new MechanicDAO();
        // xml testing
        /*
        Car bmw1 = new Car();
        bmw1.setDescription("BMW1");
        Car bmw2 = new Car();
        bmw2.setDescription("BMW2");
        CarBody bodyBMW = new CarBody();
        bodyBMW.setDescription("forBMW");
        mechanicDAO.saveEntyty(bodyBMW);
        Engine engineBMW = new Engine();
        engineBMW.setDescription("engBMW");
        mechanicDAO.saveEntyty(engineBMW);
        Gearbox gearboxBMW = new Gearbox();
        gearboxBMW.setDescription("gearBMW");
        mechanicDAO.saveEntyty(gearboxBMW);
        bodyBMW.getCarList().add(bmw1);
        bodyBMW.getCarList().add(bmw2);
        engineBMW.getCarList().add(bmw1);
        engineBMW.getCarList().add(bmw2);
        gearboxBMW.getCarList().add(bmw1);
        gearboxBMW.getCarList().add(bmw2);
        bmw1.setCarBody(bodyBMW);
        bmw2.setCarBody(bodyBMW);
        bmw1.setEngine(engineBMW);
        bmw2.setEngine(engineBMW);
        bmw1.setGearbox(gearboxBMW);
        bmw2.setGearbox(gearboxBMW);
        mechanicDAO.saveEntyty(bmw1);
        mechanicDAO.saveEntyty(bmw2);
        mechanicDAO.saveEntyty(gearboxBMW);
        mechanicDAO.saveEntyty(engineBMW);
        mechanicDAO.saveEntyty(bodyBMW);*/
//annotations testing
        CarA bmw1 = new CarA();
        bmw1.setDescription("BMW1");
        CarA bmw2 = new CarA();
        bmw2.setDescription("BMW2");
        CarBodyA bodyBMW = new CarBodyA();
        bodyBMW.setDescription("forBMW");
        EngineA engineBMW = new EngineA();
        engineBMW.setDescription("engBMW");
        GearboxA gearboxBMW = new GearboxA();
        gearboxBMW.setDescription("gearBMW");
        bodyBMW.getCarList().add(bmw1);
        bodyBMW.getCarList().add(bmw2);
        engineBMW.getCarList().add(bmw1);
        engineBMW.getCarList().add(bmw2);
        gearboxBMW.getCarList().add(bmw1);
        gearboxBMW.getCarList().add(bmw2);
        bmw1.setCarBody(bodyBMW);
        bmw2.setCarBody(bodyBMW);
        bmw1.setEngine(engineBMW);
        bmw2.setEngine(engineBMW);
        bmw1.setGearbox(gearboxBMW);
        bmw2.setGearbox(gearboxBMW);
        mechanicDAO.saveEntyty(bmw1);
        mechanicDAO.saveEntyty(bmw2);
        mechanicDAO.saveEntyty(gearboxBMW);
        mechanicDAO.saveEntyty(engineBMW);
        mechanicDAO.saveEntyty(bodyBMW);
        // System.out.println(bmw1);
        bmw1.setDescription("Mercedes");
        mechanicDAO.updateCar(bmw1);
        // System.out.println(bmw1);
        mechanicDAO.deleteCar(bmw1);
    }
}
