package com.example.retrofitapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFragment extends Fragment implements ItemClickListener {

    View view;
    RecyclerView recyclerView;
    List<Results> details;
    TextView sortlth;
    TextView sorthtl;

    TextView sortdatelth;
    TextView sortdatehtl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        details = new ArrayList<>();
        sortlth = view.findViewById(R.id.lth);
        sorthtl = view.findViewById(R.id.htl);

        sortdatehtl = view.findViewById(R.id.datehtl);
        sortdatelth = view.findViewById(R.id.datelth);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);
        Call<Response> call = api.getDetails();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<com.example.retrofitapp.Response> call, retrofit2.Response<Response> response) {

                if(response.code() != 200)
                {
                    return;
                }

                com.example.retrofitapp.Response r = response.body();

                details = r.results;

                putDataIntoRecyclerView(r.results);

            }

            @Override
            public void onFailure(Call<com.example.retrofitapp.Response> call, Throwable t) {

            }
        });

        sortlth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<details.size();i++) {
                    for (int j = i + 1; j < details.size(); j++) {
                        String a1 = details.get(i).getPrice().replace("AED ","");
                        String a2 = details.get(j).getPrice().replace("AED ","");
                        if (Integer.parseInt(a1) > Integer.parseInt(a2)) {
                            Results temp1 = details.get(i);
                            Results temp2 = details.get(j);
                            details.set(i, temp2);
                            details.set(j, temp1);
                        }
                    }
                }
                putDataIntoRecyclerView(details);
            }
        });

        sorthtl.setOnClickListener((new View.OnClickListener(){

            public void onClick(View view) {
                for(int i=0;i<details.size();i++) {
                    for (int j = i + 1; j < details.size(); j++) {
                        String a1 = details.get(i).getPrice().replace("AED ","");
                        String a2 = details.get(j).getPrice().replace("AED ","");
                        if (Integer.parseInt(a1) < Integer.parseInt(a2)) {
                            Results temp1 = details.get(i);
                            Results temp2 = details.get(j);
                            details.set(i, temp2);
                            details.set(j, temp1);
                        }
                    }
                }
                putDataIntoRecyclerView(details);
            }
        }));

        sortdatelth.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Collections.sort(details, new Comparator<Results>() {
                    @Override
                    public int compare(Results m1, Results m2) {
                        return m1.getCreatedAt().compareTo(m2.getCreatedAt());
                    }
                });
                putDataIntoRecyclerView(details);
            }
        });

        sortdatehtl.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Collections.sort(details, new Comparator<Results>() {
                    @Override
                    public int compare(Results m1, Results m2) {
                        return m1.getCreatedAt().compareTo(m2.getCreatedAt());
                    }
                });
                Collections.reverse(details);
                putDataIntoRecyclerView(details);
            }
        });


        return view;
    }

    private void putDataIntoRecyclerView(List<Results> details)
    {
        Adaptery adaptery = new Adaptery(getActivity(), details,this );
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptery);
    }

    @Override
    public void onItemClick(Results data) {
        ((MainActivity)getActivity()).showDetails(data);
    }
}
