package cars_annot;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class CarA {
    private int id;
    private String description;
    private GearboxA gearbox;
    private CarBodyA carBody;
    private EngineA engine;

    public CarA() {
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "name")
    public String getDescription() {
        return description;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cb", referencedColumnName = "id")
    public CarBodyA getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBodyA carBody) {
        this.carBody = carBody;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_en", referencedColumnName = "id")
    public EngineA getEngine() {
        return engine;
    }

    public void setEngine(EngineA engine) {
        this.engine = engine;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_box", referencedColumnName = "id")
    public GearboxA getGearbox() {
        return gearbox;
    }

    public void setGearbox(GearboxA gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public String toString() {
        return this.description + " - " + this.carBody + " - " + this.gearbox + " - " + this.engine;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CarA car = (CarA) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
