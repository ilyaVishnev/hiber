package cars_xml;

//import org.hibernate.mapping.Set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gearbox {

    private int id;
    private String description;
    private ModelX model;
    private Set<Car> cars = new HashSet<>();
    private int year;

    public Gearbox() {
    }

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

    public ModelX getModel() {
        return model;
    }

    public void setModel(ModelX model) {
        this.model = model;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Gearbox car = (Gearbox) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
