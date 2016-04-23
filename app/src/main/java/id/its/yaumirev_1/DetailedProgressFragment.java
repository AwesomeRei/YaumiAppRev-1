package id.its.yaumirev_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedProgressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedProgressFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView gridView;

    //    private OnFragmentInteractionListener mListener;
    private static final String[] DESCRIPTION = new String[]{
            "Tahajud","Tahajud2","Tahajud3","Tahajud4","Tahajud5","Tahajud6"
    };

    private static final String[] DESCIPTION_VALUES = new String[]{
            "10%","20%","30%","40%","50%","60%"
    };

    public DetailedProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailedProgressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailedProgressFragment newInstance(int position) {
        DetailedProgressFragment fragment = new DetailedProgressFragment();
//        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_detailed_progress, container, false);
        gridView =(GridView)rootView.findViewById(R.id.gridTarget);
        gridView.setAdapter(new ProfileDetailTargetAdapter(this.getContext(),DESCRIPTION,DESCIPTION_VALUES));

        return rootView;
    }

}
