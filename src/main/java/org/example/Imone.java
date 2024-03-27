package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Imone {
    private ArrayList<Darbuotojas> visiDarbuotojai;
    private ArrayList<Projektas> visiProjektai;
    private ArrayList<Skyrius> visiSkyriai;

    public Imone(Connection conn) {
        this.visiDarbuotojai = getALlDarbuotojaiFromDb(conn);
        this.visiProjektai = getAllProjektaiFromDb(conn);
        this.visiSkyriai = getAllSkyriaiFromDb(conn);
    }

    public static ArrayList<Darbuotojas> getALlDarbuotojaiFromDb(Connection conn) {
        ResultSet rs;
        ArrayList<Darbuotojas> result = new ArrayList<>();
        try (Statement state = conn.createStatement()) {
            rs = state.executeQuery("select * from darbuotojas");
            System.out.println("\n'Darbuotojas' read from db: ");
            while (rs.next()) {
                result.add(new Darbuotojas(rs.getString("asmenskodas"),
                        rs.getString("vardas"),
                        rs.getString("pavarde"),
                        rs.getDate("dirbanuo").toLocalDate(),
                        rs.getDate("gimimometai").toLocalDate(),
                        rs.getString("pareigos"),
                        rs.getString("skyrius_pavadinimas"),
                        rs.getInt("projektas_id")));
                System.out.printf("%s %s%n", result.getLast().getVardas(), result.getLast().getPavarde());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static ArrayList<Skyrius> getAllSkyriaiFromDb(Connection conn) {
        ResultSet rs;
        ArrayList<Skyrius> result = new ArrayList<>();
        try (Statement state = conn.createStatement()) {
            rs = state.executeQuery("select * from skyrius");
            System.out.println("\n'Skyrius' read from db: ");
            while (rs.next()) {
                result.add(new Skyrius(rs.getString("PAVADINIMAS"), rs.getString("VADOVAS_ASMENSKODAS")));
                System.out.printf("%s %s%n", result.getLast().getPavadinimas(), result.getLast().getVadovoAsmensKodas());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;

    }

    public static ArrayList<Projektas> getAllProjektaiFromDb(Connection conn) {
        ResultSet rs;
        ArrayList<Projektas> result = new ArrayList<>();
        try (Statement state = conn.createStatement()) {
            rs = state.executeQuery("select * from projektas");
            System.out.println("\n'Projektas' read from db: ");
            while (rs.next()) {
                result.add(new Projektas(rs.getInt("ID"), rs.getString("Pavadinimas"), new ArrayList<>()));
                System.out.printf("%s %s%n", result.getLast().getId(), result.getLast().getPavadinimas());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void printProjektaiWithDarbuotojai() {
        for (Projektas p : getVisiProjektai()) {
            addDarbuotojasToProjektas(p);
            p.printProjektas();
        }
    }


    public void addDarbuotojasToProjektas(Projektas projektas) {
        for (Darbuotojas d : getVisiDarbuotojai()) {
            if (projektas.getId() == d.getProjektasId()) {
                projektas.getDarbuotojuSarasas().add(d);
            }
        }
    }


    public ArrayList<Darbuotojas> getVisiDarbuotojai() {
        return visiDarbuotojai;
    }

    public void setVisiDarbuotojai(ArrayList<Darbuotojas> visiDarbuotojai) {
        this.visiDarbuotojai = visiDarbuotojai;
    }

    public ArrayList<Projektas> getVisiProjektai() {
        return visiProjektai;
    }

    public void setVisiProjektai(ArrayList<Projektas> visiProjektai) {
        this.visiProjektai = visiProjektai;
    }

    public ArrayList<Skyrius> getVisiSkyriai() {
        return visiSkyriai;
    }

    public void setVisiSkyriai(ArrayList<Skyrius> visiSkyriai) {
        this.visiSkyriai = visiSkyriai;
    }
}
