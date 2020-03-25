package com.cailei.viewdarglistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cailei
 * @date : 2020-03-25 11:36
 * @description :
 */
public class FontAdapter extends RecyclerView.Adapter<FontAdapter.MyViewHolder> {
    private List<String> mLists = new ArrayList<>();
    public Context mContext;

    public FontAdapter(List<String> mLists, Context mContext) {
        this.mLists = mLists;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_adapter, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
