package com.example.retrofitapp;

import java.util.ArrayList;
import java.util.List;

public class Response {
    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    List<Results> results = new ArrayList<Results>();


}
