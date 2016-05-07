package id.its.yaumirev_1;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
/**
 * A placeholder fragment containing a simple view.
 */
public class ChartProgressFragment extends Fragment {

    private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    float[][] randomNumbersTab2 = new float[maxNumberOfLines][numberOfPoints];
    int[] color = {
            Color.BLUE,Color.RED,Color.YELLOW,Color.BLACK,Color.GREEN
    };

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private AsyncTask test;
    private Tanggal tgl;
    private Amal amal;
    private List<PointValue> values;
    private List<PointValue> values2;
    private List<PointValue> values3;
    private ArrayList<List<PointValue>> manyValues;
    private Line[] line;
    private List<Line> lines;
    private int max;


    public ChartProgressFragment() {
    }

    public static ChartProgressFragment newInstance() {
        ChartProgressFragment fragment = new ChartProgressFragment();
        Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chart_progress, container, false);

        chart = (LineChartView) rootView.findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
//        values = new ArrayList<PointValue>();
        manyValues = new ArrayList<List<PointValue>>();
        test = new FetchData().execute("Ok");
        // Generate some random values.

        generateData();

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        resetViewport(max);

        return rootView;
    }

    private class TwoDimensionalArrayList<T> extends ArrayList<ArrayList>{

    }
    private class FetchData extends AsyncTask<String,Integer,Double> {

        @Override
        protected Double doInBackground(String... params) {
            String url = "https://api.myjson.com/bins/22bni";
            GsonRequest jsObjRequest = new GsonRequest(url,IbadahHarian.class,null,
                    new Response.Listener<IbadahHarian>() {
                        @Override
                        public void onResponse(IbadahHarian response) {
                            Log.d("Response: ",response.toString());
                            lines = new ArrayList<Line>();
                            values = new ArrayList<PointValue>();
                            values2 = new ArrayList<PointValue>();
                            values3 = new ArrayList<PointValue>();
                            max = response.getTgl().size();
                            for (int i=0;i<response.getTgl().size();i++){
                                tgl = response.getTgl().get(i);
                                Log.d("Tanggal: ", tgl.getIdtgl());
//                                manyValues.add();
                                line = new Line[tgl.getAmal().size()];
                                for (int j=0;j<tgl.getAmal().size();j++){
                                    amal = tgl.getAmal().get(j);
                                    if (amal.getNamaamal().equals("puasa")){
                                        values.add(new PointValue(i,Float.parseFloat(amal.getValue())));
                                        Log.d("Ini: puasa ==",amal.getNamaamal());
//                                        manyValues.add(0,values);
                                    }else if (amal.getNamaamal().equals("Sholat Dhuha")){
                                        values2.add(new PointValue(i,Float.parseFloat(amal.getValue())));
//                                        manyValues.add(1,values);
                                        Log.d("Ini: X ==",amal.getNamaamal());
                                    }else {
                                        values3.add(new PointValue(i,Float.parseFloat(amal.getValue())));
                                        Log.d("Ini: tahajjud == ",amal.getNamaamal());
//                                        manyValues.add(2,values);
                                    }
//                                    values.add(new PointValue(i,Float.parseFloat(amal.getValue())));
//                                    line[j] = new Line().setColor(color[j]).setCubic(true);
//                                    Log.d("Line ke- ", String.valueOf(j));
//                                    Log.d("Value Amal: ", amal.getNamaamal());
//                                    Log.d("Value Amal: ", amal.getValue());
//                                    lines.add(line[j]);
                                }
//                                Amal amal = tgl.getAmal();
                            }

                            line[0] = new Line(values).setColor(color[0]).setCubic(true);
                            line[1] = new Line(values2).setColor(color[1]).setCubic(true);
                            line[2] = new Line(values3).setColor(color[2]).setCubic(true);
                            lines.add(line[0]);
                            lines.add(line[1]);
                            lines.add(line[2]);
                            generateData();

//                            column = new String[response.getAmals().size()];
//                            for (int i= 0;i<response.getAmals().size();i++){
//                                Amal amalItem = response.getAmals().get(i);
//                                column[i] = amalItem.getNamaamal();
//                                System.out.println("Response: "+ column[i]);
//                            }
//                            mProgress.setVisibility(View.GONE);
//                            profile = new ProfileTargetAdapter(getContext(),column);
//                            gridView.setAdapter(profile);
//                            gridView.setVisibility(View.VISIBLE);


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
//            if(cek!=null)
//            {
////                profile = new TargetAdapter(getContext(),cek);
////                listView.setAdapter(profile);
////                listView.setVisibility(View.VISIBLE);
////                Log.d("masuk", "null");
//            }
//            Log.d("masuk", "gak null");
//            Log.d("status async2-->", test.getStatus().toString());
        }
    }

    private void resetViewport(int max ) {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 10;
        v.left = 0;
        v.right = max + 2;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private void reset() {
        numberOfLines = 1;

        hasAxes = true;
        hasAxesNames = true;
        hasLines = true;
        hasPoints = true;
        shape = ValueShape.CIRCLE;
        isFilled = false;
        hasLabels = false;
        isCubic = false;
        hasLabelForSelected = false;
        pointsHaveDifferentColor = false;

        chart.setValueSelectionEnabled(hasLabelForSelected);
        resetViewport(max);
    }

    private void generateData() {


        LineChartData data = new LineChartData();
        data.setLines(lines);

        if (hasAxes) {
            Axis axisX = new Axis().setHasLines(true).setTextColor(ChartUtils.COLOR_RED);
            Axis axisY = new Axis().setHasLines(true).setTextColor(ChartUtils.COLOR_RED);
            if (hasAxesNames) {
                axisX.setName("Tanggal");
                axisY.setName("Jumlah Ibadah yang dilakukan");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
//
//        LineChartView chart = new LineChartView(context);
        chart.setLineChartData(data);

    }

    private void addLineToData() {
        if (data.getLines().size() >= maxNumberOfLines) {
            Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ++numberOfLines;
        }

        generateData();
    }

    private void toggleLines() {
        hasLines = !hasLines;

        generateData();
    }

    private void togglePoints() {
        hasPoints = !hasPoints;

        generateData();
    }

    private void toggleCubic() {
        isCubic = !isCubic;

        generateData();

        if (isCubic) {
            // It is good idea to manually set a little higher max viewport for cubic lines because sometimes line
            // go above or below max/min. To do that use Viewport.inest() method and pass negative value as dy
            // parameter or just set top and bottom values manually.
            // In this example I know that Y values are within (0,100) range so I set viewport height range manually
            // to (-5, 105).
            // To make this works during animations you should use Chart.setViewportCalculationEnabled(false) before
            // modifying viewport.
            // Remember to set viewport after you call setLineChartData().
            final Viewport v = new Viewport(chart.getMaximumViewport());
            v.bottom = -5;
            v.top = 105;
            // You have to set max and current viewports separately.
            chart.setMaximumViewport(v);
            // I changing current viewport with animation in this case.
            chart.setCurrentViewportWithAnimation(v);
        } else {
            // If not cubic restore viewport to (0,100) range.
            final Viewport v = new Viewport(chart.getMaximumViewport());
            v.bottom = 0;
            v.top = 100;

            // You have to set max and current viewports separately.
            // In this case, if I want animation I have to set current viewport first and use animation listener.
            // Max viewport will be set in onAnimationFinished method.
            chart.setViewportAnimationListener(new ChartAnimationListener() {

                @Override
                public void onAnimationStarted() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationFinished() {
                    // Set max viewpirt and remove listener.
                    chart.setMaximumViewport(v);
                    chart.setViewportAnimationListener(null);

                }
            });
            // Set current viewpirt with animation;
            chart.setCurrentViewportWithAnimation(v);
        }

    }

    private void toggleFilled() {
        isFilled = !isFilled;

        generateData();
    }

    private void togglePointColor() {
        pointsHaveDifferentColor = !pointsHaveDifferentColor;

        generateData();
    }

    private void setCircles() {
        shape = ValueShape.CIRCLE;

        generateData();
    }

    private void setSquares() {
        shape = ValueShape.SQUARE;

        generateData();
    }

    private void setDiamonds() {
        shape = ValueShape.DIAMOND;

        generateData();
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;

        if (hasLabels) {
            hasLabelForSelected = false;
            chart.setValueSelectionEnabled(hasLabelForSelected);
        }

        generateData();
    }

    private void toggleLabelForSelected() {
        hasLabelForSelected = !hasLabelForSelected;

        chart.setValueSelectionEnabled(hasLabelForSelected);

        if (hasLabelForSelected) {
            hasLabels = false;
        }

        generateData();
    }

    private void toggleAxes() {
        hasAxes = !hasAxes;

        generateData();
    }

    private void toggleAxesNames() {
        hasAxesNames = !hasAxesNames;

        generateData();
    }

    /**
     * To animate values you have to change targets values and then call {@link Chart#startDataAnimation()}
     * method(don't confuse with View.animate()). If you operate on data that was set before you don't have to call
     * {@link LineChartView#setLineChartData(LineChartData)} again.
     */
    private void prepareDataAnimation() {
        for (Line line : data.getLines()) {
            for (PointValue value : line.getValues()) {
                // Here I modify target only for Y values but it is OK to modify X targets as well.
                value.setTarget(value.getX(), (float) Math.random() * 100);
            }
        }
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}