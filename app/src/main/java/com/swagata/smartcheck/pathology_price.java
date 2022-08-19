package com.swagata.smartcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.swagata.smartcheck.ModalClass.RecyclerViewModalClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class pathology_price extends AppCompatActivity {
    ProgressDialog dialog;
    EditText search;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    List<RecyclerViewModalClass> list;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathology_price);
        dialog = new ProgressDialog(this);
        dialog.setTitle("pathology");
        dialog.setMessage("LOADING ... ");
        dialog.setCancelable(false);
        recyclerView = findViewById(R.id.recycler_view1);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(linearLayoutManager);
        ImageView backButton;
        backButton = findViewById(R.id.backspace);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),home_page.class);
                startActivity(intent);
            }
        });
        search=findViewById(R.id.search);
        dialog.show();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = search.getText().toString();
                if (str.length()==0){
                    apiCall();
                }
                apiCall(str);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        apiCall();
    }
    void apiCall(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://script.google.com/macros/s/AKfycbzajhT_N5kgVC_NJT2qEeVJ-7kq6TrZLm4QAymYke4IGSUwbvJuSRw0_odNZW1z0-SlSg/exec";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj1 = new JSONObject(response);
                    JSONArray arr = obj1.getJSONArray("data");
                    for(int i=0;i<arr.length();i++){
                        JSONObject innerobject = arr.getJSONObject(i);
                        String name = innerobject.getString("name");
                        String price = innerobject.getString("price");
                        RecyclerViewModalClass modalClass = new RecyclerViewModalClass(name,price);
                        list.add(modalClass);
                    }
                    Comparator<RecyclerViewModalClass> comparator = new Comparator<RecyclerViewModalClass>(){
                        public int compare(RecyclerViewModalClass o1,RecyclerViewModalClass o2){
                            return o1.getName().compareTo(o2.getName());
                        }
                    };
                    adapter = new RecyclerViewAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(pathology_price.this, "CANNOT LOAD THE INFORMATION", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
        dialog.dismiss();
    }
    void apiCall(String str){
        ArrayList<RecyclerViewModalClass> SearchList = new ArrayList<>();
       for(int i=0;i<list.size();i++){
           String toBeSearched = list.get(i).getName();
           if (toBeSearched.toLowerCase(Locale.ROOT).contains(str.toLowerCase(Locale.ROOT))){
               SearchList.add(list.get(i));
           }
       }
        adapter.searchItem(SearchList);
       recyclerView.setAdapter(adapter);
    }
}