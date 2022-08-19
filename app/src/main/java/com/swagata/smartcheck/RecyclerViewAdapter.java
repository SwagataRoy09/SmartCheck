package com.swagata.smartcheck;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.swagata.smartcheck.ModalClass.RecyclerViewModalClass;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    List<RecyclerViewModalClass> list;
    Context context;
    public RecyclerViewAdapter(List<RecyclerViewModalClass> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_of_recycler_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.viewHolder holder, int position) {
        String name = list.get(position).getName();
        String price = list.get(position).getPrice();
        holder.setData(name,price);
    }

    @Override
    public int getItemCount() {
       return  list.size();
    }

    public void searchItem(ArrayList<RecyclerViewModalClass> searchList) {
        list=searchList;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Patname);
            price = itemView.findViewById(R.id.Patprice);
        }
        public void setData(String n,String p){
            name.setText(n);
            price.setText(p);
        }
    }
}
