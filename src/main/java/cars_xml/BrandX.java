package cars_xml;


import java.util.ArrayList;
import java.util.List;

public class BrandX {

    public BrandX() {
    }

    private int id;
    private String name;
    private List<ModelX> models = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelX> getModels() {
        return models;
    }

    public void setModels(List<ModelX> models) {
        this.models = models;
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
        BrandX brand = (BrandX) o;
        if (this.getId() == brand.getId()) {
            return true;
        }
        return false;
    }
}
