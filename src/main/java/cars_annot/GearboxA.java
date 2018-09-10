package cars_annot;

//import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gearbox")
public class GearboxA {

    private int id;
    private String description;
    private Set<CarA> carList = new HashSet<>();

    public GearboxA() {
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gearbox")
    public Set<CarA> getCarList() {
        return carList;
    }

    public void setCarList(Set<CarA> carList) {
        this.carList = carList;
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
        GearboxA car = (GearboxA) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
