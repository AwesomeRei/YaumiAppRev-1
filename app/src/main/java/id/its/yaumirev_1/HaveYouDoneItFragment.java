package id.its.yaumirev_1;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HaveYouDoneItFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HaveYouDoneItFragment extends Fragment {

//    MyCustomAdapter dataAdapter;
    private View rootView;
    private GridView gridView;
    private RecyclerView recyclerView;
    private Button button;
    private EditText input;
    private String[] column;
    private String[] satuan;
    private ProfileTargetAdapter profile;
    private MyDBHandler dbHandler;
    private ReadFileJSON myJSON;
    private AsyncTask test;
    private GridLayoutManager ilayout;
    private ProgressBar mProgress;
    private RecyclerViewAdapter rcAdapter;
    private LinearLayout linearLayout;
    public HaveYouDoneItFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HaveYouDoneItFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HaveYouDoneItFragment newInstance(String param1, String param2) {
        HaveYouDoneItFragment fragment = new HaveYouDoneItFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_have_you_done_it, container, false);
        gridView =(GridView)rootView.findViewById(R.id.gridTarget);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler);
        linearLayout = (LinearLayout)rootView.findViewById(R.id.linearLayoutH);
        linearLayout.setVisibility(View.GONE);
        mProgress = (ProgressBar) rootView.findViewById(R.id.progressBar);
        dbHandler = new MyDBHandler(getActivity(),null,null,1);
//        column = dbHandler.getData();
//        column = Arrays.copyOfRange(column,1,column.length);\
        ilayout = new GridLayoutManager(getContext(),2);
        test = new FetchData().execute("Ok");

//        myJSON = new ReadFileJSON(getActivity(),"jsonDataFetchedAmalan");

//        profile = new ProfileTargetAdapter(this.getContext(),column);
//        gridView.setAdapter(profile);

//        displayListView();

        checkButtonClick();

        return rootView;
    }
    private class FetchData extends AsyncTask<String,Integer,Double> {

        @Override
        protected Double doInBackground(String... params) {

            String url = "https://api.myjson.com/bins/3x1tk";
            GsonRequest jsObjRequest = new GsonRequest(url,Ibadah.class,null,
                    new Response.Listener<Ibadah>() {
                        @Override
                        public void onResponse(Ibadah response) {

                            column = new String[response.getAmals().size()];
                            for (int i= 0;i<response.getAmals().size();i++){
                                Amal amalItem = response.getAmals().get(i);
                                column[i] = amalItem.getNamaamal();
                                System.out.println("Response: "+ column[i]);
                            }
                            mProgress.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.VISIBLE);

//                            profile = new ProfileTargetAdapter(getContext(),column);
//                            gridView.setAdapter(profile);
//                            gridView.setVisibility(View.VISIBLE);
                            recyclerView.setLayoutManager(ilayout);
                            rcAdapter = new RecyclerViewAdapter(getContext(),column);
                            recyclerView.setAdapter(rcAdapter);


                        }
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(error != null) Log.e("MainActivity", error.getMessage());
                        }
                    });

            MySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
//            Log.d("status async", test.getStatus().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
        }
    }
    private void checkButtonClick() {
        Button myButton = (Button) rootView.findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {
        String[] datamu;
            @Override
            public void onClick(View v) {
//                String url = null;

                Log.d("Count ", String.valueOf(rcAdapter.getItemCount()));
                datamu = new String[rcAdapter.getItemCount()];
                datamu = rcAdapter.getAll();

//                Log.d("All: ",datamu[0] );
//                Log.d("All: ",datamu[1] );

                for (int i=0;i<rcAdapter.getItemCount();i++){
                    Log.d("Value",rcAdapter.getItem(i));
                }
//                for (int i=0;i<column.length;i++){
//                    Log.d("Nama ", String.valueOf(i));
//                    profile.getItematZero();
//                    Log.d("Jumlah: ", String.valueOf(profile.getCount()));
//                    Log.d("Nama Input ",rcAdapter.getItem(i));
//                    Log.d("Value Input ",rcAdapter.getItemCount());
//                    System.out.println(column[i]);
//                    System.out.println(profile.getItemInput(i));
//                    Ibadah inputku = new Ibadah();
//                    List<Amal> amalanku = new ArrayList<Amal>();
//                    final Amal punyaku = new Amal();
//                    punyaku.setIdamal(String.valueOf(i+1));
//                    punyaku.setNamaamal(profile.getItemPosition(i));
//                    punyaku.setValue(profile.getItemInput(i));
//                    punyaku.setSatuan(satuan[i]);
//                    amalanku.add(punyaku);
//                    inputku.setAmal(amalanku);
                    // Ready to implement dont know right or wrong
//                    StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//
//                        @Override
//                        public void onResponse(String response) {
//
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    }){
//                        @Override
//                        protected Map<String, String> getParams() {
//                            Map<String,String> params = new HashMap<String, String>();
//                            params.put("idamal",punyaku.getIdamal());
//                            params.put("namaamal",punyaku.getNamaamal());
//                            params.put("value",punyaku.getValue());
//                            params.put("satuan",punyaku.getSatuan());
//                            return params;
//                        }
//
//                        @Override
//                        public Map<String, String> getHeaders() throws AuthFailureError {
//                            Map<String,String> params = new HashMap<String, String>();
//                            params.put("Content-Type","application/x-www-form-urlencoded");
//                            return params;
//                        }
//                    };
//                    MySingleton.getInstance(getActivity()).addToRequestQueue(sr);
                }

//            }
        });

    }








}
