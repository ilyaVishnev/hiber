package cars_xml;

public class Car {
    private int id;
    private int price;
    private HolderX holder;
    private CarBody carbody;
    private Engine engine;
    private Gearbox gearbox;
    private String description;
    private Boolean status;
    private String photo;
    private int year;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HolderX getHolder() {
        return holder;
    }

    public void setHolder(HolderX holder) {
        this.holder = holder;
    }

    public CarBody getCarbody() {
        return carbody;
    }

    public void setCarbody(CarBody carbody) {
        this.carbody = carbody;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.description + " " + this.getHolder().getId() + " " +
                this.getGearbox().getId() + " " + this.getEngine().getId() + " " + this.getCarbody().getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Car car = (Car) o;
        if (this.getId() == car.getId()) {
            return true;
        }
        return false;
    }
}
