/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservasoftware;

/**
 *
 * @author Dalex
 */
public class Feriados {
    
   public String idFeriado;
   public String fechaInicio;
   public String fechaFinal;
   public int[] vecFechaInicio;
   public int[] vecFechaFinal;
   public String descripcion;

    public Feriados(String idFeriado, String fechaInicio, String fechaFinal, String descripcion) {
        this.idFeriado = idFeriado;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.descripcion = descripcion;
        this.vecFechaInicio = tranformarFechas(fechaInicio.split("-"));
        this.vecFechaFinal = tranformarFechas(fechaFinal.split("-"));
    }

    private int[] tranformarFechas(String fecha[]) {
        int[] fechaTransformada = new int[fecha.length];
        for (int i = 0; i < fecha.length; i++) {
            fechaTransformada[i] = Integer.parseInt(fecha[i]);
        }
        return fechaTransformada;
    }

}
