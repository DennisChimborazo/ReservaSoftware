/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Repositorio.Conexiones;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import reservasoftware.Feriados;
import reservasoftware.Sounds;

/**
 *
 * @author Dalex
 */
public class VisHorario extends javax.swing.JInternalFrame {

    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    public JMenuItem jitmReserva = new JMenuItem("Reservar");
    public JMenuItem jitmEliminarReserva = new JMenuItem(" Eliminar");
    public JMenuItem jitmModfificarReserva = new JMenuItem(" Modificar reserva");
    LinkedList<Feriados> listaFeriados = new LinkedList();
    VisPrincipal visPrin;

    /**
     * Creates new form VisHorarios
     */
    public VisHorario() {
        initComponents();
        asignarFechaActual();
        cargarFeriados();
        Font font = new Font("Lucida fax", Font.PLAIN, 16); // Por ejemplo, Arial, negrita, tamaño 16
        this.jitmReserva.setFont(font);
        this.jitmEliminarReserva.setFont(font);
        this.jitmModfificarReserva.setFont(font);
        this.jppmMenu.add(jitmReserva);
        this.jppmMenu.add(jitmModfificarReserva);
        this.jppmMenu.add(jitmEliminarReserva);
        this.jtblHorarios.setComponentPopupMenu(jppmMenu);
        this.jPanel1.setBackground(Color.WHITE);
        accionJitmReserva();
        jitmModfificarReserva();
        jitmEliminarReserva();
        cargarcomboEfidicios();
        seleccionarFecha();
    }

    public void LimpiarTabla() {
        for (int i = 0; i < this.jtblHorarios.getRowCount(); i++) {
            for (int j = 1; j < this.jtblHorarios.getColumnCount(); j++) {
                this.jtblHorarios.setValueAt(null, i, j);
            }
        }
    }

