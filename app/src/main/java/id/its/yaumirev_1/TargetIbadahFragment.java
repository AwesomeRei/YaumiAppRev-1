package id.its.yaumirev_1;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TargetIbadahFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TargetIbadahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TargetIbadahFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View rootView;
    private ListView listView;
    private Button button;
    private EditText input;
    private String[] column;
    private TargetAdapter profile;
    private MyDBHandler dbHandler;
    private LinearLayout llEnterTarget;
    private List<EditText> editTextList = new ArrayList<EditText>();
    private List<EditText> editTextList2 = new ArrayList<EditText>();
    private List<TextView> textViewList = new ArrayList<TextView>();
    int _intMyLineCount;

    private ReadFileJSON myJSON;
    private Ibadah ib;
    private Amal amal;
    private List<Amal> amalan;
    final Gson gson= new Gson();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TargetIbadahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TargetIbadahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TargetIbadahFragment newInstance(String param1, String param2) {
        TargetIbadahFragment fragment = new TargetIbadahFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_target_ibadah, container, false);
        listView =(ListView) rootView.findViewById(R.id.listView1);

        String url = "http://search.twitter.com/search.json?q=javacodegeeks";


        try {
            AssetManager assetManager = getActivity().getAssets();
            InputStream ims = assetManager.open("jsonDataFetchedUntukNampilkanTarget.txt");
            Reader reader = new InputStreamReader(ims);
            System.out.println(reader);
            ib = gson.fromJson(reader,Ibadah.class);
            System.out.println(ib);
//            for (int i = 0;i<ib.getAmals().size();i++){
//                column[i] = ib.getAmals().get(i).getNamaamal();
//            }
//            amalan = readJsonStream(ims);

        }catch (IOException e){
            e.printStackTrace();
        }
//        System.out.println("Ini File JSONNYA "+ loadJSONFromAsset());
//        ib = readJsonStream(ims)
//        System.out.print("Convered data"+ ib);

//        amalan = ib.getAmals();

        profile = new TargetAdapter(this.getContext(),column);
//        System.out.println(profile);
//        listView.setAdapter(profile);
//        System.out.println(listView);
//        String[] values = new String[] { "Message1", "Message2", "Message3" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, column);
//        setListAdapter(profile);
        listView.setAdapter(profile);

        displayAddTarget();
        checkButtonClick();
        return rootView;
    }
    private InputStream retrieveStream(String url) {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(url);

        try {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();

        }
        catch (IOException e) {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }

        return null;

    }
    public String loadJSONFromAsset(){
        String json = null;
//        System.out.println(context.getAssets());
        try {
            InputStream is = getActivity().getAssets().open("jsonDataFetchedUntukNampilkanTarget.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    private void displayAddTarget(){
        llEnterTarget = (LinearLayout) rootView.findViewById(R.id.linearView);
        button = (Button)rootView.findViewById(R.id.buttonAdd);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(30, 20, 30, 0);
                llEnterTarget.addView(linearlayout(_intMyLineCount),layoutParams);
                _intMyLineCount++;
            }
        });


    }

    private EditText editText(int _intID) {
        EditText editText = new EditText(getActivity());
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        lparams.setMargins(4,0,4,0);
        editText.setId(_intID);
        editText.setHint("Tulis Disini");
//        editText.setWidth(300);
        editText.setBackgroundColor(Color.WHITE);
        editText.setLayoutParams(lparams);
        editTextList.add(editText);

        return editText;
    }
    private EditText editText2(int _intID) {
        EditText editText = new EditText(getActivity());
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,2);
        lparams.setMargins(4,0,4,0);
        editText.setId(_intID);
        editText.setHint("Target apa ");
//        editText.setWidth(400);
        editText.setBackgroundColor(Color.WHITE);
        editTextList2.add(editText);

        return editText;
    }

    private TextView textView(int _intID)
    {
        TextView txtviewAll=new TextView(getActivity());
        txtviewAll.setId(_intID);
        txtviewAll.setText("Step ");
        txtviewAll.setWidth(80);

        txtviewAll.setTextColor(Color.RED);
        txtviewAll.setTypeface(Typeface.DEFAULT_BOLD);
        //textviewList.add(txtviewAll);
        return txtviewAll;
    }
    private LinearLayout linearlayout(int _intID)
    {
        LinearLayout LLMain=new LinearLayout(getActivity());
        LLMain.setId(_intID);

//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(0, 8,0 , 0);
//        LLMain.addView(textView(_intID));
        LLMain.addView(editText2(_intID));
        LLMain.addView(editText(_intID));

        LLMain.setOrientation(LinearLayout.HORIZONTAL);
        //linearlayoutList.add(LLMain);
        return LLMain;

    }

    private void checkButtonClick() {
        Button myButton = (Button) rootView.findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                for (int i=0;i<column.length;i++){
//                    System.out.println("++++++"+profile.getItemInput(i));
                    dbHandler.addSomething(dbHandler.addNewRow(),column[i],profile.getItemInput(i));
//                    System.out.println(column[i]);
//                    System.out.println(profile.getItemInput(i));
                }

                for (EditText editText : editTextList) {
                    Log.i("Edit Text",editText.getText().toString());
//                    sb.append(editText.getText().toString());
//                    sb.append("\n");
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + sb);

                }
                for (EditText editText : editTextList2) {

                    Log.i("Edit Text",editText.getText().toString());
//                    sm.append(editText.getText().toString());
//                    sm.append("\n");
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + sm);

                }

                long count = dbHandler.getDataCount();
                Log.i("Jumlah Data", String.valueOf(count));
//                Toast.makeText(getActivity(),
//                        (int) count, Toast.LENGTH_LONG).show();

            }
        });

    }

}
