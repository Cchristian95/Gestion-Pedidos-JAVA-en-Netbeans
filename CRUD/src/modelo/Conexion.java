/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.*;

/**
 *
 * @author calva
 */
public class Conexion {
    //credenciales o variables de conexion
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/productos";

    Connection conn;
    
    public Connection conex(){
        try {
            Class.forName(driver);
            conn = (Connection)DriverManager.getConnection(url, user, password);
            if (conn != null){
                System.out.println("Conexion Exitosa");
            }
        } catch (Exception e) {
            System.out.println("Error de Conexion" + e.getMessage());
        }
        return conn;
    }

}
