package cars_xml;

import java.util.HashSet;
import java.util.Set;

public class ModelX {

    private Integer id;
    private String name;
    private BrandX brand;
    Set<CarBody> bodies = new HashSet<>();
    Set<Engine> engines = new HashSet<>();
    Set<Gearbox> gerboxes = new HashSet<>();

    public ModelX() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandX getBrand() {
        return brand;
    }

    public void setBrand(BrandX brand) {
        this.brand = brand;
    }

    public Set<CarBody> getBodies() {
        return bodies;
    }

    public void setBodies(Set<CarBody> bodies) {
        this.bodies = bodies;
    }

    public Set<Engine> getEngines() {
        return engines;
    }

    public void setEngines(Set<Engine> engines) {
        this.engines = engines;
    }

    public Set<Gearbox> getGerboxes() {
        return gerboxes;
    }

    public void setGerboxes(Set<Gearbox> gerboxes) {
        this.gerboxes = gerboxes;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ModelX model = (ModelX) o;
        if (this.getId() == model.getId()) {
            return true;
        }
        return false;
    }
}
