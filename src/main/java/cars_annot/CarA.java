package cars_annot;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class CarA {
    private int id;
    private int price;
    private Holder holder;
    private CarBodyA carBodyA;
    private EngineA engineA;
    private GearboxA gearboxA;
    private String description;
    private Boolean status;
    private String photo;
    private int year;

    public CarA() {
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "id")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_holder", referencedColumnName = "id")
    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cb", referencedColumnName = "id")
    public CarBodyA getCarBodyA() {
        return carBodyA;
    }

    public void setCarBodyA(CarBodyA carBodyA) {
        this.carBodyA = carBodyA;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_en", referencedColumnName = "id")
    public EngineA getEngineA() {
        return engineA;
    }

    public void setEngineA(EngineA engineA) {
        this.engineA = engineA;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_box", referencedColumnName = "id")
    public GearboxA getGearboxA() {
        return gearboxA;
    }

    public void setGearboxA(GearboxA gearboxA) {
        this.gearboxA = gearboxA;
    }


    @Override
    public String toString() {
        return this.description + " " + this.getHolder().getId() + " " +
                this.getGearboxA().getId() + " " + this.getEngineA().getId() + " " + this.getCarBodyA().getId();
    }

    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
