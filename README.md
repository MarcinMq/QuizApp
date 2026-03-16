# 📱 QuizApp

![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg) ![Java](https://img.shields.io/badge/Language-Java-orange.svg) ![Gradle](https://img.shields.io/badge/Build-Gradle-blue.svg)

**QuizApp** to lekka i intuicyjna aplikacja mobilna na system Android, zaprojektowana do sprawdzania wiedzy poprzez interaktywne testy. Dzięki dynamicznemu systemowi ładowania danych, baza pytań może być rozwijana bez konieczności ingerencji w kod źródłowy.

---

## ✨ Funkcje aplikacji

* **🕹️ Interaktywny Quiz** – Płynny interfejs użytkownika z pytaniami wielokrotnego wyboru.
* **📂 Dynamiczne dane (JSON)** – Pytania są wczytywane z pliku zewnętrznego w folderze `assets`, co ułatwia zarządzanie treścią.
* **📊 System Punktacji** – Natychmiastowe sprawdzanie poprawności i podsumowanie wyników po zakończeniu quizu.
* **⚙️ Panel Ustawień** – Personalizacja doświadczenia użytkownika (np. zmiana liczby pytań lub poziomu trudności).

---

## 🏗️ Struktura Projektu

Główna logika aplikacji znajduje się w pakiecie `com.example.quizapp`:

| Komponent | Opis |
| :--- | :--- |
| **`MainActivity`** | Ekran powitalny i menu główne aplikacji. |
| **`QuizActivity`** | Główny silnik quizu – obsługa logiki pytań, liczników i walidacji. |
| **`WynikActivity`** | Prezentacja końcowego rezultatu i statystyk sesji. |
| **`UstawieniaActivity`** | Zarządzanie preferencjami i konfiguracją aplikacji. |
| **`Pytanie`** | Model danych reprezentujący strukturę pojedynczego pytania. |

---

## 📄 Zarządzanie Pytaniami (Dane)

Wszystkie pytania przechowywane są w formacie JSON w lokalizacji:  
`app/src/main/assets/pytania.json`

### Przykład struktury JSON:
```json
{
    "pytanie": "Jakie jest największe miasto w Polsce?",
    "odpowiedzi": [
        "Kraków",
        "Warszawa",
        "Wrocław",
        "Poznań"
    ],
    "poprawna": 1
}
```
> [!IMPORTANT]
> Pole `poprawna` to indeks tablicy `odpowiedzi` (liczony od 0). W powyższym przykładzie wartość `1` oznacza "Warszawa".

---

## 🛠️ Wymagania Techniczne

Aby uruchomić lub modyfikować projekt, upewnij się, że posiadasz:
* **Android Studio** (wersja Flamingo lub nowsza)
* **JDK 17** lub nowszy
* **Min SDK**: Sprawdź szczegóły w pliku `app/build.gradle.kts`

---

## 🚀 Instalacja i Uruchomienie

1.  **Sklonuj repozytorium**
    ```bash
    git clone [https://github.com/marcinmq/quizapp.git](https://github.com/marcinmq/quizapp.git)
    ```
2.  **Otwórz projekt**
    Uruchom Android Studio i wybierz opcję **Open**, a następnie wskaż folder z projektem.
3.  **Synchronizacja**
    Poczekaj, aż Gradle pobierze wszystkie zależności (proces widoczny na dolnym pasku).
4.  **Uruchomienie**
    Podłącz urządzenie z Androidem lub uruchom emulator i kliknij zieloną ikonę **Run** (Play).

---

*Opracowano na potrzeby projektu QuizApp.*
