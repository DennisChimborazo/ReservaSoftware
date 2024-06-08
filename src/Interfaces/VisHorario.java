/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Repositorio.Conexion;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Dalex
 */
public class VisHorario extends javax.swing.JInternalFrame {

    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
    public JMenuItem jitmReserva = new JMenuItem("Reservar");
    public JMenuItem jitmEliminarReserva = new JMenuItem(" Eliminar reserva");
    public JMenuItem jitmModfificarReserva = new JMenuItem(" Modificar reserva");

    /**
     * Creates new form VisHorarios
     */
    public VisHorario() {
        initComponents();
        asignarFechaActual();
        Font font = new Font("Lucida fax", Font.PLAIN, 16); // Por ejemplo, Arial, negrita, tamaño 16
        this.jitmReserva.setFont(font);
        this.jitmEliminarReserva.setFont(font);
        this.jitmModfificarReserva.setFont(font);
        this.jppmMenu.add(jitmReserva);
        this.jppmMenu.add(jitmModfificarReserva);
        this.jppmMenu.add(jitmEliminarReserva);
        this.jtblHorarios.setComponentPopupMenu(jppmMenu);
        accionJitmReserva();
        jitmModfificarReserva();
        jitmEliminarReserva();
        seleccionarFecha();
        cargarTabla();

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

    public void acutualizarDatos() {
        int fechaActual = indiceSemana(this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime()));
        cargarReservas(fechaActual);
    }

    public void borrarReserva() {
        int op = JOptionPane.showConfirmDialog(null, "Desea borrar la reserva", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            try {
                int fila = this.jtblHorarios.getSelectedRow();
                int columna = this.jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(this.jtblHorarios.getValueAt(fila, columna));
                valor = valor.substring(0, 1);
                Conexion cc = new Conexion();
                Connection cn = cc.conectar();
                String Sql = "delete from reseva where idhorario='" + valor + " ' ";
                PreparedStatement psd = cn.prepareStatement(Sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se elimino la reserva del registro");
                    LimpiarTablaReserva();
                    String fechaVerificacion = this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime());
                    int indexSemana = indiceSemana(fechaVerificacion);
                    cargarReservas(indexSemana);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Verifique los datos que desea borrar");
            }

        }

    }

    public void cargarReservas(int indice) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "select * from reseva";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String idhorario = rs.getString("idhorario");
                String profe = rs.getString("nombre");
                int horaInicio = Integer.parseInt(rs.getString("horainicio"));
                int horaFin = Integer.parseInt(rs.getString("horafin"));
                String dia = rs.getString("fecha");
                int horas = horasAsignadas(horaInicio, horaFin);
                int indicehorario = horaInicio(horaInicio) - 7;
                int indexSemana = indiceSemana(dia);
                if (indice == indexSemana) {
                    for (int i = 0; i < horas; i++) {
                        this.jtblHorarios.setValueAt(idhorario + ". reserva\n" + profe, (indicehorario + i), indiceDia(dia) - 1);
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
        valor = valor.substring(0, 1);
        try {
            Repositorio.Conexion cn = new Repositorio.Conexion();
            Connection cc = cn.conectar();
            String sql = "select * from reseva where idhorario= '" + valor + " '";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String profe = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String horaInicio = (rs.getString("horainicio"));
                String horaFin = (rs.getString("horafin"));
                datos.add(profe);
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
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "select * from horario";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String materia = rs.getString("materia");
                String profe = rs.getString("profesor");
                int horaInicio = Integer.parseInt(rs.getString("horainicio"));
                int horaFin = Integer.parseInt(rs.getString("horafin"));
                int dia = Integer.parseInt(rs.getString("dia"));
                int horas = horasAsignadas(horaInicio, horaFin);
                int indicehorario = horaInicio(horaInicio) - 7;

                for (int i = 0; i < horas; i++) {
                    this.jtblHorarios.setValueAt(materia + "\n" + profe, (indicehorario + i), dia);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void accionJitmReserva() {

        this.jitmReserva.addActionListener((ActionEvent e) -> {
            if (jtblHorarios.getSelectedColumn() != 0) {
                int filHora = jtblHorarios.getSelectedRow();
                int columDia = jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(jtblHorarios.getValueAt(filHora, columDia));
                if (!valor.contains("reserva")) {
                    if (valor.contains("null")) {
                        verificacionDatosReserva();
                    } else {
                        JOptionPane.showMessageDialog(null, "La fecha seleccionada corresponde \na un jornada laboral");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No puede reversar esta fecha se \nencuentra ya reservada");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido");
            }
        });
    }

    private void jitmModfificarReserva() {
        this.jitmModfificarReserva.addActionListener((ActionEvent e) -> {
            if (jtblHorarios.getSelectedColumn() != 0) {
                int filHora = jtblHorarios.getSelectedRow();
                int columDia = jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(jtblHorarios.getValueAt(filHora, columDia));
                if (valor.contains("reserva")) {
                    int fila = jtblHorarios.getSelectedRow();
                    int columna = jtblHorarios.getSelectedColumn();
                    String id = String.valueOf(jtblHorarios.getValueAt(fila, columna));
                    id = id.substring(0, 1);
                    VisReserva vr = new VisReserva(this, jtblHorarios.getSelectedRow() + 7, horasDisponibles(), valor, id, cargarReservasindividual());
                    vr.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "La fecha selecionada \nno se puede modificar");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido");
            }
        });
    }

    private void jitmEliminarReserva() {
        this.jitmEliminarReserva.addActionListener((ActionEvent e) -> {
            if (jtblHorarios.getSelectedColumn() != 0) {
                int filHora = jtblHorarios.getSelectedRow();
                int columDia = jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(jtblHorarios.getValueAt(filHora, columDia));
                if (valor.contains("reserva")) {

                    borrarReserva();

                } else {
                    JOptionPane.showMessageDialog(null, "La fecha selecionada \nno se puede elinimar");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido");
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
        String valor = this.formatoFecha.format(this.jcnlCalendar.getCalendar().getTime());
        if (verificacionFechaValida(valor)) {
            int verificacionDiaValido = indiceDia(valor);
            if (verificacionDiaValido == 1) {
                JOptionPane.showMessageDialog(null, "No se puede reservar en fines de semana");
                return false;
            } else if ((this.jtblHorarios.getSelectedColumn() + 1) != verificacionDiaValido) {
                JOptionPane.showMessageDialog(null, "El dia seleccionado no"
                        + "\ncorresponde a la fecha selecionada");
                return false;

            } else {
                VisReserva vr = new VisReserva(this, this.jtblHorarios.getSelectedRow() + 7, horasDisponibles(), valor, null, null);
                vr.setVisible(true);
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puede reservar en una fecha"
                    + "\nanterior a la fecha actual");
            return false;
        }

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
        jcmbSeleccion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblHorarios = new ComponentesPropios.utcJTable();
        jcnlCalendar = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Fecha");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jPanel1.add(jcmbSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 170, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Eliga");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jScrollPane2.setViewportView(jtblHorarios);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 900, 430));
        jPanel1.add(jcnlCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 170, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JComboBox<String> jcmbSeleccion;
    private com.toedter.calendar.JDateChooser jcnlCalendar;
    private javax.swing.JPopupMenu jppmMenu;
    public ComponentesPropios.utcJTable jtblHorarios;
    // End of variables declaration//GEN-END:variables
}
