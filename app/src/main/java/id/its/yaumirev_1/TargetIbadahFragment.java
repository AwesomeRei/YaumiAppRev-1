package id.its.yaumirev_1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private static String[] cek;
    private static String[] satuan;
    private TargetAdapter profile;

    private LinearLayout llEnterTarget;
    private List<EditText> editTextList = new ArrayList<EditText>();
    private List<EditText> editTextList2 = new ArrayList<EditText>();
    private List<TextView> textViewList = new ArrayList<TextView>();
    private AsyncTask test;
    int _intMyLineCount;
    private ProgressBar mProgress;
    private ScrollView x;
    private ReadFileJSON myJSON;
    private Ibadah ib  ;
    private Amal amal;
    private List<Amal> amalan;
    final Gson gson= new Gson();

    String url = "https://api.myjson.com/bins/4uglk";
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
        x =(ScrollView) rootView.findViewById(R.id.targetScroll);
        x.setVisibility(View.GONE);
        mProgress = (ProgressBar)rootView.findViewById(R.id.progressBar);
        test = new FetchData().execute("Ok");

        displayAddTarget();
        checkButtonClick();
        return rootView;
    }

    private Response.Listener<Ibadah> createMyRequestErrorListener() {
        return new Response.Listener<Ibadah>(){

            @Override
            public void onResponse(Ibadah response) {

            }
        };
    }

    private class FetchData extends AsyncTask<String,Integer,Double>{

        @Override
        protected Double doInBackground(String... params) {
            String url = "https://api.myjson.com/bins/3x1tk";
//            String url = "https://10.151.33.33:8080/yaumiWS/rest/yaumi/target";
            GsonRequest jsObjRequest = new GsonRequest(url,Ibadah.class,null,
                    new Response.Listener<Ibadah>() {
                        @Override
                        public void onResponse(Ibadah response) {

                            cek = new String[response.getAmals().size()];
                            satuan = new String[response.getAmals().size()];
                            for (int i= 0;i<response.getAmals().size();i++){
                                Amal amalItem = response.getAmals().get(i);
                                cek[i] = amalItem.getNamaamal();
                                satuan[i] =amalItem.getSatuan();
                                System.out.println("Response: "+ cek[i]);
                            }
                            mProgress.setVisibility(View.GONE);
                            x.setVisibility(View.VISIBLE);
                            profile = new TargetAdapter(getContext(),cek);
                            listView.setAdapter(profile);
                            listView.setVisibility(View.VISIBLE);


                        }
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(error != null) Log.e("MainActivity", error.getMessage());
                        }
                    });

            MySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
            Log.d("status async", test.getStatus().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
            if(cek!=null)
            {
                profile = new TargetAdapter(getContext(),cek);
                listView.setAdapter(profile);
                listView.setVisibility(View.VISIBLE);
                Log.d("masuk", "null");
            }
            Log.d("masuk", "gak null");
            Log.d("status async2-->", test.getStatus().toString());
        }
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

    private TextView textView(int _intID){
        TextView txtviewAll=new TextView(getActivity());
        txtviewAll.setId(_intID);
        txtviewAll.setText("Step ");
        txtviewAll.setWidth(80);

        txtviewAll.setTextColor(Color.RED);
        txtviewAll.setTypeface(Typeface.DEFAULT_BOLD);
        //textviewList.add(txtviewAll);
        return txtviewAll;
    }
    private LinearLayout linearlayout(int _intID){
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
            String url = null;

                for (int i=0;i<cek.length;i++){
                    Log.d("Nama: ",profile.getItemPosition(i));
                    Log.d("Inputan nya: ",profile.getItemInput(i));
                    Ibadah inputku = new Ibadah();
                    List<Amal> amalanku = new ArrayList<Amal>();
                    final Amal punyaku = new Amal();
                    punyaku.setIdamal(String.valueOf(i+1));
                    punyaku.setNamaamal(profile.getItemPosition(i));
                    punyaku.setValue(profile.getItemInput(i));
                    punyaku.setSatuan(satuan[i]);
                    amalanku.add(punyaku);
                    inputku.setAmal(amalanku);
                    // Ready to implement dont know right or wrong
                    StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("idamal",punyaku.getIdamal());
                            params.put("namaamal",punyaku.getNamaamal());
                            params.put("value",punyaku.getValue());
                            params.put("satuan",punyaku.getSatuan());
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("Content-Type","application/x-www-form-urlencoded");
                            return params;
                        }
                    };
                    MySingleton.getInstance(getActivity()).addToRequestQueue(sr);

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

//                long count = dbHandler.getDataCount();
//                Log.i("Jumlah Data", String.valueOf(count));
//                Toast.makeText(getActivity(),
//                        (int) count, Toast.LENGTH_LONG).show();

            }
        });

    }

}
