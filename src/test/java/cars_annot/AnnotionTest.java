package cars_annot;

import DAO.MechanicDAO;
import avito.CreateController;
import com.sun.deploy.net.HttpRequest;
import org.junit.Assert.*;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AnnotionTest {

    MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Test
    public void createCar() throws IOException {
        EngineA engineA = mechanicDAO.func(session -> {
            return session.get(EngineA.class, 8);
        });
        GearboxA gearboxA = mechanicDAO.func(session -> {
            return session.get(GearboxA.class, 1);
        });
        CarBodyA carBodyA = mechanicDAO.func(session -> {
            return session.get(CarBodyA.class, 1);
        });
        Holder holder = mechanicDAO.func(session -> {
            return session.get(Holder.class, 1);
        });
        CarA carA = new CarA();
        carA.setHolder(holder);
        carA.setGearboxA(gearboxA);
        carA.setCarBodyA(carBodyA);
        carA.setEngineA(engineA);
        carA.setDescription("mycar");
        mechanicDAO.saveEntyty(carA);
        CarA example = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
        Assert.assertThat(carA.toString(), is(example.toString()));
    }

    @Test
    public void updateCar() throws IOException {
        CarA car = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
        CarA example = new CarA();
        example.setId(car.getId());
        example.setDescription("mysecondCar");
        mechanicDAO.updateCar(example);
        car = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
        Assert.assertThat(car.getDescription(), is(example.getDescription()));
    }

    @Test
    public void deleteCar() throws IOException {
        CarA car = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
        mechanicDAO.deleteCar(car);
        car = mechanicDAO.func(session -> {
            CarA carA = new CarA();
            List<CarA> cars = session.createQuery("from CarA ").list();
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
