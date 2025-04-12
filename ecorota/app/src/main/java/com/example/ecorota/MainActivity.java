package com.example.ecorota;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView tvWelcome;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvWelcome = findViewById(R.id.tv_welcome);
        
        // Aqui você pode implementar as funcionalidades principais do seu aplicativo EcoRota
        // como mapas, rotas ecológicas, etc.
    }
}