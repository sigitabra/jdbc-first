package org.example;

import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        Darbuotojas insertDarbuotjas = new Darbuotojas("48709141175", "Ona", "Onataite", LocalDate.parse("2012-06-15"), LocalDate.parse("1987-09-14"), "Projektų vadovė", "Java", 3);


        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imone",
                "root",
                "*******"
        )) {

            getAllDarbuotojas(connection);
            getDarbuotojasByProjektas(connection);
            updateDarbuotojoProjektas(connection, 2, "48709141175");
            connection.setAutoCommit(false);
            try {
                insertDarbuotjas(connection, insertDarbuotjas);
                connection.commit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }


    public static void getAllDarbuotojas(Connection conn) {
        ResultSet rs;
        try (Statement state = conn.createStatement()) {
            rs = state.executeQuery("select * from darbuotojas");
            while (rs.next()) {
                System.out.printf("%s %s%n", rs.getString("vardas"), rs.getString("pavarde"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void getDarbuotojasByProjektas(Connection conn) {
        ResultSet rs;
        try (Statement state = conn.createStatement()) {
            rs = state.executeQuery("select * from projektas");
            while (rs.next()) {
                System.out.printf("%n=%s=%n", rs.getString("Pavadinimas"));

                PreparedStatement prep = conn.prepareStatement("select * from darbuotojas where projektas_id=?;");
                prep.setInt(1, rs.getInt("ID"));
                ResultSet inRs = prep.executeQuery();
                int rowNum = 1;
                while (inRs.next()) {
                    System.out.printf("%d. %s %s%n", rowNum++, inRs.getString("vardas"), inRs.getString("pavarde"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insertDarbuotjas(Connection conn, Darbuotojas darbuotojas) throws SQLException {
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
        } catch (Exception e) {
            conn.rollback();
            System.out.println(e.getMessage());
        }
    }

    public static void updateDarbuotojoProjektas(Connection conn, int naujasProjektas, String asmenskodas) throws SQLException {
        try (PreparedStatement prep = conn.prepareStatement("UPDATE darbuotojas SET pojektas_id = ? WHERE asmenskodas=?;")) {
            prep.setInt(1, naujasProjektas);
            prep.setString(2, asmenskodas);
            prep.execute();
        } catch (Exception e) {
            conn.rollback();
            System.out.println(e.getMessage());
        }
    }
}