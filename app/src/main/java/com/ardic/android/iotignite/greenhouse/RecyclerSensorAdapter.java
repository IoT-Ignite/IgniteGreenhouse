package com.ardic.android.iotignite.greenhouse;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */


public class RecyclerSensorAdapter extends RecyclerView.Adapter<RecyclerSensorAdapter.ViewHolder> {

    private static final String TAG = RecyclerSensorAdapter.class.getSimpleName();
    List<SensorViewModel> sensorList;
    CustomCardViewClickListener listenerSensorCardViewClick;

    public RecyclerSensorAdapter(List<SensorViewModel> sensorList, CustomCardViewClickListener listenerSensorCardViewClick) {

        this.sensorList = sensorList;
        this.listenerSensorCardViewClick = listenerSensorCardViewClick;
    }

    @Override
    public RecyclerSensorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_sensor_dashboard_cardview_item, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerSensorCardViewClick.onItemClick(v, view_holder.getAdapterPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.e(TAG, "TxtSensor : " + holder.txtSensorId);
        Log.e(TAG, "TxtValue : " + holder.txtSensorValue);
        Log.e(TAG, "List : " + sensorList);
        holder.txtSensorId.setText(sensorList.get(position).getSensorId());
        holder.txtSensorValue.setText(sensorList.get(position).getSensorValue());

        //      if (sensorList.get(position).isSensorOnline()) {
        //          holder.imgSensorStatus.setImageResource(R.drawable.raspberry_pi_online);
        //     } else {
        //         holder.imgSensorStatus.setImageResource(R.drawable.offline_raspberrypi);
        //     }
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSensorId;
        public TextView txtSensorValue;
        public ImageView imgSensorStatus;
        public CardView cardViewSensor;


        public ViewHolder(View view) {
            super(view);

            cardViewSensor = view.findViewById(R.id.content_sensor_dashboard_card_view_item_card_view);
            txtSensorId = view.findViewById(R.id.content_sensor_dashboard_card_view_item_txt_sensor_thing_id);
            txtSensorValue = view.findViewById(R.id.content_sensor_dashboard_card_view_item_txt_sensor_value);
            //   imgSensorStatus = view.findViewById(R.id.content_sensor_dashboard_card_view_item_img_sensor_status);

        }
    }


}
