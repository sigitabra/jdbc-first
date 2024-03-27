package org.example;

public class Skyrius {
    private String pavadinimas;
    private String vadovoAsmensKodas;

    public Skyrius(String pavadinimas, String vadovoAsmensKodas) {
        this.pavadinimas = pavadinimas;
        this.vadovoAsmensKodas = vadovoAsmensKodas;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getVadovoAsmensKodas() {
        return vadovoAsmensKodas;
    }

    public void setVadovoAsmensKodas(String vadovoAsmensKodas) {
        this.vadovoAsmensKodas = vadovoAsmensKodas;
    }
}
