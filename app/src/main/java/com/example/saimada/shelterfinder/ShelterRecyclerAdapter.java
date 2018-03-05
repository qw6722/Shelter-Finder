package com.example.saimada.shelterfinder;

/**
 * Created by rohit on 2/28/2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by csa on 3/7/2017.
 */

public class ShelterRecyclerAdapter extends RecyclerView.Adapter<ShelterRecyclerAdapter.MyHoder>{

    List<Shelter> list;
    Context context;
    //Used for binding and getting information
    private Spinner genderSpinner;
    private Spinner ageSpinner;

    //Keeping track of Spinner changes
    private String _gender = "NA";
    private String _age = "Anyone";

    public ShelterRecyclerAdapter(List<Shelter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_shelter,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Shelter shelter = list.get(position);
        holder.name.setText(shelter.getShelterName());
        holder.address.setText(shelter.getAddress());
        holder.capacity.setText(shelter.getCapacity());
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView name,address,capacity;


        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.vname);
            address= (TextView) itemView.findViewById(R.id.vaddress);
            capacity= (TextView) itemView.findViewById(R.id.vcapacity);

        }
    }

}
