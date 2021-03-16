package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.jobs.Models.Job;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    String tag = "MainActivity";
    String url ="http://api.dataatwork.org/v1/jobs";
    RecyclerView rvJobs;
    List<Job> jobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvJobs = findViewById(R.id.rvJobs);
        jobs = new ArrayList<>();


        JobsAdapter jobsAdapter = new JobsAdapter(jobs);
        rvJobs.setAdapter(jobsAdapter);

        rvJobs.setLayoutManager(new LinearLayoutManager(this));


        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.i(tag, "OnSuccess" + json.jsonArray.toString());

                try {
                    jobs.addAll(Job.fromJsonArray(json.jsonArray));
                    Log.d("MainActivity", "" + jobs.size());
                    jobsAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e(tag, "OnFailure", throwable);
            }
        });
    }
}