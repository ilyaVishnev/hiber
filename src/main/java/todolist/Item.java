package todolist;

import java.sql.Timestamp;
import java.util.Date;

public class Item {
    private int id;
    private String desc;
    private Date created;
    private boolean done;

    public Item() {
        created = new Date();
        done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getId());
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

}
