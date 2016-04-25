package id.its.yaumirev_1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HaveYouDoneItFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HaveYouDoneItFragment extends Fragment {

//    MyCustomAdapter dataAdapter;
    private View rootView;
    private GridView gridView;
    private Button button;
    private EditText input;
    private String[] column;
    private ProfileTargetAdapter profile;
    private MyDBHandler dbHandler;
    private ReadFileJSON myJSON;

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

        dbHandler = new MyDBHandler(getActivity(),null,null,1);
        column = dbHandler.getData();
        column = Arrays.copyOfRange(column,1,column.length);

//        myJSON = new ReadFileJSON(getActivity(),"jsonDataFetchedAmalan");

        profile = new ProfileTargetAdapter(this.getContext(),column);
        gridView.setAdapter(profile);

//        displayListView();

        checkButtonClick();

        return rootView;
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
//                DESCRIPTION.length
//                input = (EditText) rootView.findViewById(R.id.editTextInput);
//                System.out.println(input.getText());

//                StringBuffer responseText = new StringBuffer();
//                responseText.append("The following were selected...\n");
//
//                ArrayList<Ibadah> ibadahList = dataAdapter.ibadahList;
//                for(int i=0;i<ibadahList.size();i++){
//                    Ibadah country = ibadahList.get(i);
//                    if(country.isSelected()){
//                        responseText.append("\n" + country.getName());
//                    }
//                }

                long count = dbHandler.getDataCount();
                Log.i("Jumlah Data", String.valueOf(count));
//                Toast.makeText(getActivity(),
//                        (int) count, Toast.LENGTH_LONG).show();

            }
        });

    }








}
