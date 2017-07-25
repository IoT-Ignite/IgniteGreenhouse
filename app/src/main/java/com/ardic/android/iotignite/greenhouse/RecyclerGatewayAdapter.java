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

    List<GatewayViewModel> list_gateway;
    CustomCardViewClickListener listenerGatewayCardViewClick;

    public RecyclerGatewayAdapter(List<GatewayViewModel> list_gateway, CustomCardViewClickListener listenerGatewayCardViewClick) {

        this.list_gateway = list_gateway;
        this.listenerGatewayCardViewClick = listenerGatewayCardViewClick;
    }

    @Override
    public RecyclerGatewayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_gateway_dashboard_cardview_item, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerGatewayCardViewClick.onItemClick(v, view_holder.getAdapterPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtGatewayId.setText(list_gateway.get(position).getGatewayId());

        if (list_gateway.get(position).isGatewayOnline()) {
            holder.imgGatewayStatus.setImageResource(R.drawable.raspberry_pi_online);
        } else {
            holder.imgGatewayStatus.setImageResource(R.drawable.offline_raspberrypi);
        }
    }

    @Override
    public int getItemCount() {
        return list_gateway.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtGatewayId;
        public ImageView imgGatewayStatus;
        public CardView cardViewGateway;


        public ViewHolder(View view) {
            super(view);

            cardViewGateway = view.findViewById(R.id.content_gateway_dashboard_card_view_item_card_view);
            txtGatewayId = view.findViewById(R.id.content_gateway_dashboard_card_view_item_txt_gateway_id);
            imgGatewayStatus = view.findViewById(R.id.content_gateway_dashboard_card_view_item_img_gateway_status);

        }
    }


}
