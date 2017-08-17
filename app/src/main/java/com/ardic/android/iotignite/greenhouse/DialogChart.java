package com.ardic.android.iotignite.greenhouse;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.controllers.ThingDataHistoryController;
import com.ardic.android.iotignite.greenhouse.listeners.ChartDataListener;
import com.ardic.android.iotignite.greenhouse.listeners.ThingDataHistoryAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;
import com.ardic.android.iotignite.lib.restclient.model.Thing;
import com.ardic.android.iotignite.lib.restclient.model.ThingData;
import com.ardic.android.iotignite.lib.restclient.model.ThingDataHistory;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yavuz.erzurumlu on 8/10/17.
 */

public class DialogChart extends Dialog {

    private static final String TAG = DialogChart.class.getSimpleName();
    private Thing dialogThing;
    private String deviceId;
    private LineDataSet dataSet;
    private ChartDataListener mChartDataListener = new ChartDataListener() {
        @Override
        public void onNewData(String thing, ThingData data) {
            if (!isChartCreating && dialogThing.getId().equals(thing)) {
                if (isShowing() && lineChart != null) {
                    dataSet.addEntry(new Entry(dataSet.getEntryCount() + 1, Float.valueOf(data.getData().get(0)), data));
                    dataSet.notifyDataSetChanged();
                    lineChart.getData().notifyDataChanged();
                    lineChart.notifyDataSetChanged();
                    lineChart.moveViewToAnimated(lineChart.getData().getEntryCount() - lineChart.getVisibleXRange(), lineChart.getData().getEntryCount(), YAxis.AxisDependency.RIGHT, 4000L);
                }
            }

        }
    };

    private LineChart lineChart;
    private Activity mActivity;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private boolean isChartCreating;
    //TODO :Add time values to xAxis.
    //private ChartXAxisDateValueFormatter xAxisDateValueFormatter;

    public DialogChart(@NonNull Activity activity, String deviceId, Thing mThing) {
        super(activity);
        this.dialogThing = mThing;
        this.deviceId = deviceId;
        this.mActivity = activity;

    }

    public ChartDataListener updateChart() {

        return mChartDataListener;
    }

