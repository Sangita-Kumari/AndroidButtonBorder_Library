package com.prem.borderbuttonlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.prem.borderbutton.BorderButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BorderButton btn = findViewById(R.id.btn);
        btn.setButtonColor("#c1c1c1")
                .setBorderWidth(5)
                .setBorderColor("#000000")
                .setCornerRadius(35)
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Awesome", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
