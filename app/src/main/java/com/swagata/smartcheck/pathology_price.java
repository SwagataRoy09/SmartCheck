package com.swagata.smartcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.swagata.smartcheck.ModalClass.RecyclerViewModalClass;

import java.util.ArrayList;
import java.util.List;

public class pathology_price extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    List<RecyclerViewModalClass> list;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathology_price);
        recyclerView = findViewById(R.id.recycler_view1);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        list = new ArrayList<>();
        ImageView backButton;


        backButton = findViewById(R.id.backspace);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),home_page.class);
                startActivity(intent);
            }
        });
        for(int i=0;i<10;i++) {
            list.add(new RecyclerViewModalClass("SWAGATA ROY", "21"));
            Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_SHORT).show();
        }
        adapter = new RecyclerViewAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}