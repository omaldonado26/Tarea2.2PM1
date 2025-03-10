package com.example.app2prestapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnVerPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVerPosts = findViewById(R.id.btnVerPosts);
            btnVerPosts.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ListPlaceHolder.class);
                startActivity(intent);
            });

;
    }

}
