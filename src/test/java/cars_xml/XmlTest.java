package cars_xml;

import DAO.MechanicDAO;
import cars_annot.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class XmlTest {

    MechanicDAO mechanicDAO;

    public XmlTest() {
        mechanicDAO = MechanicDAO.getMechanicDAO();
    }

    @Test
    public void createCar() throws IOException {
        Engine engine = mechanicDAO.func(session -> {
            return session.get(Engine.class, 8);
        });
        Gearbox gearbox = mechanicDAO.func(session -> {
            return session.get(Gearbox.class, 1);
        });
        CarBody carBody = mechanicDAO.func(session -> {
            return session.get(CarBody.class, 1);
        });
        HolderX holder = mechanicDAO.func(session -> {
            return session.get(HolderX.class, 1);
        });
        Car car = new Car();
        car.setHolder(holder);
        car.setGearbox(gearbox);
        car.setCarbody(carBody);
        car.setEngine(engine);
        car.setDescription("mycar");
        mechanicDAO.saveEntyty(car);
        Car example = mechanicDAO.func(session -> {
            List<Car> cars = session.createQuery("from Car ").list();
            return cars.get(0);
        });
        Assert.assertThat(car.toString(), is(example.toString()));
    }

    @Test
    public void updateCar() throws IOException {
        Car car = mechanicDAO.func(session -> {
            List<Car> cars = session.createQuery("from Car ").list();
            return cars.get(0);
        });
        Car example = new Car();
        example.setId(car.getId());
        example.setDescription("mysecondCar");
        mechanicDAO.updateCar(example);
        car = mechanicDAO.func(session -> {
            List<Car> cars = session.createQuery("from Car ").list();
            return cars.get(0);
        });
        Assert.assertThat(car.getDescription(), is(example.getDescription()));
    }

    @Test
    public void deleteCar() throws IOException {
        Car car = mechanicDAO.func(session -> {
            List<Car> cars = session.createQuery("from Car ").list();
            return cars.get(0);
        });
        mechanicDAO.deleteCar(car);
        car = mechanicDAO.func(session -> {
            Car carA = new Car();
            List<Car> cars = session.createQuery("from Car ").list();
            if (cars.isEmpty()) {
                carA.setDescription("null");
                return carA;
            }
            carA.setDescription("notNull");
            return carA;
        });
        Assert.assertThat(car.getDescription(), is("null"));
    }
}
