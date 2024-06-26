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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reservasoftware.Sounds;

/**
 *
 * @author Dalex
 */
public class VistNuevosEspacios extends javax.swing.JFrame {

    int Xmov, Ymov;
    VisPrincipal vsP;

    /**
     * Creates new form NewJFrame
     */
    public VistNuevosEspacios() {
        initComponents();
        this.setLocationRelativeTo(this);
        cargarInformacion();
    }

    private void cargarInformacion() {
        this.jcmbEspacios.addItem("Ciencias aplicadas");
        this.jcmbEspacios.addItem("Edificio 1");
        this.jcmbEspacios.addItem("Edificio 2");
        this.jcmbEspacios.addItem("Talleres tecnologicos");
        this.jcmbTipoEspacio.addItem("Aula");
        this.jcmbTipoEspacio.addItem("Laboratorio");
        this.jcmbTipoEspacio.addItem("Taller");
        this.jcmbTipoEspacio.addItem("Auditorio");
    }

    public void consumirDatos(VisPrincipal vsP) {
        this.vsP = vsP;
    }

    private void guardarEspacios() {
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String idEdidicio = buscarIdEdificio();
            String sql = "insert into aulas (nom_aul,tipo_aul,id_edi_per)values(?,?,?)";
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, this.jtxtNombreEspacio.getText());
            psd.setString(2, this.jcmbTipoEspacio.getSelectedItem().toString());
            psd.setString(3, idEdidicio);
            int num = psd.executeUpdate();
            if (num != 0) {
                Sounds.sonidoOk();
                JOptionPane.showMessageDialog(null, "Se creo un nuevo espacio");
                this.jtxtNombreEspacio.setText("");
            }
        } catch (SQLException ex) {
            Sounds.sonidoError();
            JOptionPane.showMessageDialog(null, "Revisa los datos ingresados", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);

        }
    }

    private String buscarIdEdificio() {
        String idAula = "";
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String queryBuscarIdAula = "SELECT id_edi FROM edificios WHERE nom_edi= ?";
            PreparedStatement declaración = cn.prepareStatement(queryBuscarIdAula);
            declaración.setString(1, this.jcmbEspacios.getSelectedItem().toString());
            ResultSet resultado = declaración.executeQuery();
            if (resultado.next()) {
                idAula = resultado.getString("id_edi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idAula;

    }

    private boolean valdacionEspacio() {
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String consulta = "SELECT nom_aul FROM aulas WHERE LOWER(nom_aul) = ?";
            PreparedStatement declaración = cn.prepareStatement(consulta);
            declaración.setString(1, this.jtxtNombreEspacio.getText().toLowerCase());
            ResultSet resultado = declaración.executeQuery();
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jcmbEspacios = new javax.swing.JComboBox<>();
        jcmbTipoEspacio = new javax.swing.JComboBox<>();
        jbtnCrear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();
        jbtnVolver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtNombreEspacio = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcmbEspacios.setFont(new java.awt.Font("Microsoft Uighur", 0, 23)); // NOI18N
        jPanel1.add(jcmbEspacios, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 110, 210, 38));

        jcmbTipoEspacio.setFont(new java.awt.Font("Microsoft Uighur", 0, 23)); // NOI18N
        jPanel1.add(jcmbTipoEspacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 170, 210, 38));

        jbtnCrear.setBackground(new java.awt.Color(110, 7, 7));
        jbtnCrear.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnCrear.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCrear.setText("Crear");
        jbtnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 150, 41));

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

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("   X");
        jPnl_salida.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 50, 30));

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

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 30));

        jbtnVolver.setBackground(new java.awt.Color(25, 134, 191));
        jbtnVolver.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 150, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel1.setText("Edificio:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel2.setText("Tipo de espacio:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jtxtNombreEspacio.setFont(new java.awt.Font("Microsoft Uighur", 0, 23)); // NOI18N
        jPanel2.add(jtxtNombreEspacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 210, 40));

        jPanel4.setBackground(new java.awt.Color(110, 7, 7));

        jLabel4.setFont(new java.awt.Font("Microsoft Uighur", 1, 50)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Espacios");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(150, 150, 150))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 440, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearActionPerformed
        if (!this.jtxtNombreEspacio.getText().isEmpty()) {
            if (!valdacionEspacio()) {
                guardarEspacios();
                this.vsP.setVisible(true);
                this.dispose();

            } else {
                Sounds.sonidoError();
                JOptionPane.showMessageDialog(null, "El nombre elegido ya se encuentra en los registros", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);

            }
        } else {
            Sounds.sonidoError();
            JOptionPane.showMessageDialog(null, "Por favor ingrese el nombre", "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jbtnCrearActionPerformed

    private void jPnl_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseClicked
        Sounds.sonidoAdvertencia();
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmación de salida", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed

        this.vsP.setVisible(true);
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(VistNuevosEspacios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistNuevosEspacios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistNuevosEspacios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistNuevosEspacios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistNuevosEspacios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    public javax.swing.JButton jbtnCrear;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcmbEspacios;
    private javax.swing.JComboBox<String> jcmbTipoEspacio;
    private javax.swing.JTextField jtxtNombreEspacio;
    // End of variables declaration//GEN-END:variables
}
