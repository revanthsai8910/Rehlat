package com.example.retrofitapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    List<Results> movieList;
    Button sortlth;
    Button sorthtl;

    Button sortdatelth;
    Button sortdatehtl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        movieList = new ArrayList<>();
        sortlth = view.findViewById(R.id.lth);
        sorthtl = view.findViewById(R.id.htl);

        sortdatehtl = view.findViewById(R.id.datehtl);
        sortdatelth = view.findViewById(R.id.datelth);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<Response> call = movieApi.getMovies();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<com.example.retrofitapp.Response> call, retrofit2.Response<Response> response) {

                if(response.code() != 200)
                {
                    return;
                }

                com.example.retrofitapp.Response movies = response.body();

                movieList = movies.results;

                putDataIntoRecyclerView(movies.results);

            }


            @Override
            public void onFailure(Call<com.example.retrofitapp.Response> call, Throwable t) {

            }
        });

        sortlth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<movieList.size();i++) {
                    for (int j = i + 1; j < movieList.size(); j++) {
                        String a1 = movieList.get(i).getPrice().replace("AED ","");
                        String a2 = movieList.get(j).getPrice().replace("AED ","");
                        if (Integer.parseInt(a1) > Integer.parseInt(a2)) {
                            Results temp1 = movieList.get(i);
                            Results temp2 = movieList.get(j);
                            movieList.set(i, temp2);
                            movieList.set(j, temp1);
                        }
                    }
                }
                putDataIntoRecyclerView(movieList);
            }
        });

        sorthtl.setOnClickListener((new View.OnClickListener(){

            public void onClick(View view) {
                for(int i=0;i<movieList.size();i++) {
                    for (int j = i + 1; j < movieList.size(); j++) {
                        String a1 = movieList.get(i).getPrice().replace("AED ","");
                        String a2 = movieList.get(j).getPrice().replace("AED ","");
                        if (Integer.parseInt(a1) < Integer.parseInt(a2)) {
                            Results temp1 = movieList.get(i);
                            Results temp2 = movieList.get(j);
                            movieList.set(i, temp2);
                            movieList.set(j, temp1);
                        }
                    }
                }
                putDataIntoRecyclerView(movieList);
            }
        }));

        sortdatelth.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Collections.sort(movieList, new Comparator<Results>() {
                    @Override
                    public int compare(Results m1, Results m2) {
                        return m1.getCreatedAt().compareTo(m2.getCreatedAt());
                    }
                });
                putDataIntoRecyclerView(movieList);
            }
        });

        sortdatehtl.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Collections.sort(movieList, new Comparator<Results>() {
                    @Override
                    public int compare(Results m1, Results m2) {
                        return m1.getCreatedAt().compareTo(m2.getCreatedAt());
                    }
                });
                Collections.reverse(movieList);
                putDataIntoRecyclerView(movieList);
            }
        });


        return view;
    }

    private void putDataIntoRecyclerView(List<Results> movieList)
    {
        Adaptery adaptery = new Adaptery(getActivity(), movieList,this );
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptery);
    }

    @Override
    public void onItemClick(Results data) {
        ((MainActivity)getActivity()).showDetails(data);
    }
}
