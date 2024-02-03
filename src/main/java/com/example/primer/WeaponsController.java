package com.example.primer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;


public class WeaponsController implements Initializable {


    @FXML
    private TableView<weapons> table_weapons;

    @FXML
    private TableColumn<weapons, Integer> col_id_weapon;

    @FXML
    private TableColumn<weapons, Integer> col_id_pavilion;

    @FXML
    private TableColumn<weapons, String> col_name_weapon;

    @FXML
    private TableColumn<weapons, Integer> col_year_weapon;

    @FXML
    private TableColumn<weapons, Integer> col_kalibr_weapon;

    @FXML
    private TextField txt_id_pavilion;

    @FXML
    private TextField txt_name_weapon;

    @FXML
    private TextField txt_year_weapon;

    @FXML
    private TextField txt_kalibr_weapon;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField filterField;

    @FXML
    private Button Back_button;


    ObservableList<weapons> listM;
    ObservableList<weapons> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_weapons (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into weapons (id_pavilion,name_weapon,year_weapon,kalibr_weapon)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_pavilion.getText());
            pst.setString(2, txt_name_weapon.getText());
            pst.setString(3, txt_year_weapon.getText());
            pst.setString(4, txt_kalibr_weapon.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Добавлено");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void getSelected (MouseEvent event){
        index = table_weapons.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id_weapon.getCellData(index).toString());
        txt_id_pavilion.setText(col_id_pavilion.getCellData(index).toString());
        txt_name_weapon.setText(col_name_weapon.getCellData(index).toString());
        txt_year_weapon.setText(col_year_weapon.getCellData(index).toString());
        txt_kalibr_weapon.setText(col_kalibr_weapon.getCellData(index).toString());

    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_id_pavilion.getText();
            String value3 = txt_name_weapon.getText();
            String value4 = txt_year_weapon.getText();
            String value5 = txt_kalibr_weapon.getText();
            String sql = "update weapons set id_weapon= '"+value1+"',id_pavilion= '"+value2+"',name_weapon= '"+
                    value3+"',year_weapon= '"+value4+"',kalibr_weapon= '"+value5+"' where id_weapon='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Обновлено");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from users where user_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void UpdateTable(){
        col_id_weapon.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("id_weapon"));
        col_id_pavilion.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("id_pavilion"));
        col_name_weapon.setCellValueFactory(new PropertyValueFactory<weapons,String>("name_weapon"));
        col_year_weapon.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("year_weapon"));
        col_kalibr_weapon.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("kalibr_weapon"));

        listM = mysqlconnect.getDatausers();
        table_weapons.setItems(listM);
    }




    @FXML
    void search_user() {
        col_id_weapon.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("id_weapon"));
        col_id_pavilion.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("id_pavilion"));
        col_name_weapon.setCellValueFactory(new PropertyValueFactory<weapons,String>("name_weapon"));
        col_year_weapon.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("year_weapon"));
        col_kalibr_weapon.setCellValueFactory(new PropertyValueFactory<weapons,Integer>("kalibr_weapon"));

        dataList = mysqlconnect.getDatausers();
        table_weapons.setItems(dataList);
        FilteredList<weapons> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(person.getId_pavilion()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getName_weapon().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(person.getYear_weapon()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(person.getKalibr_weapon()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<weapons> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_weapons.comparatorProperty());
        table_weapons.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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