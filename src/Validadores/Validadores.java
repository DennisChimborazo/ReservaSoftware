/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validadores;

import Repositorio.Conexion;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edupu
 */
public class Validadores {

    public static boolean isValidCedula(String cedula) {
        // Verificar que la cédula tenga 10 dígitos
        if (cedula.length() != 10) {
            return false;
        }
        // Convertir cédula a arreglo de enteros
        int[] digits = new int[10];
        try {
            for (int i = 0; i < 10; i++) {
                digits[i] = Integer.parseInt(String.valueOf(cedula.charAt(i)));
            }
        } catch (NumberFormatException e) {
            return false;
        }
        // Verificar el rango de la región (primeros dos dígitos)
        int region = digits[0] * 10 + digits[1];
        if (region < 1 || region > 24) {
            return false;
        }
        // Aplicar el algoritmo de validación
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;

        for (int i = 0; i < 9; i++) {
            int producto = digits[i] * coeficientes[i];
            if (producto >= 10) {
                producto -= 9;
            }
            suma += producto;
        }
        int digitoVerificadorCalculado = (10 - (suma % 10)) % 10;
        // Comparar el dígito verificador calculado con el último dígito de la cédula
        return digitoVerificadorCalculado == digits[9];
    }

    public static boolean isValidTelefono(String numero) {
        return numero.substring(0, 2).equals("09") && numero.length() == 10;
    }

    public static boolean existeUsuario(String cedula) {
        try {
            String sql = "SELECT COUNT(*) FROM personas WHERE id_per='" + cedula + "'";
            Statement st = Conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Validadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