    private void seleccionarFecha() {
        jcnlCalendar.addPropertyChangeListener((PropertyChangeEvent evt) -> {

            LimpiarTablaReserva();
            String fechaVerificacion = this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime());
            int indexSemana = indiceSemana(fechaVerificacion);
            cargarFeriadosTabla();
            cargarReservas(indexSemana);
        });
    }

    public void LimpiarTablaReserva() {
        for (int i = 0; i < this.jtblHorarios.getRowCount(); i++) {
            for (int j = 1; j < this.jtblHorarios.getColumnCount(); j++) {
                String valor = String.valueOf(jtblHorarios.getValueAt(i, j));
                if (valor.contains("reserva")) {
                    this.jtblHorarios.setValueAt(null, i, j);
                }
            }
        }

    }

    public void actualizarDatos() {
        int fechaActual = indiceSemana(this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime()));
        cargarReservas(fechaActual);
        cargarFeriadosTabla();
        cargarFeriados();
        cargarFeriadosTabla();
    }

    public void cargarFeriados() {
        this.listaFeriados.clear();
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = "select * from feriados";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String id_fer = rs.getString("id_fer");
                String descripcion = rs.getString("descip");
                String fechaInicio = rs.getString("fecha_inicio");
                String fechaFin = rs.getString("fecha_fin");
                Feriados f = new Feriados(id_fer, fechaInicio, fechaFin, descripcion);
                this.listaFeriados.add(f);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void cargarFeriadosTabla() {
        LimpiarTablaFeriados();
        int indiceSemana = indiceSemana(this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime()));
        for (Feriados f : this.listaFeriados) {
            if (indiceSemana(f.fechaInicio) == indiceSemana || indiceSemana(f.fechaFinal) == indiceSemana) {
                List<LocalDate> fechas = obtenerFechasEnRango(f.fechaInicio, f.fechaFinal);
                fechas = obtenerFechasNumeroSemana(fechas, indiceSemana);
                for (int i = 0; i < fechas.size(); i++) {
                    int indiceDia = indiceDia(String.valueOf(fechas.get(i))) - 1;
                    for (int j = 0; j < this.jtblHorarios.getRowCount(); j++) {
                        if (indiceDia <= 6 && indiceDia != 0) {
                            this.jtblHorarios.setValueAt(f.idFeriado + " Feriado\n" + f.descripcion, j, indiceDia);
                        }
                    }
                }
            }
        }
    }

    private List<LocalDate> obtenerFechasEnRango(String fechaInicio, String fechaFin) {
        LocalDate fechaInicial = LocalDate.parse(fechaInicio);
        LocalDate fechaFinal = LocalDate.parse(fechaFin);
        List<LocalDate> fechas = new ArrayList<>();
        LocalDate fechaActual = fechaInicial;
        while (!fechaActual.isAfter(fechaFinal)) {
            fechas.add(fechaActual);
            fechaActual = fechaActual.plusDays(1);
        }
        return fechas;
    }

    private List<LocalDate> obtenerFechasNumeroSemana(List<LocalDate> fechas, int indiceSemana) {
        List<LocalDate> fechasSemana = new ArrayList<>();

        for (LocalDate fecha : fechas) {
            if (esSemana(fecha, indiceSemana)) {
                fechasSemana.add(fecha);
            }
        }

        return fechasSemana;
    }

    private boolean esSemana(LocalDate fecha, int indiceSemana) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int semanaFecha = fecha.get(weekFields.weekOfWeekBasedYear());
        return semanaFecha == indiceSemana;
    }

    private void LimpiarTablaFeriados() {
        for (int i = 0; i < this.jtblHorarios.getRowCount(); i++) {
            for (int j = 1; j < this.jtblHorarios.getColumnCount(); j++) {
                String valor = String.valueOf(jtblHorarios.getValueAt(i, j));
                if (valor.contains("Feriado")) {
                    this.jtblHorarios.setValueAt(null, i, j);
                }
            }
        }

    }

    private void cargarcomboEfidicios() {
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = "select nom_edi from edificios";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String materia = rs.getString("nom_edi");
                this.jcmbEdificios.addItem(materia);
            }
            cc.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cargarcomboAulasDisponibles(String idEdificio) {
        this.jcmbEspaciosDisponibles.removeAllItems();
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = "SELECT nom_aul FROM aulas JOIN edificios ON aulas.id_edi_per = edificios.id_edi WHERE edificios.nom_edi = '" + idEdificio + "'";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String materia = rs.getString("nom_aul");
                this.jcmbEspaciosDisponibles.addItem(materia);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void borrarReserva() {
        int op = JOptionPane.showConfirmDialog(null, "Desea borrar la reserva", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            try {
                int fila = this.jtblHorarios.getSelectedRow();
                int columna = this.jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(this.jtblHorarios.getValueAt(fila, columna));
                valor = valor.substring(0, 2).trim();
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                String Sql = "delete from reservas where id_reser='" + valor + " ' ";
                PreparedStatement psd = cn.prepareStatement(Sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    Sounds.sonidoOk();
                    JOptionPane.showMessageDialog(null, "Se elimino la reserva del registro");
                    LimpiarTablaReserva();
                    String fechaVerificacion = this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime());
                    int indexSemana = indiceSemana(fechaVerificacion);
                    cargarReservas(indexSemana);
                }
            } catch (SQLException ex) {
                Sounds.sonidoError();
                JOptionPane.showMessageDialog(null, "Verifique los datos que desea borrar", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void borrarFerado() {
        int op = JOptionPane.showConfirmDialog(null, "Desea borrar Feriado", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            try {
                int fila = this.jtblHorarios.getSelectedRow();
                int columna = this.jtblHorarios.getSelectedColumn();
                String valor[] = String.valueOf(this.jtblHorarios.getValueAt(fila, columna)).split(" ");
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                String Sql = "delete from feriados where id_fer='" + valor[0] + " ' ";
                PreparedStatement psd = cn.prepareStatement(Sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    Sounds.sonidoOk();
                    JOptionPane.showMessageDialog(null, "Se elimino la feriado del registro");
                    cargarFeriados();
                    cargarFeriadosTabla();

                }
            } catch (SQLException ex) {
                Sounds.sonidoError();
                JOptionPane.showMessageDialog(null, "Verifique los datos que desea borrar", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void cargarReservas(int indice) {
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            //String sql = "SELECT reservas.*,personas.nom_per AS nombre_persona FROM reservas JOIN aulas ON reservas.id_lab_reser = aulas.id_aul JOIN personas ON reservas.id_per_reserv = personas.ced_per WHERE aulas.nom_aul = '" + this.jcmbEspaciosDisponibles.getSelectedItem().toString() + "'";
            String sql = "SELECT reservas.*, personas.nom_per AS nombre_persona FROM reservas JOIN aulas ON reservas.id_lab_reser = aulas.id_aul JOIN personas ON (reservas.id_per_reserv = personas.ced_per OR reservas.id_per_reserv = personas.id_per) WHERE aulas.nom_aul = '" + this.jcmbEspaciosDisponibles.getSelectedItem().toString() + "'";

            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String profe = rs.getString("nombre_persona");
                String id_reser = rs.getString("id_reser");
                int horaInicio = Integer.parseInt(rs.getString("hor_reserv"));
                int horaFin = Integer.parseInt(rs.getString("hora_fin_reserv"));
                String dia = rs.getString("fecha_reserv");
                int horas = horasAsignadas(horaInicio, horaFin);
                int indicehorario = horaInicio(horaInicio) - 7;
                int indexSemana = indiceSemana(dia);
                if (indice == indexSemana) {
                    for (int i = 0; i < horas; i++) {
                        this.jtblHorarios.setValueAt(id_reser + "  reserva\n" + profe, (indicehorario + i), indiceDia(dia) - 1);
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public ArrayList cargarReservasindividual() {
        ArrayList<String> datos = new ArrayList();
        int fila = this.jtblHorarios.getSelectedRow();
        int columna = this.jtblHorarios.getSelectedColumn();
        String valor = String.valueOf(this.jtblHorarios.getValueAt(fila, columna));
        valor = valor.substring(0, 2).trim();
        System.out.println("valooor " + valor);
        try {
            Repositorio.Conexiones cn = new Repositorio.Conexiones();
            Connection cc = cn.conectar();
            String sql = "select reservas.*, personas.nom_per, personas.ape_per FROM reservas JOIN personas ON reservas.id_per_reserv = personas.id_per WHERE reservas.id_reser = '" + valor + " '";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nom_per");
                String apellido = rs.getString("ape_per");
                String descripcion = rs.getString("desc_reser");
                String horaInicio = (rs.getString("hor_reserv"));
                String horaFin = (rs.getString("hora_fin_reserv"));
                datos.add(nombre);
                datos.add(apellido);
                datos.add(descripcion);
                datos.add(horaInicio);
                datos.add(horaFin);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return datos;
    }

    private void cargarTabla() {
        LimpiarTabla();
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = " SELECT horarios.* , materias.nombre AS nombre_materia, personas.nom_per AS nombre_persona, personas.ape_per AS apellido_persona from horarios JOIN aulas ON horarios.id_aul = aulas.id_aul JOIN materias ON horarios.id_materia = materias.id_materia JOIN personas ON materias.id_per = personas.id_per WHERE aulas.nom_aul = '" + this.jcmbEspaciosDisponibles.getSelectedItem().toString() + "' ";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String idmateria = rs.getString("nombre_materia");
                String apellidoProfesor = rs.getString("apellido_persona");
                String nombreProfesor = rs.getString("nombre_persona");
                int horaInicio = Integer.parseInt(rs.getString("hora_inicio"));
                int horaFin = Integer.parseInt(rs.getString("hora_fin"));
                int dia = Integer.parseInt(rs.getString("id_dia"));
                int horas = horasAsignadas(horaInicio, horaFin);
                int indicehorario = horaInicio(horaInicio) - 7;
                for (int i = 0; i < horas; i++) {
                    this.jtblHorarios.setValueAt(idmateria + "\n" + apellidoProfesor + " " + nombreProfesor, (indicehorario + i), dia);

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void accionJitmReserva() {
        this.jitmReserva.addActionListener((ActionEvent e) -> {
            if (jtblHorarios.getSelectedRow() == -1) {
                Sounds.sonidoError();
                JOptionPane.showMessageDialog(null, "Porfavor seleccione de \nde manera adecuada", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);

            } else {
                if (jtblHorarios.getSelectedColumn() != 0) {
                    int filHora = jtblHorarios.getSelectedRow();
                    int columDia = jtblHorarios.getSelectedColumn();
                    String valor = String.valueOf(jtblHorarios.getValueAt(filHora, columDia));
                    if (!valor.contains("reserva")) {
                        if (valor.contains("null")) {
                            verificacionDatosReserva();
                        } else {
                            Sounds.sonidoError();
                            JOptionPane.showMessageDialog(null, "La fecha seleccionada corresponde \no puede reservarla", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        Sounds.sonidoError();
                        JOptionPane.showMessageDialog(null, "No puede reversar esta fecha se \nencuentra ya reservada", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    Sounds.sonidoError();
                    JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }

    private void jitmModfificarReserva() {
        this.jitmModfificarReserva.addActionListener((ActionEvent e) -> {
            if (jtblHorarios.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Porfavor seleccione de \nde manera adecuada");
            } else {
                if (jtblHorarios.getSelectedColumn() != 0) {
                    String valordia = fechaAutomaticaReserva();
                    if (verificacionFechaValida(valordia)) {
                        int filHora = jtblHorarios.getSelectedRow();
                        int columDia = jtblHorarios.getSelectedColumn();
                        String valor = String.valueOf(jtblHorarios.getValueAt(filHora, columDia));
                        if (valor.contains("reserva")) {
                            int fila = jtblHorarios.getSelectedRow();
                            int columna = jtblHorarios.getSelectedColumn();
                            String[] id = String.valueOf(jtblHorarios.getValueAt(fila, columna)).split(" ");
                            VisReserva vr = new VisReserva(this, this.jcmbEspaciosDisponibles.getSelectedItem().toString(), jtblHorarios.getSelectedRow() + 7, horasDisponibles(), valor, id[0], cargarReservasindividual());
                            vr.setVisible(true);
                        } else {
                            Sounds.sonidoError();
                            JOptionPane.showMessageDialog(null, "La fecha selecionada \nno se puede modificar", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        Sounds.sonidoError();
                        JOptionPane.showMessageDialog(null, "No puede modificar la reserva corespondiente\na esa fecha", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Sounds.sonidoError();
                    JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private void jitmEliminarReserva() {
        this.jitmEliminarReserva.addActionListener((ActionEvent e) -> {
            if (jtblHorarios.getSelectedRow() == -1) {
                Sounds.sonidoError();
                JOptionPane.showMessageDialog(null, "Porfavor seleccione de \nde manera adecuada", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
            } else {
                if (jtblHorarios.getSelectedColumn() != 0) {
                    String valordia = fechaAutomaticaReserva();
                    if (verificacionFechaValida(valordia)) {
                        int filHora = jtblHorarios.getSelectedRow();
                        int columDia = jtblHorarios.getSelectedColumn();
                        String valor = String.valueOf(jtblHorarios.getValueAt(filHora, columDia));

                        if (valor.contains("reserva") || valor.contains("Feriado")) {
                            if (valor.contains("reserva")) {
                                borrarReserva();

                            } else {
                                borrarFerado();
                            }

                        } else {
                            Sounds.sonidoError();
                            JOptionPane.showMessageDialog(null, "La fecha selecionada \nno se puede elinimar", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        Sounds.sonidoError();
                        JOptionPane.showMessageDialog(null, "No puede borrar la reserva corespondiente\na esa fecha", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Sounds.sonidoError();
                    JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private void asignarFechaActual() {
        Date fecha = new Date();
        this.jcnlCalendar.setDate(fecha);

    }

    private int indiceDia(String fecha) {
        int numDia = 0;
        try {
            Date date = formatoFecha.parse(fecha);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            numDia = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return numDia;
    }

    public int indiceSemana(String fecha) {
        int semana = 0;
        try {

            Date date = this.formatoFecha.parse(fecha);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            semana = calendar.get(Calendar.WEEK_OF_YEAR);

        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return semana;

    }

    private int horasAsignadas(int horaInicio, int horaFin) {
        int hora = 0;
        if (horaInicio <= 12 && horaFin >= 14) {
            hora = ((horaFin - horaInicio) - 1);
        }
        if (horaInicio <= 12 && horaFin <= 13 || horaInicio >= 14 && horaFin >= 14) {
            hora = horaFin - horaInicio;

        }

        return hora;
    }

    private int horaInicio(int horaInicio) {
        if (horaInicio >= 14) {
            horaInicio = (horaInicio - 1);
        }
        return horaInicio;
    }

    private int horasDisponibles() {
        int cont = 0;
        int fila = this.jtblHorarios.getSelectedRow();
        int columna = this.jtblHorarios.getSelectedColumn();
        if (fila != -1 && columna != -1) {
            while (fila < this.jtblHorarios.getRowCount()) {
                Object valor = this.jtblHorarios.getValueAt(fila, columna);
                if (valor == null || valor.toString().isEmpty()) {
                    cont++;
                    fila++;
                } else {
                    break;
                }
            }
        }

        return cont;
    }

    private boolean verificacionFechaValida(String fecha) {
        Date fechaActual = new Date();
        Date fechaOtra = null;
        try {
            fechaOtra = formatoFecha.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (fechaOtra != null && !fechaOtra.before(fechaActual)
                || fechaOtra.toString().substring(0, 10).equals(fechaActual.toString().substring(0, 10))) {
            return true;
        } else {
            return false;
        }

    }

    public boolean verificacionDatosReserva() {
        String valor = fechaAutomaticaReserva();
        if (verificacionFechaValida(valor)) {
            int verificacionDiaValido = indiceDia(valor);
            if (verificacionDiaValido == 1) {
                JOptionPane.showMessageDialog(null, "No se puede reservar en fines de semana");
                return false;

            } else {
                Date fechaActual = new Date();
                if (fechaAutomaticaReserva().equalsIgnoreCase(this.formatoFecha.format(fechaActual))) {
                    if (!verificacionHora()) {
                        VisReserva vr = new VisReserva(this, this.jcmbEspaciosDisponibles.getSelectedItem().toString(), this.jtblHorarios.getSelectedRow() + 7, horasDisponibles(), fechaAutomaticaReserva(), null, null);
                        vr.consumirVistaPrincipal(this.visPrin);
                        vr.setVisible(true);
                        return true;
                    } else {
                        Sounds.sonidoError();
                        JOptionPane.showMessageDialog(null, "No puede reservar esta hora seleccionda", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    VisReserva vr = new VisReserva(this, this.jcmbEspaciosDisponibles.getSelectedItem().toString(), this.jtblHorarios.getSelectedRow() + 7, horasDisponibles(), fechaAutomaticaReserva(), null, null);
                    vr.consumirVistaPrincipal(this.visPrin);
                    vr.setVisible(true);
                    return true;
                }
            }
        } else {
            Sounds.sonidoError();
            JOptionPane.showMessageDialog(null, "No puede reservar en una fecha \nanterior a la fecha actual", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public String fechaAutomaticaReserva() {
        int semana = indiceSemana(this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime()));
        int indiceIngreso = this.jtblHorarios.getSelectedColumn();
        LocalDate primerDiaSemana = LocalDate.of(2024, 1, 1)
                .with(TemporalAdjusters.firstDayOfYear())
                .plusWeeks(semana - 1);
        LocalDate fechaCalculada = primerDiaSemana.with(DayOfWeek.of(indiceIngreso));
        return fechaCalculada.toString();
    }

    public boolean verificacionHora() {
        int horaTabla = this.jtblHorarios.getSelectedRow() + 7;
        String cadenaHoraTabla = String.valueOf(horaTabla) + ":00";
        LocalDateTime tiempoActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaObjetivo = LocalTime.parse(cadenaHoraTabla, formatter);
        LocalTime horaActual = tiempoActual.toLocalTime();
        if (horaActual.isAfter(horaObjetivo)) {
            return true;
        } else if (horaActual.isBefore(horaObjetivo)) {
            return false;
        } else {
            return true;
        }
    }

    public void consumirVistaPrincipal(VisPrincipal vp) {
        this.visPrin = vp;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jppmMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblHorarios = new ComponentesPropios.utcJTable();
        jcnlCalendar = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jcmbEspaciosDisponibles = new javax.swing.JComboBox<>();
        jcmbEdificios = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setVisible(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jLabel1.setText("Fecha");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jScrollPane2.setViewportView(jtblHorarios);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 900, 430));

        jcnlCalendar.setFont(new java.awt.Font("Microsoft Uighur", 1, 18)); // NOI18N
        jPanel1.add(jcnlCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 160, 30));

        jLabel3.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jLabel3.setText("Edificio:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jcmbEspaciosDisponibles.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jcmbEspaciosDisponibles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbEspaciosDisponiblesItemStateChanged(evt);
            }
        });
        jPanel1.add(jcmbEspaciosDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 260, 30));

        jcmbEdificios.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jcmbEdificios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbEdificiosItemStateChanged(evt);
            }
        });
        jPanel1.add(jcmbEdificios, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 210, 30));

        jLabel4.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jLabel4.setText("Aula:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbEdificiosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbEdificiosItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            cargarcomboAulasDisponibles(this.jcmbEdificios.getSelectedItem().toString());
            cargarFeriadosTabla();
        }

    }//GEN-LAST:event_jcmbEdificiosItemStateChanged

    private void jcmbEspaciosDisponiblesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbEspaciosDisponiblesItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
            String fechaVerificacion = this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime());
            int indexSemana = indiceSemana(fechaVerificacion);
            cargarReservas(indexSemana);
            cargarFeriadosTabla();
            cargarFeriadosTabla();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbEspaciosDisponiblesItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisHorario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcmbEdificios;
    public javax.swing.JComboBox<String> jcmbEspaciosDisponibles;
    private com.toedter.calendar.JDateChooser jcnlCalendar;
    private javax.swing.JPopupMenu jppmMenu;
    public ComponentesPropios.utcJTable jtblHorarios;
    // End of variables declaration//GEN-END:variables
}
