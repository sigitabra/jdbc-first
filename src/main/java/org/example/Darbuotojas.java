package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

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

    public Darbuotojas(String asmenskodas) {
        this.asmenskodas = asmenskodas;
    }

    public static void insertDarbuotjas(Connection conn, Darbuotojas darbuotojas) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement prep = conn.prepareStatement("INSERT INTO darbuotojas VALUES (?, ?, ?, ?, ?, ?, ?, ?);")) {
            prep.setString(1, darbuotojas.getAsmenskodas());
            prep.setString(2, darbuotojas.getVardas());
            prep.setString(3, darbuotojas.getPavarde());
            prep.setDate(4, Date.valueOf(darbuotojas.getDirbanuo()));
            prep.setDate(5, Date.valueOf(darbuotojas.getGimimometai()));
            prep.setString(6, darbuotojas.getPareigos());
            prep.setString(7, darbuotojas.getSkyriusPavadinimas());
            prep.setInt(8, darbuotojas.getProjektasId());
            prep.execute();
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static void updateDarbuotojoProjektas(Connection conn, int naujasProjektas, String asmenskodas) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement prep = conn.prepareStatement("UPDATE darbuotojas SET projektas_id = ? WHERE asmenskodas=?;")) {
            prep.setInt(1, naujasProjektas);
            prep.setString(2, asmenskodas);
            prep.execute();
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public String toString() {
        return vardas + " " + pavarde + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Darbuotojas that)) return false;
        return Objects.equals(getAsmenskodas(), that.getAsmenskodas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAsmenskodas());
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
