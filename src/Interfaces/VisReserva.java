/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Repositorio.Conexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Dalex
 */
public class VisReserva extends javax.swing.JFrame {

    int Xmov, Ymov;
    int horainico;
    int horasTotales;
    String fecha;
    String id;
    ArrayList<String> datos;
    int cont = 3;
    VisHorario vistHorario;

    /**
     * Creates new form VisReserva
     *
     * @param vistHorario
     * @param horainico
     * @param horasTotales
     * @param fecha
     * @param id
     * @param datos
     */
    public VisReserva(VisHorario vistHorario, int horainico, int horasTotales, String fecha, String id, ArrayList<String> datos) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.vistHorario = vistHorario;
        this.datos = datos;
        this.fecha = fecha;
        this.id = id;
        this.horainico = horainico;
        this.horasTotales = horasTotales;
        verificacionAccion();
        this.jtxtHoraInicio.setEditable(false);
    }

    public void asignarHorasDisponibles(int horainico, int horaFin) {
        if (horainico >= 13) {
            horainico = horainico + 1;
        }
        int cont = 0;
        this.jtxtHoraInicio.setText(String.valueOf(horainico));
        if (horainico <= 12) {
            cont = horainico;
            for (int i = 0; i < horaFin; i++) {
                if (cont == 13) {
                    horainico = horainico + 1;
                }
                this.jcmbHorasDisponibles.addItem((horainico + i) + ":00 - " + (horainico + i + 1) + ":00");
                cont++;
            }
        } else {

            for (int i = 0; i < horaFin; i++) {
                this.jcmbHorasDisponibles.addItem((horainico + i) + ":00 - " + (horainico + i + 1) + ":00");
            }
        }
    }

    private boolean controlIngresosValidos() {
        if (!this.jtxtNombres.getText().isEmpty() && !this.jtxtaDescripcion.getText().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos requeridos");
        }
        return false;
    }

    private void guardar() {
        this.cont = cont + 1;
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String sql = "insert into reseva (idhorario,nombre,descripcion,fecha,horainicio,horafin)values(?,?,?,?,?,?)";
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, String.valueOf(cont));
            psd.setString(2, this.jtxtNombres.getText());
            psd.setString(3, this.jtxtaDescripcion.getText().toLowerCase());
            psd.setString(4, this.fecha);
            psd.setString(5, this.jtxtHoraInicio.getText());
            psd.setString(6, obtenerHoraFinal());
            int num = psd.executeUpdate();
            if (num != 0) {
                JOptionPane.showMessageDialog(null, "Se guardo la reserva");
                this.vistHorario.acutualizarDatos();
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

            // JOptionPane.showMessageDialog(null, "Verifique los datos que desea guardar");
        }
    }

    private String obtenerHoraFinal() {
        String h = this.jcmbHorasDisponibles.getSelectedItem().toString();
        h = h.split(":")[0];
        return String.valueOf(Integer.parseInt(h) + 1);
    }

    public void editarReserva() {

        int op = JOptionPane.showConfirmDialog(null, "Desea editar la reserva", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {

            try {
                Conexion cc = new Conexion();
                Connection cn = cc.conectar();
                String sql = "update reseva set nombre='" + this.jtxtNombres.getText()
                        + "',descripcion='" + this.jtxtaDescripcion.getText() + "' where idhorario = '" + this.id + "'";
                PreparedStatement psd = cn.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se actualizo la infomacion de la reserva");
                    this.vistHorario.acutualizarDatos();
                    this.dispose();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Verifique los datos que desea editar");
            }

        }

    }

    public void verificacionAccion() {
        if (this.id == null) {
            this.jbtnReservar.setText("Reservar");
            asignarHorasDisponibles(this.horainico, this.horasTotales);
        } else {
            this.jtxtNombres.setText(this.datos.get(0));
            this.jtxtaDescripcion.setText(this.datos.get(1));
            this.jtxtHoraInicio.setText(this.datos.get(2));
            this.jcmbHorasDisponibles.addItem(this.datos.get(3));
            this.jbtnReservar.setText("Editar");

        }
    }

    private VisReserva() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtaDescripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jtxtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbtnReservar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcmbHorasDisponibles = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jtxtHoraInicio = new javax.swing.JTextField();
        jbtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Hasta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jtxtaDescripcion.setColumns(20);
        jtxtaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jtxtaDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 490, 160));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));
        jPanel1.add(jtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 310, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Reserva");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        jbtnReservar.setText("Reservar");
        jbtnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReservarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnReservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 210, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(530, 420));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnl_salida.setBackground(new java.awt.Color(255, 255, 255));
        jPnl_salida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPnl_salidaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPnl_salidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPnl_salidaMouseExited(evt);
            }
        });
        jPnl_salida.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("   X");
        jPnl_salida.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel2.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 50, 30));

        jPnl_min.setBackground(new java.awt.Color(255, 255, 255));
        jPnl_min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPnl_minMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPnl_minMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPnl_minMouseExited(evt);
            }
        });
        jPnl_min.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLbl_min.setBackground(new java.awt.Color(0, 0, 0));
        jLbl_min.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLbl_min.setText("   -");
        jPnl_min.add(jLbl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel2.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 50, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Descripcion");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        jcmbHorasDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbHorasDisponiblesActionPerformed(evt);
            }
        });
        jPanel1.add(jcmbHorasDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 160, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Hora de inicio ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        jPanel1.add(jtxtHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 120, 40));

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 230, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReservarActionPerformed
        if (this.id == null) {
            guardar();
        } else {
            editarReserva();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnReservarActionPerformed

    private void jPnl_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseClicked

        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);

        if (mensaje == JOptionPane.YES_OPTION) {
            this.dispose();
        }

    }//GEN-LAST:event_jPnl_salidaMouseClicked

    private void jPnl_salidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseEntered
        jPnl_salida.setBackground(Color.red);
    }//GEN-LAST:event_jPnl_salidaMouseEntered

    private void jPnl_salidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseExited
        jPnl_salida.setBackground(Color.WHITE);

    }//GEN-LAST:event_jPnl_salidaMouseExited

    private void jPnl_minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_minMouseClicked
        setExtendedState(JFrame.ICONIFIED);

        // TODO add your handling code here:
    }//GEN-LAST:event_jPnl_minMouseClicked

    private void jPnl_minMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_minMouseEntered
        jPnl_min.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_jPnl_minMouseEntered

    private void jPnl_minMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_minMouseExited
        jPnl_min.setBackground(Color.white);
    }//GEN-LAST:event_jPnl_minMouseExited

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - Xmov, y - Ymov);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        Xmov = evt.getX();
        Ymov = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jcmbHorasDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbHorasDisponiblesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbHorasDisponiblesActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(VisReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnReservar;
    private javax.swing.JComboBox<String> jcmbHorasDisponibles;
    private javax.swing.JTextField jtxtHoraInicio;
    private javax.swing.JTextField jtxtNombres;
    private javax.swing.JTextArea jtxtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
