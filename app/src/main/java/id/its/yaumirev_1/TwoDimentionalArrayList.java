package id.its.yaumirev_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary on 5/14/2016.
 */
public class TwoDimentionalArrayList<T> extends ArrayList<ArrayList<T>> {
    public void addToInnerArray(int index, T element){
        while (index >= this.size()) {
            this.add(new ArrayList<T>());
        }
        this.get(index).add(element);
    }
    public void addToInnerArray(int index, int index2, T element) {
        while (index >= this.size()) {
            this.add(new ArrayList<T>());
        }

        ArrayList<T> inner = this.get(index);
        while (index2 >= inner.size()) {
            inner.add(null);
        }

        inner.set(index2, element);
    }




}
