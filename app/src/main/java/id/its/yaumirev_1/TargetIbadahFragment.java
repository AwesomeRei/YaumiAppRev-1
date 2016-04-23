package id.its.yaumirev_1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TargetIbadahFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TargetIbadahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TargetIbadahFragment extends ListFragment {
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
//        listView =(ListView) rootView.findViewById(R.id.list);

        dbHandler = new MyDBHandler(getActivity(),null,null,1);
        column = dbHandler.getData();
        column = Arrays.copyOfRange(column,1,column.length);
//        System.out.println(column[1]);
        profile = new TargetAdapter(this.getContext(),column);
//        System.out.println(profile);
//        listView.setAdapter(profile);
//        System.out.println(listView);
//        String[] values = new String[] { "Message1", "Message2", "Message3" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, column);
        setListAdapter(profile);
//        listView.setAdapter(profile);
        return rootView;
    }

}
