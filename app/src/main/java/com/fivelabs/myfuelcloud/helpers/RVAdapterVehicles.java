package com.fivelabs.myfuelcloud.helpers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fivelabs.myfuelcloud.R;
import com.fivelabs.myfuelcloud.model.Vehicle;

import java.util.List;

/**
 * Created by breogangf on 16/10/15.
 */
public class RVAdapterVehicles extends RecyclerView.Adapter<RVAdapterVehicles.VehicleViewHolder>{

    List<Vehicle> vehicles;

    public RVAdapterVehicles(List<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView brand;
        TextView model;
        TextView year;

        VehicleViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            brand = (TextView)itemView.findViewById(R.id.brand);
            model = (TextView)itemView.findViewById(R.id.model);
            year = (TextView)itemView.findViewById(R.id.year);
        }
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_item, parent, false);
        VehicleViewHolder pvh = new VehicleViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        holder.brand.setText(vehicles.get(position).getBrand());
        holder.model.setText(vehicles.get(position).getModel());
        holder.year.setText(String.valueOf(vehicles.get(position).getYear()));
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}