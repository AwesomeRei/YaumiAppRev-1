package id.its.yaumirev_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Zachary on 4/24/2016.
 */
public class TargetAdapter extends BaseAdapter {
    private Context context;
    private String[] mobileValues;
    private EditText[] inputCollection;

    public TargetAdapter(Context context,String[] mobileValues){
        this.context = context;
        this.mobileValues = mobileValues;
        inputCollection = new EditText[getCount()];
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        System.out.println(mobileValues[1]+ " " + mobileValues[2]);
        System.out.println("FFFFFUUUUUUUUCCCCCCCCKKKKKKKK");
        if (convertView == null){
            gridView = new View(context);
//            gridView = inflater.inflate(R.layout.grid_detail,null);
            gridView = inflater.inflate(R.layout.grid_detail_input,null);
            System.out.println(mobileValues[1]);
            TextView textView = (TextView) gridView.findViewById(R.id.textViewInput);
            textView.setText(mobileValues[position]);

        }else {
            gridView = (View) convertView;
        }

        EditText editText = (EditText) gridView.findViewById(R.id.editTextInput);
        inputCollection[position] = editText;
        return gridView;
    }
}
