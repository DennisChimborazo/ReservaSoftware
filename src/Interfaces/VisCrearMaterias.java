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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class VisCrearMaterias extends javax.swing.JFrame {

    /**
     * Creates new form VisCrearMaterias
     */
    int Xmov, Ymov;
    VisPrincipal vsP;

    public VisCrearMaterias() {
        initComponents();
        this.jPanel1.setBackground(Color.WHITE);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/banner.png"));
        this.jLabel1.setIcon(imagen);
        setLocationRelativeTo(this);
        jbtnVolver.setBackground(new Color(25,134,191));
        jbtnVolver.setForeground(Color.WHITE);
        jbtnCrear.setBackground(new Color(110,7,7));
        jbtnCrear.setForeground(Color.WHITE);
        this.cargarcomboMateria();
        this.cargarcomboPersonas();
        
    }

     public void consumirDatos(VisPrincipal vsP){
        this.vsP=vsP;
    }
  public void cargarcomboMateria() {
        this.jcbxCarerra.removeAllItems();
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = "SELECT * FROM carreras";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String carrera = rs.getString("nom_car");
                this.jcbxCarerra.addItem(carrera);
            }
            cc.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
  public void cargarcomboPersonas() {
        this.jcbxDocente.removeAllItems();
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = "SELECT * FROM personas";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                String persona = rs.getString("nom_per");
                String personas = rs.getString("ape_per");
                this.jcbxDocente.addItem(persona + " " + personas);
            }
            cc.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
  private String buscarIdCarrera() {
        String idCarrera = "";
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String queryBuscarIdAula = "SELECT id_car FROM carreras WHERE nom_car= ?";
            PreparedStatement declaración = cn.prepareStatement(queryBuscarIdAula);
            declaración.setString(1, this.jcbxCarerra.getSelectedItem().toString());
            ResultSet resultado = declaración.executeQuery();
            if (resultado.next()) {
                idCarrera = resultado.getString("id_car");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCarrera;

    }
  private String buscarIdPersona() {
    String idPersona = "";
    try {
        Conexiones cc = new Conexiones();
        Connection cn = cc.conectar();
        String nombreCompleto = this.jcbxDocente.getSelectedItem().toString();
        
        // Dividir el nombre completo en nombre y apellido
        String[] partes = nombreCompleto.split(" ");
        if (partes.length < 2) {
            JOptionPane.showMessageDialog(null, "Nombre completo inválido.");
            return idPersona;
        }
        String nombre = partes[0];
        String apellido = partes[1];

        String queryBuscarIdPersona = "SELECT id_per FROM personas WHERE nom_per= ? AND ape_per= ?";
        PreparedStatement declaración = cn.prepareStatement(queryBuscarIdPersona);
        declaración.setString(1, nombre);
        declaración.setString(2, apellido);
        
        ResultSet resultado = declaración.executeQuery();
        if (resultado.next()) {
            idPersona = resultado.getString("id_per");
        }
        cn.close();
    } catch (SQLException ex) {
        Logger.getLogger(VisReserva.class.getName()).log(Level.SEVERE, null, ex);
    }
    return idPersona;
}
  private void guardarMateria() {
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String idCarrera = buscarIdCarrera();
            String idPersona= buscarIdPersona();
            String sql = "insert into materias (nombre,id_carrera,id_per) values (?,?,?)";
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, this.jtxtMateria.getText());
            psd.setString(2, idCarrera);
            psd.setString(3, idPersona);
            int num = psd.executeUpdate();
            if (num != 0) {
                JOptionPane.showMessageDialog(null, "Se creo una nueva materia");
                this.vsP.setVisible(true);
                this.dispose();
                this.jtxtMateria.setText("");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtMateria = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbxCarerra = new javax.swing.JComboBox<>();
        jcbxDocente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jbtnVolver = new javax.swing.JButton();
        jbtnCrear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel2.setText("Materia:");

        jtxtMateria.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel3.setText("Carrera:");

        jLabel4.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel4.setText("Docente:");

        jcbxCarerra.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jcbxCarerra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbxDocente.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jcbxDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jLabel5.setText("Ingreso de Materias");

        jbtnVolver.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });

        jbtnCrear.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnCrear.setText("Crear");
        jbtnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCrearActionPerformed(evt);
            }
        });

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

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 50, 30));

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

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 50, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jbtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jcbxCarerra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtMateria)
                                .addComponent(jcbxDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbxCarerra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbxDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtMateria))
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearActionPerformed
        // TODO add your handling code here:
         if (!this.jtxtMateria.getText().isEmpty()) {
            
                guardarMateria();
 
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor ingrese el nombre");
        }
    }//GEN-LAST:event_jbtnCrearActionPerformed

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

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        // TODO add your handling code here:
        this.vsP.setVisible(true);
    this.dispose();
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
            java.util.logging.Logger.getLogger(VisCrearMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisCrearMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisCrearMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisCrearMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisCrearMaterias().setVisible(true);
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JButton jbtnCrear;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JComboBox<String> jcbxCarerra;
    private javax.swing.JComboBox<String> jcbxDocente;
    private javax.swing.JTextField jtxtMateria;
    // End of variables declaration//GEN-END:variables
}
