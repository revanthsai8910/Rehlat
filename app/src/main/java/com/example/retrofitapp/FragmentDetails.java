package com.example.retrofitapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class FragmentDetails extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        Bundle bundle = getArguments();
        Results data = (Results) bundle.getSerializable("data");
        //Toast.makeText(getActivity(), data.getPrice(), Toast.LENGTH_LONG).show();

        ImageView imgview = view.findViewById(R.id.imageurl);
        Glide.with(getActivity())
                .load(data.getImageUrls().get(0))
                .into(imgview);

        /*ImageView img = view.findViewById(R.id.imageView3);
        Glide.with(getActivity())
                .load(data.getImageUrlsThumbnails().get(0))
                .into(img);*/

        TextView title = view.findViewById(R.id.name);
        title.setText("Name: "+data.getName());

        TextView price = view.findViewById(R.id.price);
        price.setText("Price: "+data.getPrice());

        TextView createdAt = view.findViewById(R.id.createdat);
        createdAt.setText("Created At: "+data.getCreatedAt());

        /*TextView imageid = view.findViewById(R.id.imageid);
        imageid.setText("Image ID: "+data.getImageIds());

        TextView uid = view.findViewById(R.id.uid);
        uid.setText("UID: "+data.getUid());*/




        return view;
    }

}