    public void showDialog() {
        setContentView(R.layout.sensor_data_history_layout);
        lineChart = findViewById(R.id.sensor_data_history_chart);
        avLoadingIndicatorView = findViewById(R.id.progress);
        isChartCreating = true;
        setupChartAxis();
        avLoadingIndicatorView.show();


        new ThingDataHistoryController(mActivity, deviceId,
                Constants.GREENHOUSE_NODE, dialogThing.getId(),
                getStartOfDay(new Date(System.currentTimeMillis())).getTime(),
                new Date(System.currentTimeMillis()).getTime(), 10,
                new ThingDataHistoryAsyncTaskListener() {
                    @Override
                    public void onDataHistoryTaskComplete(final ThingDataHistory dataHistory) {

                        Log.i(TAG, "History Task Complete!!" + dataHistory.getList().size());
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                avLoadingIndicatorView.hide();
                                Log.i(TAG,"READY :" + dataHistory.getList().size());
                                List<Entry> entries = new ArrayList<>();

                                List<ThingData> dataList = dataHistory.getList();

                                /*     if (!dataList.isEmpty()) {
                                    lastData = dataList.get(dataList.size() - 1);
                                    xAxisDateValueFormatter.setDate(new Date(lastData.getCreateDate()));
                                }*/
                                /**
                                 * Incoming history list is reverse sorted. new to old. So add it to list inverted.
                                 */

                                int reverseIteration = dataList.size() - 1;
                                while (reverseIteration >= 0) {
                                    Log.i(TAG, "Adding entry : " + dataList.get(reverseIteration).getData().get(0));
                                    entries.add(new Entry((dataList.size() - reverseIteration), Float.valueOf(dataList.get(reverseIteration).getData().get(0))));
                                    reverseIteration--;
                                    // TODO : Commented lines are for adding time values to xAxis.
                                    // Log.i(TAG, "Adding entry Create Date: " + dataList.get(k).getCreateDate() + "\nDATE : " + new Date(dataList.get(k).getCreateDate()).toString());
                                    // long difference = dataList.get(k).getCreateDate() - lastData.getCreateDate();
                                    // Log.i(TAG, "DIFF : " + difference);
                                    // entries.add(new Entry(difference, Float.valueOf(dataList.get(k).getData().get(0))));
                                }

                                dataSet = new LineDataSet(entries, dialogThing.getId());

                                if (Constants.GREENHOUSE_TEMPERATURE_THINGTYPE.equals(dialogThing.getType())) {
                                    dataSet.setCircleColor(Color.RED);
                                    dataSet.setColor(Color.RED);
                                    lineChart.getDescription().setText("Temperature °C");

                                } else {
                                    dataSet.setCircleColor(Color.CYAN);
                                    dataSet.setColor(Color.CYAN);
                                    lineChart.getDescription().setText("Humidity %");
                                }

                                // add entries to dataset
                                dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                                dataSet.setCircleRadius(7f);
                                dataSet.setCircleHoleRadius(2f);
                                dataSet.setLineWidth(4f);
                                dataSet.setValueTextSize(10f);

                                dataSet.setMode(LineDataSet.Mode.LINEAR);
                                /// dataSet.set
                                LineData lineData = new LineData(dataSet);
                                lineChart.setData(lineData);
                                lineChart.setVisibleXRangeMaximum(6);
                                lineChart.getLegend().setTextSize(12f);
                                lineChart.getDescription().setTextSize(12f);

                                lineChart.moveViewToAnimated(lineData.getEntryCount() - lineChart.getVisibleXRange(), lineData.getEntryCount(), YAxis.AxisDependency.RIGHT, 10000L);
                                isChartCreating = false;
                            }
                        });
                    }
                }).execute();

        show();
    }


    private void setupChartAxis() {
        XAxis mXAxis = lineChart.getXAxis();
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxis.setAxisMinimum(-1f);
        mXAxis.setDrawAxisLine(true);
        mXAxis.setDrawLabels(true);
        mXAxis.setXOffset(0);
        // TODO : Commented lines are for adding time values to xAxis.
        //xAxisDateValueFormatter = new ChartXAxisDateValueFormatter();
        // mXAxis.setValueFormatter(xAxisDateValueFormatter);
        mXAxis.setEnabled(true);

        // don't need y axis on right.
        lineChart.getAxisRight().setEnabled(false);

        // left y axis.
        YAxis mYAxis = lineChart.getAxis(YAxis.AxisDependency.LEFT);
        mYAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        mYAxis.setDrawLabels(true);
        mYAxis.setDrawAxisLine(false);
        mYAxis.setAxisMinimum(-15f);
        mYAxis.setAxisMaximum(100f);
        mYAxis.setEnabled(true);
    }


    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public String getThing() {
        return dialogThing.getId();
    }

    public class ChartXAxisDateValueFormatter implements IAxisValueFormatter {

        private final String TAG = ChartXAxisDateValueFormatter.class.getSimpleName();
        private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm");
        private Date mDate;
        private String formattedDate;
        private Date refDate;
        private int count = 0;


        @Override
        public String getFormattedValue(float value, AxisBase axis) {

            //    Log.i(TAG, "VALUE : " + value);
            long lValue = (long) value;


            count++;
            Log.i(TAG, "COUNT : " + count);

            //    Log.i(TAG, "REF DATE : " + refDate.toString());
            long dateTimeLong = refDate.getTime() + lValue;
            //   Log.i(TAG, "LONG VALUE : " + lValue);
            mDate = new Date(dateTimeLong);
            //    Log.i(TAG, "DATE : " + mDate.toString());
            formattedDate = mSimpleDateFormat.format(mDate);
            return formattedDate;


        }

        public void setDate(Date d) {
            this.refDate = d;
        }
    }
}
