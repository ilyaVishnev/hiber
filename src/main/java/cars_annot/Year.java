package cars_annot;

import javax.persistence.*;

@Entity
@Table(name = "years")
public class Year {

    private int year;
    private int id;

    public Year() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
