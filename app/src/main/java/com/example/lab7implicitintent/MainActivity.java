package com.example.lab7implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private static final String KEY_RECYCLER_STATE = "recycler_state";
//    public static List<String> URLLIST = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<String> url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        webView = findViewById(R.id.webView);

        Intent i = getIntent();
        String data = i.getStringExtra(Intent.EXTRA_TEXT) == null ? "" : i.getStringExtra(Intent.EXTRA_TEXT);
        String link = i.getStringExtra("url");

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);

        loadData();
        url.add(data);

        recyclerAdapter = new RecyclerAdapter(url);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        saveData();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Share preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(url);
        editor.putString("task list", json);
//        editor.apply();
        editor.commit();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Share preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<List<String>>() {}.getType();
        url = gson.fromJson(json, type);

        if(url == null){
            url = new ArrayList<>();
        }
//        buildRecyclerView(url);
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//
//    }

}
