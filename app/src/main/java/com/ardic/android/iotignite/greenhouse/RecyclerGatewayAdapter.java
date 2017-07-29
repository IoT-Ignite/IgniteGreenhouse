package com.ardic.android.iotignite.greenhouse;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */

public class RecyclerGatewayAdapter extends RecyclerView.Adapter<RecyclerGatewayAdapter.ViewHolder> {

    private List<GatewayViewModel> gatewayList;
    private CustomCardViewClickListener gatewayCardViewClickListener;

    public RecyclerGatewayAdapter(List<GatewayViewModel> gatewayList, CustomCardViewClickListener gatewayCardViewClickListener) {

        this.gatewayList = gatewayList;
        this.gatewayCardViewClickListener = gatewayCardViewClickListener;
    }

    @Override
    public RecyclerGatewayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_gateway_dashboard_cardview_item, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatewayCardViewClickListener.onItemClick(v, view_holder.getAdapterPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtGatewayLabel.setText(gatewayList.get(position).getGatewayLabel());
        holder.txtGatewayId.setText(gatewayList.get(position).getGatewayId());

        if (gatewayList.get(position).isGatewayOnline()) {
            holder.imgGatewayStatus.setImageResource(R.drawable.raspberry_pi_online);
            holder.txtGatewayStatus.setText("Connected");

            if (gatewayList.get(position).isGatewayOnline()) {
                holder.imgGatewayStatus.setImageResource(R.drawable.wireless_signal);
            } else {
                holder.imgGatewayStatus.setImageResource(R.drawable.wireless_error);
            }
        }
    }

    @Override
    public int getItemCount() {
        return gatewayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtGatewayLabel;
        public TextView txtGatewayId;
        public TextView txtGatewayStatus;
        public ImageView imgGatewayStatus;
        public CardView cardViewGateway;


        public ViewHolder(View view) {
            super(view);

            cardViewGateway = view.findViewById(R.id.content_gateway_dashboard_card_view_item_card_view);
            txtGatewayLabel = view.findViewById(R.id.content_gateway_dashboard_card_view_item_txt_gateway_label);
            txtGatewayId = view.findViewById(R.id.content_gateway_dashboard_card_view_item_txt_gateway_id);
            //   txtGatewayStatus = view.findViewById(R.id.content_gateway_dashboard_card_view_item_txt_gateway_status);
            imgGatewayStatus = view.findViewById(R.id.content_gateway_dashboard_card_view_item_img_gateway_status);

        }
    }
}