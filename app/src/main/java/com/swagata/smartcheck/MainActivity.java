package com.swagata.smartcheck;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        TextView signup,loginButton;
        signup = findViewById(R.id.tv_signup);
        loginButton=findViewById(R.id.tv_loginbutton);
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
                startActivity(new Intent(getApplicationContext(),home_page.class));
            }
        });
    }
}