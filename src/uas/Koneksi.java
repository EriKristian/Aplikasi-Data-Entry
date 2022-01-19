/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    public static Connection koneksi;
    public Statement st;
    private static com.mysql.jdbc.Connection con;

    public Connection bukainkoneksi() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mobil?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (Exception e) {
            System.out.println("Koneksi Error Bambank" + e);
        }
        return koneksi;
    }

    public void koneksi() {
        try {
            bukainkoneksi();
            st = koneksi.createStatement();
        } catch (SQLException e) {
            System.out.println("Koneksi Error Bambank!" + e);
        }
    }

    
    public void simpanData(String sql) {
        try {
            koneksi();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
