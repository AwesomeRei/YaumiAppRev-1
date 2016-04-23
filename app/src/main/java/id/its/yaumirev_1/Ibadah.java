package id.its.yaumirev_1;

/**
 * Created by Zachary on 4/19/2016.
 */
public class Ibadah {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name = null;
    boolean selected = false;


    public Ibadah(String nama, boolean selected) {
        super();
        this.name = nama;
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }




}
