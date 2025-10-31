package com.example.quizapp;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WynikActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "QuizWynikiPrefs";
    private static final String KEY_WYNIKI = "wyniki";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        TextView txtWynik = findViewById(R.id.txtWynik);
        TextView txtData = findViewById(R.id.txtData);
        TextView txtHistoriaWynikow = findViewById(R.id.txtHistoriaWynikow);

        // Pobranie wyniku i daty z Intent
        String wynik = getIntent().getStringExtra("wynik");
        String data = getIntent().getStringExtra("data");

        // Wyświetlenie aktualnego wyniku
        txtWynik.setText("Twój wynik: " + wynik + "%");
        txtData.setText("Data: " + data);

        // Zapisanie wyniku w SharedPreferences
        zapiszWynik(wynik, data);

        // Pobranie i wyświetlenie historii wyników
        List<String> historiaWynikow = pobierzHistorieWynikow();
        StringBuilder historiaTekst = new StringBuilder();
        for (String w : historiaWynikow) {
            historiaTekst.append(w).append("\n");
        }
        txtHistoriaWynikow.setText(historiaTekst.toString());
    }

    // Metoda do zapisywania wyniku w SharedPreferences
    private void zapiszWynik(String wynik, String data) {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Pobranie istniejącej historii wyników
        Set<String> wynikiSet = preferences.getStringSet(KEY_WYNIKI, new HashSet<>());
        wynikiSet.add("Wynik: " + wynik + "%, Data: " + data);

        // Zapisanie zaktualizowanej historii
        editor.putStringSet(KEY_WYNIKI, wynikiSet);
        editor.apply();
    }

    // Metoda do pobierania historii wyników z SharedPreferences
    private List<String> pobierzHistorieWynikow() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        Set<String> wynikiSet = preferences.getStringSet(KEY_WYNIKI, new HashSet<>());

        return new ArrayList<>(wynikiSet);
    }
}
