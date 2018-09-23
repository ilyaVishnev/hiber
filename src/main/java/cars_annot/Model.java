package cars_annot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "model")
public class Model {

    private Integer id;
    private String name;
    private Brand brand;
    Set<CarBodyA> bodies = new HashSet<>();
    Set<EngineA> engines = new HashSet<>();
    Set<GearboxA> gerboxes = new HashSet<>();

    public Model() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    public Set<CarBodyA> getBodies() {
        return bodies;
    }

    public void setBodies(Set<CarBodyA> bodies) {
        this.bodies = bodies;
    }

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "model")
    public Set<EngineA> getEngines() {
        return engines;
    }

    public void setEngines(Set<EngineA> engines) {
        this.engines = engines;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    public Set<GearboxA> getGerboxes() {
        return gerboxes;
    }

    public void setGerboxes(Set<GearboxA> gerboxes) {
        this.gerboxes = gerboxes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_br", referencedColumnName = "id")

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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
        Model model = (Model) o;
        if (this.getId() == model.getId()) {
            return true;
        }
        return false;
    }
}
