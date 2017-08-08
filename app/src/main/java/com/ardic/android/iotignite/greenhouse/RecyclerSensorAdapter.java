package com.ardic.android.iotignite.greenhouse;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardic.android.iotignite.greenhouse.listeners.CardViewClickListener;
import com.ardic.android.iotignite.lib.restclient.constant.Api;

import java.util.List;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */


public class RecyclerSensorAdapter extends RecyclerView.Adapter<RecyclerSensorAdapter.ViewHolder> {

    private static final String TAG = RecyclerSensorAdapter.class.getSimpleName();
    private List<SensorViewModel> sensorList;
    private CardViewClickListener sensorCardViewClickListener;

    public RecyclerSensorAdapter(List<SensorViewModel> sensorList, CardViewClickListener sensorCardViewClickListener) {

        this.sensorList = sensorList;
        this.sensorCardViewClickListener = sensorCardViewClickListener;
    }

    @Override
    public RecyclerSensorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_sensor_dashboard_cardview_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        viewHolder.imgSensorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorCardViewClickListener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorCardViewClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (Api.DEBUG) {
            Log.i(TAG, "List : " + sensorList.toString());
        }

        SensorViewModel mdl = sensorList.get(position);
        holder.txtSensorId.setText(mdl.getSensorId());
        holder.txtSensorValue.setText(mdl.getSensorValue());
        holder.txtNodeId.setText(mdl.getNodeId());
        holder.txtLastDataTime.setText(mdl.getSensorLastSyncDateString());
        setSensorImageByType(holder, mdl.getSensorType());
        setSensorState(holder, mdl);

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
        public TextView txtLastDataTime;
        public TextView txtNodeId;
        public ImageView imgSensorType;
        public ImageView imgSensorStatus;
        public CardView cardViewSensor;
        public ImageView imgSensorInfo;


        public ViewHolder(View view) {
            super(view);

            cardViewSensor = view.findViewById(R.id.content_sensor_dashboard_card_view_item_card_view);
            txtSensorId = view.findViewById(R.id.content_sensor_dashboard_card_view_item_txt_sensor_thing_id);
            txtSensorValue = view.findViewById(R.id.content_sensor_dashboard_card_view_item_txt_sensor_value);
            txtLastDataTime = view.findViewById(R.id.content_sensor_dashboard_card_view_item_txt_date);
            imgSensorType = view.findViewById(R.id.content_sensor_dashboard_card_view_item_img_sensor_type);
            imgSensorStatus = view.findViewById(R.id.content_sensor_dashboard_card_view_item_img_sensor_status);
            txtNodeId = view.findViewById(R.id.content_sensor_dashboard_card_view_item_txt_sensor_node_id);
            imgSensorInfo = view.findViewById(R.id.img_sensor_info_content_sensor_dashboard_card_view_item);
        }
    }

    private void setSensorImageByType(ViewHolder holder, String type) {

        if (Constants.GREENHOUSE_TEMPERATURE_THINGTYPE.equals(type)) {
            holder.imgSensorType.setImageResource(R.drawable.thermometer);
        } else if (Constants.GREENHOUSE_HUMIDITY_THINGTYPE.equals(type)) {
            holder.imgSensorType.setImageResource(R.drawable.humidity);
        }

    }

    private void setSensorState(ViewHolder holder, SensorViewModel mdl) {
        if (mdl.isSensorOnline()) {
            holder.imgSensorStatus.setImageResource(android.R.drawable.presence_online);
        } else {
            holder.imgSensorStatus.setImageResource(android.R.drawable.presence_invisible);
        }
    }


}
