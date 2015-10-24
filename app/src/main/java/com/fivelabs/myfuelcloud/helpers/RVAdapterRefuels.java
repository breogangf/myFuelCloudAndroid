package com.fivelabs.myfuelcloud.helpers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fivelabs.myfuelcloud.R;
import com.fivelabs.myfuelcloud.model.Refuel;
import com.fivelabs.myfuelcloud.util.Common;

import java.util.List;

/**
 * Created by breogangf on 16/10/15.
 */
public class RVAdapterRefuels extends RecyclerView.Adapter<RVAdapterRefuels.RefuelViewHolder>{

    List<Refuel> refuels;

    public RVAdapterRefuels(List<Refuel> refuels){
        this.refuels = refuels;
    }

    public static class RefuelViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView date;
        TextView vehicle;
        TextView gas_station;
        TextView price_amount;
        TextView fuel_amount;

        RefuelViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            date = (TextView)itemView.findViewById(R.id.date);
            vehicle = (TextView)itemView.findViewById(R.id.vehicle);
            gas_station = (TextView)itemView.findViewById(R.id.gas_station);
            price_amount = (TextView)itemView.findViewById(R.id.price_amount);
            fuel_amount = (TextView)itemView.findViewById(R.id.fuel_amount);
        }
    }

    @Override
    public RefuelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.refuel_item, parent, false);
        RefuelViewHolder pvh = new RefuelViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RefuelViewHolder holder, int position) {
        holder.date.setText(Common.formatDateTime(refuels.get(position).getDate()));
        holder.vehicle.setText(refuels.get(position).getVehicle().getModel());
        holder.gas_station.setText(String.valueOf(refuels.get(position).getGas_station()));
        holder.price_amount.setText(String.format("%s€", String.valueOf(refuels.get(position).getPrice_amount())));
        holder.fuel_amount.setText(String.format("%sL", String.valueOf(refuels.get(position).getFuel_amount())));
    }

    @Override
    public int getItemCount() {
        return refuels.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}