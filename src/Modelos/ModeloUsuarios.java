/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author edupu
 */
public class ModeloUsuarios {

    public static String modeloUsser(String nombre, String apellido, String cedula) {
        return nombre.substring(0, 1).toLowerCase() + apellido.toLowerCase() + cedula.substring(8, 10);
    }

    public static String modeloCorreo(String nombre, String apellido) {
        return nombre.substring(0, 1).toLowerCase() + apellido.toLowerCase() + "@uta.edu.ec";
    }

    public static String modelomayus(String palabra) {
        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1);
    }
}
