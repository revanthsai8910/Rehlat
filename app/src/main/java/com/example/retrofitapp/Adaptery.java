package com.example.retrofitapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {
    private Context mContext;
    private List<Results> details;
    private ItemClickListener clickListener;

    public Adaptery(Context mContext, List<Results> details, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.details = details;
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.each_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id.setText(details.get(position).getPrice());
        holder.title.setText(details.get(position).getName());

        //Adding glide library to display images (for quick and efficient image loading)
        Glide.with(mContext)
                .load(details.get(position).getImageUrlsThumbnails().get(0))
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(details.get(position));


            }
        });

    }

    @Override
    public int getItemCount() {
        return details.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView id;
        ImageView img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView2);
            id = itemView.findViewById(R.id.textView3);
            img = itemView.findViewById(R.id.imageView);
        }
    }


}
