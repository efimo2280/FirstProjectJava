package com.example.primer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ZapiskaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_zapiska;

    @FXML
    private TableColumn<?, ?> idpavilion_col;

    @FXML
    private Button mainBrn_Statistika;

    @FXML
    private Button mainBtn_Redact;

    @FXML
    private Button mainBtn_main;

    @FXML
    private Button mainBtn_zapiska;

    @FXML
    private TableView<?> tableView_zapiska;

    @FXML
    private TextField textField_family;

    @FXML
    private TextField textField_idPavilion;

    @FXML
    private TextField textField_name;

    @FXML
    private TextField textField_year;

    @FXML
    void initialize() {
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
        button_zapiska.setOnAction(event -> {
            String data1 = textField_family.getText();
            String data2 = textField_name.getText();
            String data3 = textField_year.getText();
            String data4 = textField_idPavilion.getText();

            // Создайте подключение к базе данных
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/war","root","2280")) {
                // Создайте PreparedStatement для выполнения SQL-запроса
                String sql = "INSERT INTO prisoners (id_prisoners, id_pavilion, last_name_prisoner, first_name_prisoner, year_prisoner) VALUES (NULL, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);

                // Установите параметры запроса
                statement.setString(1, data4);
                statement.setString(2, data1);
                statement.setString(3, data2);
                statement.setString(4, data3);

                statement.executeUpdate();

                textField_year.clear();
                textField_name.clear();
                textField_family.clear();
                textField_idPavilion.clear();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}


