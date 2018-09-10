package cars_xml;

//import org.hibernate.mapping.Set;

import java.util.*;

public class CarBody {

    private int id;
    private String description;
    private Set<Car> carList = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCarList(Set<Car> carList) {
        this.carList = carList;
    }

    public Set<Car> getCarList() {
        return carList;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CarBody car = (CarBody) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
