package com.example.primer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class mysqlconnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/war","root","2280");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<weapons> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<weapons> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from weapons");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new weapons(rs.getInt("id_weapon"), rs.getInt("id_pavilion"), rs.getString("name_weapon"), rs.getInt("year_weapon"), rs.getInt("kalibr_weapon")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<pavilions> getDataPavilions(){
        Connection conn = ConnectDb();
        ObservableList<pavilions> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from pavilions");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new pavilions(rs.getInt("id_pavilion"),  rs.getString("name_pavilion")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
