package com.example.lab7implicitintent;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<String> url = new ArrayList<>();


    public RecyclerAdapter(List<String> url) {
        this.url = url;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        if(getItemCount()>0) {
            holder.textView.setText(url.get(position));
            holder.textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent i = new Intent(v.getContext(), MainActivity.class);
//                    Intent i = new Intent();
//                    i.setAction(Intent.ACTION_SEND);
                    i.putExtra("url",url.get(position));
                    v.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return url.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
