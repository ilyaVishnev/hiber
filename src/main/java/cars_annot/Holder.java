package cars_annot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "holders")
public class Holder {
    private int id;
    private String login;
    private String password;
    private List<CarA> cars = new ArrayList<>();

    public Holder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holder")
    public List<CarA> getCars() {
        return cars;
    }

    public void setCars(List<CarA> cars) {
        this.cars = cars;
    }
}
