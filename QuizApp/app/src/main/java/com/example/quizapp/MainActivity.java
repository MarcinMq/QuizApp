package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnRozpocznijGre;
    private Button btnUstawienia;
    private Button btnWyniki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja przycisków
        btnRozpocznijGre = findViewById(R.id.btn_rozpocznij_gre);
        btnUstawienia = findViewById(R.id.btn_ustawienia);
        btnWyniki = findViewById(R.id.btn_wyniki);

        // Obsługa przycisku "Rozpocznij Grę"
        btnRozpocznijGre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        // Obsługa przycisku "Ustawienia"
        btnUstawienia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UstawieniaActivity.class);
                startActivity(intent);
            }
        });

        // Obsługa przycisku "Wyniki"
        btnWyniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WynikActivity.class);
                startActivity(intent);
            }
        });
    }
}
