package com.example.primer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StatistikaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Integer> list_pavilions;

    @FXML
    private ListView<Integer> list_prisoners;

    @FXML
    private ListView<Integer> list_weapons;

    @FXML
    private ListView<Integer> list_zarabotok;

    @FXML
    private Button mainBrn_Statistika;

    @FXML
    private Button mainBtn_Redact;

    @FXML
    private Button mainBtn_main;

    @FXML
    private Button mainBtn_zapiska;

    private int getRowCountFromDatabase() {
        int rowCount = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/war","root","2280");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM weapons")) {

            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowCount;
    }
    private int getRowCountPrisonersFromDatabase() {
        int rowCountPrisoners = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/war","root","2280");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM prisoners")) {

            if (resultSet.next()) {
                rowCountPrisoners = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowCountPrisoners;
    }

    private int getRowCountZarabotokFromDatabase() {
        int rowCountZarabotok = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/war","root","2280");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM prisoners")) {

            if (resultSet.next()) {
                rowCountZarabotok = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowCountZarabotok * 500;
    }

    private int getRowCountPavilionsFromDatabase() {
        int rowCountPavilions = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/war","root","2280");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM pavilions")) {

            if (resultSet.next()) {
                rowCountPavilions = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowCountPavilions;
    }





    @FXML
    void initialize() {
        mainBtn_main.setOnAction(event -> {
            Stage currentStage = (Stage) mainBtn_main.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main.fxml"));

            try {
                Parent root = loader.load();
                Stage previousStage = new Stage();
                previousStage.setScene(new Scene(root));
                previousStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainBtn_zapiska.setOnAction(event -> {
            mainBtn_zapiska.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("zapiska.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        mainBrn_Statistika.setOnAction(event -> {
            mainBrn_Statistika.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("statistika.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        mainBtn_Redact.setOnAction(event -> {
            mainBtn_Redact.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("vybor.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        int rowCount = getRowCountFromDatabase();
        list_weapons.getItems().add(rowCount);

        int rowCountPrisoners = getRowCountPrisonersFromDatabase();
        list_prisoners.getItems().add(rowCountPrisoners);

        int rowCountPavilions = getRowCountPavilionsFromDatabase();
        list_pavilions.getItems().add(rowCountPavilions);

        int rowCountZarabotok = getRowCountZarabotokFromDatabase();
        list_zarabotok.getItems().add(rowCountZarabotok);
    }

}

