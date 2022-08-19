package com.swagata.smartcheck;

import static android.app.PendingIntent.getActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class raise_a_complain extends AppCompatActivity {
    ImageView pdf;
    TextView file,pdfname;
    ActivityResultLauncher<Intent> resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_acomplain);
        pdf = findViewById(R.id.pdf);
        file = findViewById(R.id.file);
        pdfname = findViewById(R.id._pdf);
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts
                        .StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    public void onActivityResult(
                            ActivityResult result)
                    {

                        // Initialize result data
                        Intent data = result.getData();
                        // check condition
                        if (data != null) {
                            Uri sUri = data.getData();
                            File myFile = new File(sUri.toString());
                            pdfname.setText(Html.fromHtml(myFile.getName()));
                            // Get PDF path
                            String sPath = sUri.getPath();
                            // Set path on text view
                            file.setText(Html.fromHtml(
                                    sPath));
                        }
                    }
                });
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(
                        getApplicationContext(),
                        Manifest.permission
                                .READ_EXTERNAL_STORAGE)
                        != PackageManager
                        .PERMISSION_GRANTED) {
                    // When permission is not granted
                    // Result permission
                    ActivityCompat.requestPermissions(
                            raise_a_complain.this,
                            new String[]{
                                    Manifest.permission
                                            .READ_EXTERNAL_STORAGE},
                            1);
                } else {
                    // When permission is granted
                    // Create method
                    selectPDF();
                }
            }
        });
    }
        private void selectPDF()
        {
            // Initialize intent
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            // set type
            intent.setType("application/pdf");
            // Launch intent
            resultLauncher.launch(intent);
        }
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);

        // check condition
        if (requestCode == 1 && grantResults.length > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            // When permission is granted
            // Call method
            selectPDF();
        } else {
            // When permission is denied
            // Display toast
            Toast
                    .makeText(getApplicationContext(),
                            "Permission Denied",
                            Toast.LENGTH_SHORT)
                    .show();
        }
      }
    }