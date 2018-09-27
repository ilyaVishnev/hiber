package cars_annot;

import DAO.MechanicDAO;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class IntegrationTest {

    MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Test
    public void createCar() throws IOException {
        CarA carA = saveCar();
        CarA example = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
        Assert.assertThat(carA.toString(), is(example.toString()));
    }

    @Test
    public void updateCar() throws IOException {
        CarA carA = saveCar();
        CarA example = new CarA();
        example.setId(carA.getId());
        example.setDescription("mysecondCar");
        mechanicDAO.updateCar(example);
        CarA car = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
        Assert.assertThat(car.getDescription(), is(example.getDescription()));
    }

    @Test
    public void deleteCar() throws IOException {
        CarA carA = saveCar();
        CarA carA1 = new CarA();
        carA1.setId(carA.getId());
        mechanicDAO.deleteCar(carA1);
        Integer size = mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.size();
        });
        Assert.assertThat(size, is(0));
    }

    @Test
    public void getCarById() {
        CarA carA = saveCar();
        CarA example = mechanicDAO.func(session -> {
            return session.get(CarA.class, carA.getId());
        });
        Assert.assertThat(carA.toString(), is(example.toString()));
    }

    @Test
    public void checkHolder() {
        Holder holder = new Holder();
        holder.setLogin("myLogin");
        holder.setPassword("myPassword");
        mechanicDAO.saveEntyty(holder);
        Holder holderTwo = new Holder();
        holderTwo.setLogin("SecondLogin");
        holderTwo.setPassword("SecondPassword");
        mechanicDAO.saveEntyty(holderTwo);
        holder = mechanicDAO.isCredential("myLogin", "myPassword");
        holderTwo = mechanicDAO.isCredential("SecondLogin", "SecondPassword");
        Assert.assertNotEquals(holder.toString(), holderTwo.toString());

    }

    public CarA saveCar() {
        EngineA engineA = new EngineA();
        engineA.setDescription("myEngine");
        GearboxA gearboxA = new GearboxA();
        gearboxA.setDescription("myGearbox");
        CarBodyA carBodyA = new CarBodyA();
        carBodyA.setDescription("myCarbody");
        Holder holder = new Holder();
        holder.setLogin("me");
        CarA carA = new CarA();
        carA.setHolder(holder);
        carA.setGearboxA(gearboxA);
        carA.setCarBodyA(carBodyA);
        carA.setEngineA(engineA);
        carA.setDescription("mycar");
        mechanicDAO.saveEntyty(carA);
        return mechanicDAO.func(session -> {
            List<CarA> cars = session.createQuery("from CarA ").list();
            return cars.get(0);
        });
    }
}
