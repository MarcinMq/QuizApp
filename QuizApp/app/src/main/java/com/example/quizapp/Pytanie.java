package com.example.quizapp;

import java.util.List;

public class Pytanie {
    private String pytanie;
    private List<String> odpowiedzi;
    private List<String> poprawneOdpowiedzi;
    private int czas;

    public Pytanie(String pytanie, List<String> odpowiedzi, List<String> poprawneOdpowiedzi, int czas) {
        this.pytanie = pytanie;
        this.odpowiedzi = odpowiedzi;
        this.poprawneOdpowiedzi = poprawneOdpowiedzi;
        this.czas = czas;
    }

    public String getPytanie() {
        return pytanie;
    }

    public List<String> getOdpowiedzi() {
        return odpowiedzi;
    }

    public List<String> getPoprawneOdpowiedzi() {
        return poprawneOdpowiedzi;
    }

    public int getCzas() {
        return czas;
    }
}
