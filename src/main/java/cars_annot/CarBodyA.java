package cars_annot;

//import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carbody")
public class CarBodyA {

    private int id;
    private String description;
    private Model model;
    private List<CarA> cars=new ArrayList<>();
    private int year;

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

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_m",referencedColumnName = "id")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @OneToMany(mappedBy = "carBodyA")
    public List<CarA> getCars() {
        return cars;
    }

    public void setCars(List<CarA> cars) {
        this.cars = cars;
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
