package com.example.jobs.Models;

import android.graphics.Movie;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Job {

    String title;

    public Job(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
    }

    public static List<Job> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Job> jobs = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            if(jsonArray.getJSONObject(i).has("links"))
                continue;

            Job job = new Job(jsonArray.getJSONObject(i));
            jobs.add(job);
            Log.d("Job", String.valueOf(jobs.get(i)));
        }

        Log.d("Job", "" + jobs.size());
        return jobs;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                '}';
    }
}
