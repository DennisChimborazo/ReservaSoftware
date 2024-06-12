/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author edupu
 */
public class Docente extends Persona {

    private String usuario;
    private String correo;
    private String contrasenia;
    private String tipoUsuario;

    public Docente(String cedula, String nombre, String apellido, Date fecha_nac, String telefono, String direccion, String usuario, String correo, String contrasenia, String tipoUsuario) {
        super(cedula, nombre, apellido, fecha_nac, telefono, direccion);
        this.usuario = usuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }

    public Docente(String cedula, String nombre, String apellido, String telefono, String usuario, String correo) {
        super(cedula, nombre, apellido, telefono);
        this.usuario = usuario;
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
