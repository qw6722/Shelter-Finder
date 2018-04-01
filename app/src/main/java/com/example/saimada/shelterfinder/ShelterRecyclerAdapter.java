package com.example.saimada.shelterfinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * [Class description goes here]
 * @author Rohit. Edited by Vadini and Arnab
 * @since 2/28/2018
 */
public class ShelterRecyclerAdapter extends RecyclerView.Adapter<ShelterRecyclerAdapter.MyHolder>{

    List<Shelter> list;
    Context context;

    public ShelterRecyclerAdapter(List<Shelter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_shelter,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Shelter shelter = list.get(position);
        holder.name.setText(shelter.getShelterName());
        holder.address.setText(shelter.getAddress());
        holder.capacity.setText(shelter.getCapacity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Shelter getItem(int position) {
        return list.get(position);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,address,capacity;


        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vname);
            address= itemView.findViewById(R.id.vaddress);
            capacity= itemView.findViewById(R.id.vcapacity);

        }

    }

}
