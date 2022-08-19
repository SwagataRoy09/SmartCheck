package com.swagata.smartcheck;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        TextView signup,loginButton;
        signup = findViewById(R.id.tv_signup);
        loginButton=findViewById(R.id.tv_loginbutton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        Intent i1 = new Intent(this,sign_up.class);
        Intent i2 = new Intent(this,home_page.class);
        signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(i1);
    }
});
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCall();
                startActivity(new Intent(getApplicationContext(),home_page.class));
            }
        });

    }
    void apiCall(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://script.google.com/macros/s/AKfycbwVk11fhi1DmeWfaF6nDe67L9B9-WNSgAIjO-iFm-IgIZvQ7uA6jp2V9VxmAnsrq4ZCUQ/exec";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    Toast.makeText(MainActivity.this, "SUCCESSFULLY LOGGED IN", Toast.LENGTH_SHORT).show();
                    JSONObject obj1 = new JSONObject(response);
                    JSONArray arr = obj1.getJSONArray("data");
                    for(int i=0;i<arr.length();i++){
                        JSONObject innerobject = arr.getJSONObject(i);
                        String name = innerobject.getString("name");
                        String email = innerobject.getString("email");
                        String password = innerobject.getString("password");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "CANNOT LOAD THE INFORMATION", Toast.LENGTH_SHORT).show();
            }
        }){
          protected Map<String,String> getParams(){
              Map<String,String> params = new HashMap<>();
              params.put("email",email.getText().toString());
              params.put("password",password.getText().toString());
              return params;
          }
        };
        queue.add(request);
    }
}