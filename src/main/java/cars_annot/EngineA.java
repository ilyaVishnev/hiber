package cars_annot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "engine")
public class EngineA {

    private int id;
    private String description;
    private Model model;
    private List<CarA> cars=new ArrayList<>();
    private int year;

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

    @ManyToOne(fetch = FetchType.EAGER)
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

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "engineA")
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
        EngineA car = (EngineA) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
