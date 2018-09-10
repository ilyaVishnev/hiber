package cars_annot;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "engine")
public class EngineA {

    private int id;
    private String description;
    private Set<CarA> carList = new HashSet<>();

    public EngineA() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engine")
    public Set<CarA> getCarList() {
        return carList;
    }

    public void setCarList(Set<CarA> carList) {
        this.carList = carList;
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
        EngineA car = (EngineA) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
