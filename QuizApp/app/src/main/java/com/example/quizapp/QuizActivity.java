// Klasa obsługująca quiz
package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// Aktywność obsługująca rozgrywkę w quizie
public class QuizActivity extends AppCompatActivity {

    // Lista przechowująca pytania
    private List<Pytanie> listaPytan = new ArrayList<>();
    private int aktualnePytanieIndex = 0; // Indeks aktualnego pytania
    private int poprawneOdpowiedzi = 0; // Liczba poprawnych odpowiedzi
    private TextView txtPytanie, txtTimer; // Tekst pytania i licznik czasu
    private LinearLayout layoutOpcje; // Kontener na opcje odpowiedzi
    private Button btnDalej;
    private CountDownTimer timer; // Licznik czasu na pytanie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        txtPytanie = findViewById(R.id.txtPytanie);
        txtTimer = findViewById(R.id.txtTimer);
        layoutOpcje = findViewById(R.id.layoutOpcje);
        btnDalej = findViewById(R.id.btnDalej);

        // Wczytanie pytań z pliku JSON
        listaPytan = wczytajPytaniaZJson();

        // Sprawdzenie, czy lista pytań nie jest pusta
        if (!listaPytan.isEmpty()) {
            wyswietlPytanie();
        }


        btnDalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nastepnePytanie();
            }
        });
    }

    // Metoda wyświetlająca aktualne pytanie i jego opcje odpowiedzi
    private void wyswietlPytanie() {
        Pytanie pytanie = listaPytan.get(aktualnePytanieIndex); // Pobierz aktualne pytanie
        txtPytanie.setText(pytanie.getPytanie()); // Wyświetl treść pytania
        layoutOpcje.removeAllViews(); // Usuń poprzednie opcje odpowiedzi

        // Sprawdzenie, czy pytanie ma więcej niż jedną poprawną odpowiedź
        if (pytanie.getPoprawneOdpowiedzi().size() > 1) {
            // Wielokrotny wybór - użycie CheckBox
            for (String odpowiedz : pytanie.getOdpowiedzi()) {
                CheckBox checkBox = new CheckBox(this); // Tworzenie CheckBoxa
                checkBox.setText(odpowiedz); // Ustawienie tekstu odpowiedzi
                layoutOpcje.addView(checkBox); // Dodanie CheckBoxa do układu
            }
        } else {
            // Jednokrotny wybór - użycie RadioButton
            RadioGroup radioGroup = new RadioGroup(this); // Tworzenie grupy RadioButton
            for (String odpowiedz : pytanie.getOdpowiedzi()) {
                RadioButton radioButton = new RadioButton(this); // Tworzenie RadioButton
                radioButton.setText(odpowiedz); // Ustawienie tekstu odpowiedzi
                radioGroup.addView(radioButton); // Dodanie RadioButton do grupy
            }
            layoutOpcje.addView(radioGroup); // Dodanie grupy RadioButton do układu
        }

        uruchomTimer(pytanie.getCzas()); // Uruchom licznik czasu dla pytania
    }

    // Metoda obsługująca licznik czasu
    private void uruchomTimer(int czas) {
        if (timer != null) {
            timer.cancel(); // Anuluj istniejący licznik, jeśli działa
        }

        // Tworzenie nowego licznika czasu
        timer = new CountDownTimer(czas * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText("Czas: " + millisUntilFinished / 1000 + "s"); // Aktualizacja licznika
            }

            @Override
            public void onFinish() {
                nastepnePytanie(); // Automatyczne przejście do kolejnego pytania
            }
        };

        timer.start(); // Uruchomienie licznika
    }

    // Metoda obsługująca przejście do następnego pytania
    private void nastepnePytanie() {
        aktualnePytanieIndex++; // Przejście do następnego pytania

        if (aktualnePytanieIndex < listaPytan.size()) {
            wyswietlPytanie(); // Wyświetl następne pytanie
        } else {
            zakonczQuiz(); // Jeśli brak pytań, zakończ quiz
        }
    }

    // Metoda kończąca quiz i przechodząca do WynikActivity
    private void zakonczQuiz() {
        Intent intent = new Intent(this, WynikActivity.class); // Tworzenie intencji
        intent.putExtra("poprawneOdpowiedzi", poprawneOdpowiedzi); // Przekazanie liczby poprawnych odpowiedzi
        intent.putExtra("liczbaPytan", listaPytan.size()); // Przekazanie liczby pytań
        startActivity(intent);
        finish();
    }

    // Metoda wczytująca pytania z pliku JSON
    private List<Pytanie> wczytajPytaniaZJson() {
        List<Pytanie> listaPytan = new ArrayList<>(); // Tworzenie pustej listy pytań

        try {
            InputStream is = getAssets().open("pytania.json"); // otwarcie pliku JSON
            int size = is.available(); // pobiera rozmiar pliku
            byte[] buffer = new byte[size]; // tworzy tablicę na dane
            is.read(buffer); // wczytuje dane do tablicy
            is.close();

            String json = new String(buffer, "UTF-8"); // Konwertuje bajty na tekst JSON
            JSONArray jsonArray = new JSONArray(json); // Zamienia tekst JSON na tablicę JSON

            // Iteracja przez elementy tablicy JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i); // Pobierz obiekt JSON

                int czas = object.getInt("czas"); // Pobierz czas pytania
                String pytanie = object.getString("pytanie"); // Pobierz treść pytania
                JSONArray odpowiedziArray = object.getJSONArray("odpowiedzi"); // Pobierz odpowiedzi
                JSONArray poprawneOdpArray = object.getJSONArray("poprawneOdp"); // Pobierz poprawne odpowiedzi

                List<String> odpowiedzi = new ArrayList<>();
                for (int j = 0; j < odpowiedziArray.length(); j++) {
                    odpowiedzi.add(odpowiedziArray.getString(j)); // Dodaj odpowiedzi do listy
                }

                List<String> poprawneOdp = new ArrayList<>();
                for (int j = 0; j < poprawneOdpArray.length(); j++) {
                    poprawneOdp.add(poprawneOdpArray.getString(j)); // Dodaj poprawne odpowiedzi do listy
                }

                listaPytan.add(new Pytanie(pytanie, odpowiedzi, poprawneOdp, czas)); // Dodaj pytanie do listy
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace(); // Obsługa błędów
        }

        return listaPytan; // Zwróć listę pytań
    }
}
