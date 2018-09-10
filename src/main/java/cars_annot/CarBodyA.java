package cars_annot;

//import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carbody")
public class CarBodyA {

    private int id;
    private String description;
    private Set<CarA> carList = new HashSet<>();

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

    public void setCarList(Set<CarA> carList) {
        this.carList = carList;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carBody")
    public Set<CarA> getCarList() {
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
        CarBodyA car = (CarBodyA) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
