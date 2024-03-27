package org.example;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imone", "root", "******")) {
            Imone imoneFromDb = new Imone(connection);
            imoneFromDb.printProjektaiWithDarbuotojai();
            Projektas.insertProjektasFromFileToDb(connection, "src/main/java/org/example/import_projektas.txt");
            Darbuotojas.insertDarbuotjas(connection, new Darbuotojas("48709141175", "Ona", "Onataite", LocalDate.parse("2012-06-15"), LocalDate.parse("1987-09-14"), "Projektų vadovė", "Java", 3));
            Darbuotojas.updateDarbuotojoProjektas(connection, 2, "48709141175");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}