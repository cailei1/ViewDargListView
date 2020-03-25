package com.cailei.viewdarglistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mFontRecyclerView;
    private List<String> mLists = new ArrayList<>();
    private FontAdapter fontAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFontRecyclerView = findViewById(R.id.front_view);

        creatData();


        fontAdapter=new FontAdapter(mLists,this);
        mFontRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFontRecyclerView.setAdapter(fontAdapter);
    }


    private void creatData() {
        mLists.add("小菜");
        mLists.add("小白");
        mLists.add("小黑");
        mLists.add("小名");
        mLists.add("小耐");
        mLists.add("小小");
        mLists.add("小新");
        mLists.add("小菜");
        mLists.add("小白");
        mLists.add("小黑");
        mLists.add("小名");
        mLists.add("小耐");
        mLists.add("小小");
        mLists.add("小新");
        mLists.add("小菜");
        mLists.add("小白");
        mLists.add("小黑");

    }
}
