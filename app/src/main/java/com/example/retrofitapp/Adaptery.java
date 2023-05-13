package com.example.retrofitapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {
    private Context mContext;
    private List<Results> moviesList;
    private ItemClickListener clickListener;

    public Adaptery(Context mContext, List<Results> moviesList, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.moviesList = moviesList;
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id.setText(moviesList.get(position).getPrice());
        holder.title.setText(moviesList.get(position).getName());

        //Adding glide library to display images (for quick and efficient image loading)
        Glide.with(mContext)
                .load(moviesList.get(position).getImageUrlsThumbnails().get(0))
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(moviesList.get(position));


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
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
