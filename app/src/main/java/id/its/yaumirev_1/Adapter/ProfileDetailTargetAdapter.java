package id.its.yaumirev_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import id.its.yaumirev_1.R;

/**
 * Created by Zachary on 4/10/2016.
 */
public class ProfileDetailTargetAdapter extends BaseAdapter {
    private Context context;
    private String[] mobileValues;
    private String[] targetPercent = null;
    private int[] color;
    private String[] satuan;
//    private EditText[] inputCollection;

//    public ProfileDetailTargetAdapter(Context context, String[] mobileValues){
//        this.context = context;
//        this.mobileValues = mobileValues;
//        inputCollection = new EditText[getCount()];
//    }

    public ProfileDetailTargetAdapter(Context context, String[] mobileValues, String[] targetPercent, int[] color,String[] satuan ) {
        this.context = context;
        this.mobileValues = mobileValues;
        this.targetPercent = targetPercent;
        this.color= color;
        this.satuan = satuan;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
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

        if (convertView == null){
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_detail,null);
//            gridView = inflater.inflate(R.layout.grid_detail_input,null);

//            TextView textView = (TextView) gridView.findViewById(R.id.textViewInput);
//            textView.setText(mobileValues[position]);

            TextView textView = (TextView) gridView.findViewById(R.id.targetDescription);
            textView.setText(mobileValues[position]);

            View recView =(View)gridView.findViewById(R.id.myRectangleView);
            recView.setBackgroundColor(color[position]);
//            textView.setTextColor(color[position]);


            TextView textView2 = (TextView) gridView.findViewById(R.id.targetPercentage);
            textView2.setText(targetPercent[position]);

            TextView textView3 = (TextView) gridView.findViewById(R.id.textView3);
            textView3.setText(satuan[position]);

        }else {
            gridView = (View) convertView;
        }

//        EditText editText = (EditText) gridView.findViewById(R.id.editTextInput);
//        inputCollection[position] = editText;
        return gridView;
    }
//    public String getItemInput(int position){
////        System.out.println(inputCollection[position]);
////        String mobile = mobileValues[position];
//        return inputCollection[position].getText().toString();
//    }

    public String getItemPosition(int position){
        return mobileValues[position];
    }
}

