package cars_xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Engine {

    private int id;
    private String description;
    private ModelX model;
    private Set<Car> cars = new HashSet<>();
    private int year;

    public Engine() {
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
        Engine car = (Engine) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
