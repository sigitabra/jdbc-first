package org.example;

import java.time.LocalDate;

public class Darbuotojas {
    private String asmenskodas;
    private String vardas;
    private String pavarde;
    private LocalDate dirbanuo;
    private LocalDate gimimometai;
    private String pareigos;
    private String skyriusPavadinimas;
    private Integer projektasId;

    public Darbuotojas(String asmenskodas, String vardas, String pavarde, LocalDate dirbanuo, LocalDate gimimometai, String pareigos, String skyriusPavadinimas, Integer projektasId) {
        this.asmenskodas = asmenskodas;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.dirbanuo = dirbanuo;
        this.gimimometai = gimimometai;
        this.pareigos = pareigos;
        this.skyriusPavadinimas = skyriusPavadinimas;
        this.projektasId = projektasId;
    }

    public Darbuotojas(String vardas, String pavarde) {
        this.vardas = vardas;
        this.pavarde = pavarde;
    }

    @Override
    public String toString() {
        return vardas + " " + pavarde + "\n";
    }

    public String getAsmenskodas() {
        return asmenskodas;
    }

    public void setAsmenskodas(String asmenskodas) {
        this.asmenskodas = asmenskodas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public LocalDate getDirbanuo() {
        return dirbanuo;
    }

    public void setDirbanuo(LocalDate dirbanuo) {
        this.dirbanuo = dirbanuo;
    }

    public LocalDate getGimimometai() {
        return gimimometai;
    }

    public void setGimimometai(LocalDate gimimometai) {
        this.gimimometai = gimimometai;
    }

    public String getPareigos() {
        return pareigos;
    }

    public void setPareigos(String pareigos) {
        this.pareigos = pareigos;
    }

    public String getSkyriusPavadinimas() {
        return skyriusPavadinimas;
    }

    public void setSkyriusPavadinimas(String skyriusPavadinimas) {
        this.skyriusPavadinimas = skyriusPavadinimas;
    }

    public Integer getProjektasId() {
        return projektasId;
    }

    public void setProjektasId(Integer projektasId) {
        this.projektasId = projektasId;
    }
}
