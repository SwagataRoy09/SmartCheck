package com.swagata.smartcheck;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.swagata.smartcheck.ModalClass.GridModalClass;

import java.util.ArrayList;

public class home_page extends AppCompatActivity {
    GridView grid;
    ImageView backButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        int icons[] = {
                R.drawable.medicine_cost,
                R.drawable.availability_of_blood,
                R.drawable.pathology_price,
                R.drawable.raise_complain,
                R.drawable.complain_status,
                R.drawable.history
        };
        backButton = findViewById(R.id.backspace);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        ArrayList<GridModalClass> list = new ArrayList<>();
        for(int i=0;i<icons.length;i++){
            list.add(new GridModalClass(icons[i]));
        }
        grid = findViewById(R.id.grid);
        grid.setAdapter(new GridAdapter(getApplicationContext(),list));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent intent = new Intent(getApplicationContext(),medicine_cost.class);
                    startActivity(intent);
                }
                if(i==1){
                    Intent intent = new Intent(getApplicationContext(),availability_of_blood.class);
                    startActivity(intent);
                }
                if (i==2){
                    Intent intent = new Intent(getApplicationContext(),pathology_price.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent = new Intent(getApplicationContext(),raise_a_complain.class);
                    startActivity(intent);
                }
                if(i==4){
                    Intent intent = new Intent(getApplicationContext(),complain_status.class);
                    startActivity(intent);
                }
                if(i==5){
                    Intent intent = new Intent(getApplicationContext(),history.class);
                    startActivity(intent);
                }
            }
        });
    }
}