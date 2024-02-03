package com.example.primer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VyborController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_redact_pavilion;

    @FXML
    private Button button_redact_weapon;

    @FXML
    private Button mainBrn_Statistika;

    @FXML
    private Button mainBtn_Redact;

    @FXML
    private Button mainBtn_main;

    @FXML
    private Button mainBtn_zapiska;

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
        button_redact_weapon.setOnAction(event -> {
            button_redact_weapon.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));

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
        button_redact_pavilion.setOnAction(event -> {
            button_redact_pavilion.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("pavilions.fxml"));

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
    }

}

