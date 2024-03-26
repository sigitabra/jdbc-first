package org.example;

import java.util.ArrayList;

public class Projektas {
    private int id;
    private String pavadinimas;
    private ArrayList<Darbuotojas> darbuotojuSarasas;

    public Projektas(int id, String pavadinimas, ArrayList<Darbuotojas> darbuotojuSarasas) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.darbuotojuSarasas = darbuotojuSarasas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public ArrayList<Darbuotojas> getDarbuotojuSarasas() {
        return darbuotojuSarasas;
    }

    public void setDarbuotojuSarasas(ArrayList<Darbuotojas> darbuotojuSarasas) {
        this.darbuotojuSarasas = darbuotojuSarasas;
    }
}
