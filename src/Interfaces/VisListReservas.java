/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Repositorio.Conexiones;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class VisListReservas extends javax.swing.JFrame {

    /**
     * Creates new form VisListReservas
     */
    int Xmov, Ymov;
    VisPrincipal vsP;

    public VisListReservas() {
        initComponents();
        setLocationRelativeTo(this);
        this.jPanel1.setBackground(Color.WHITE);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/banner.png"));
        this.jLabel1.setIcon(imagen);
        jbtnVolver1.setBackground(new Color(25, 134, 191));
        jbtnVolver1.setForeground(Color.WHITE);
        this.mostrarTabla();
    }

    public void consumirDatos(VisPrincipal vsP) {
        this.vsP = vsP;
    }

    public boolean mostrarTabla() {

        DefaultTableModel mtabla = (DefaultTableModel) this.jtbReserva.getModel();
        mtabla.setRowCount(0);
        Conexiones cc = new Conexiones();
        Connection cn = cc.conectar();
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsm;
        int columnas;
        try {
            ps = cn.prepareStatement("SELECT \n"
                    + "    a.nom_aul, \n"
                    + "    CONCAT(p.nom_per, ' ', p.ape_per) AS Nombre,\n"
                    + "    r.desc_reser, \n"
                    + "    r.fecha_reserv, \n"
                    + "    r.hor_reserv, \n"
                    + "    r.hora_fin_reserv\n"
                    + "FROM reservas r\n"
                    + "INNER JOIN aulas a ON a.id_aul = r.id_lab_reser\n"
                    + "INNER JOIN personas p ON p.id_per = r.id_per_reserv;");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            columnas = rsm.getColumnCount();
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                mtabla.addRow(fila);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnVolver = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbtnVolver1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbReserva = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();

        jbtnVolver.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnVolver.setText("Volver");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1082, 109));

        jLabel2.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Lista Reservas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 196, 38));

        jbtnVolver1.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnVolver1.setText("Volver");
        jbtnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolver1ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnVolver1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 520, -1, -1));

        jtbReserva.setFont(new java.awt.Font("Microsoft Uighur", 0, 20)); // NOI18N
        jtbReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Laboratorio", "Responsable", "Descripción", "Fecha", "Inicio", "Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbReserva);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 860, 290));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(530, 420));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("   X");
        jPnl_salida.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 30));

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

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPnl_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseClicked

        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);

        if (mensaje == JOptionPane.YES_OPTION) {
            System.exit(0);
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

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - Xmov, y - Ymov);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        Xmov = evt.getX();
        Ymov = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jbtnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolver1ActionPerformed
        // TODO add your handling code here:
        this.vsP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbtnVolver1ActionPerformed

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
            java.util.logging.Logger.getLogger(VisListReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisListReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisListReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisListReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisListReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JButton jbtnVolver1;
    private javax.swing.JTable jtbReserva;
    // End of variables declaration//GEN-END:variables
}
