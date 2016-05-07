package id.its.yaumirev_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zachary on 4/10/2016.
 */
public class ProfileTargetAdapter extends BaseAdapter {
    private Context context;
    private String[] mobileValues;
    private String[] targetPercent = null;
    private EditText[] inputCollection;
    private EditText no0;
    private ArrayList<EditText> input;

    public ProfileTargetAdapter(Context context,String[] mobileValues){
        this.context = context;
        this.mobileValues = mobileValues;
        inputCollection = new EditText[getCount()];
    }

    public ProfileTargetAdapter(Context context, String[] mobileValues, String[] targetPercent) {
        this.context = context;
        this.mobileValues = mobileValues;
        this.targetPercent = targetPercent;
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
        EditText editText =null;
        input= new ArrayList<EditText>();
        View gridView;

//        System.out.println(mobileValues[1]+ " " + mobileValues[2]);
        if (convertView == null){
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_detail_input,null);

            TextView textView = (TextView) gridView.findViewById(R.id.textViewInput);
            textView.setText(mobileValues[position]);
            editText = (EditText) gridView.findViewById(R.id.editTextInput);
            no0 = editText;
//            inputCollection[position] = editText;
            input.add(editText);
//            targetPercent[po] = editText.getText();
//            editText = (EditText) gridView.findViewById(R.id.editTextInput);
//            inputCollection[position] = editText;
        }else {
            gridView = (View) convertView;
        }

        editText = (EditText) gridView.findViewById(R.id.editTextInput);
//        Log.d("Posisi: ", String.valueOf(position));
        inputCollection[position] = editText;
        input.add(editText);
        System.out.println(">>>>> "+inputCollection[0].getText());
        return gridView;
    }
    public String getItemInput(int position){
        if (position==0){
            Log.d("Input in position "+position,input.get(position).getText().toString());
            return input.get(position).getText().toString();
        }
        Log.d("Nom: ", String.valueOf(position));
//        }
        Log.d("Size: ", String.valueOf(input.size()));
//        Log.d("Isi: ",input.get(position).getText().toString());
//        System.out.println(inputCollection[position]);
//        String mobile = mobileValues[position];
        return inputCollection[position].getText().toString();

    }
    public int getItematZero(){
        return input.size();
    }

    public String getItemPosition(int position){
     return inputCollection[position].getText().toString();
//        return mobileValues[position];
    }
}