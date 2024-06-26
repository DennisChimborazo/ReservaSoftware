/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edupu
 */
public class Conexiones {

    Connection conectar;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                 conectar = DriverManager.getConnection("jdbc:mysql://localhost:33060/proyectot", "root", "mysql");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return conectar;

    }

}
