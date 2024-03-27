package org.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Projektas {
    private int id;
    private String pavadinimas;
    private ArrayList<Darbuotojas> darbuotojuSarasas;

    public Projektas(int id, String pavadinimas, ArrayList<Darbuotojas> darbuotojuSarasas) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.darbuotojuSarasas = darbuotojuSarasas;
    }

    public Projektas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public void printProjektas() {
        System.out.printf("%n=%s=%n", this.getPavadinimas());
        printDarbuotojaiProjekte();
    }

    public void printDarbuotojaiProjekte() {
        int rowNum = 1;
        for (Darbuotojas d : this.getDarbuotojuSarasas()) {
            System.out.printf("%d. %s", rowNum++, d.toString());
        }
    }

    public static void insertProjektasFromFileToDb(Connection conn, String path) throws IOException, SQLException {
        List<String> lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
        conn.setAutoCommit(false);
        try (PreparedStatement prep = conn.prepareStatement("INSERT INTO Projektas (Pavadinimas) VALUES (?);")) {
            for (String s : lines) {
                prep.setString(1, s);
                prep.execute();
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projektas projektas)) return false;
        return Objects.equals(getPavadinimas(), projektas.getPavadinimas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPavadinimas());
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
