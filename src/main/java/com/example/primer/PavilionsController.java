package com.example.primer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;

public class PavilionsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back_button;

    @FXML
    private TableColumn<pavilions, Integer> col_id_pavilion;

    @FXML
    private TableColumn<pavilions, String> col_name_pavilion;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<pavilions> table_pavilions;

    @FXML
    private TextField txt_id_pavilion;

    @FXML
    private TextField txt_name_pavilion;

    ObservableList<pavilions> listM;
    ObservableList<pavilions> datalistPavilion;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void Add_Pavilions(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into pavilions (id_pavilion,name_pavilion)values(?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_pavilion.getText());
            pst.setString(2, txt_name_pavilion.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Добавлено");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void Delete(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from pavilions where id_pavilion = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_pavilion.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void Edit() {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id_pavilion.getText();
            String value2 = txt_name_pavilion.getText();
            String sql = "update pavilions set id_pavilion= '" + value1 + "',name_pavilion= '" + value2 + "' where id_pavilion='" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Обновлено");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


        @FXML
        void getSelected (MouseEvent event){
            index = table_pavilions.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            txt_id_pavilion.setText(col_id_pavilion.getCellData(index).toString());
            txt_name_pavilion.setText(col_name_pavilion.getCellData(index).toString());

        }

        @FXML
        public void UpdateTable () {
            col_id_pavilion.setCellValueFactory(new PropertyValueFactory<pavilions, Integer>("id_pavilion"));
            col_name_pavilion.setCellValueFactory(new PropertyValueFactory<pavilions, String>("name_pavilion"));

            listM = mysqlconnect.getDataPavilions();
            table_pavilions.setItems(listM);
        }

        @FXML
        void search_user () {
            col_id_pavilion.setCellValueFactory(new PropertyValueFactory<pavilions, Integer>("id_pavilion"));
            col_name_pavilion.setCellValueFactory(new PropertyValueFactory<pavilions, String>("name_pavilion"));

            datalistPavilion = mysqlconnect.getDataPavilions();
            table_pavilions.setItems(datalistPavilion);
            FilteredList<pavilions> filteredData = new FilteredList<>(datalistPavilion, b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(person.getId_pavilion()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (person.getName_pavilion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });
            SortedList<pavilions> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_pavilions.comparatorProperty());
            table_pavilions.setItems(sortedData);
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            Back_button.setOnAction(event -> {
                Stage currentStage = (Stage) Back_button.getScene().getWindow();
                currentStage.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("vybor.fxml"));

                try {
                    Parent root = loader.load();
                    Stage previousStage = new Stage();
                    previousStage.setScene(new Scene(root));
                    previousStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            UpdateTable();
            search_user();
        }
    }

