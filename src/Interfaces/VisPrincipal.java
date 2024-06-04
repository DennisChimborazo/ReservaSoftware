/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Dalex
 */
public class VisPrincipal extends javax.swing.JFrame {

    int Xmov, Ymov;

    /**
     * Creates new form VisPrincipal
     */
    public VisPrincipal() {
        initComponents();
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/banner1.jpg"));
        jLabel1.setIcon(icono);
        ImageIcon iconoa = new ImageIcon(getClass().getResource("/Imagenes/aulas.png"));
        jbtnAulas.setIcon(iconoa);
        ImageIcon iconol = new ImageIcon(getClass().getResource("/Imagenes/laboratorio.png"));
        jbtnLaboratorio1.setIcon(iconol);
        ImageIcon iconot = new ImageIcon(getClass().getResource("/Imagenes/taller.png"));
        jbtnTaller.setIcon(iconot);
        ImageIcon iconoli = new ImageIcon(getClass().getResource("/Imagenes/lista_reserva.png"));
        jbtnReservar.setIcon(iconoli);
        ImageIcon iconoau = new ImageIcon(getClass().getResource("/Imagenes/auditorios.png"));
        jbtnAuditorio.setIcon(iconoau);
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jbtnAulas = new javax.swing.JButton();
        jbtnTaller = new javax.swing.JButton();
        jbtnLaboratorio1 = new javax.swing.JButton();
        jbtnReservar = new javax.swing.JButton();
        jbtnAuditorio = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/fondo_escritorio.png"));
        Image imagen= icon.getImage();
        jdskEscritorio = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(imagen,0,0,getWidth(), getHeight(),this);
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jPnl_salida = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPnl_min = new javax.swing.JPanel();
        jLbl_min = new javax.swing.JLabel();

        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/banner1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1212, -1));

        jPanel1.setBackground(new java.awt.Color(110, 7, 7));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnAulas.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnAulas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aulas.png"))); // NOI18N
        jbtnAulas.setText("Aulas");
        jbtnAulas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnAulas.setBackground(Color.WHITE);
        jbtnAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAulasActionPerformed(evt);
            }
        });

        jbtnTaller.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnTaller.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/taller.png"))); // NOI18N
        jbtnTaller.setText("Talleres");
        jbtnTaller.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnTaller.setMaximumSize(new java.awt.Dimension(110, 44));
        jbtnTaller.setMinimumSize(new java.awt.Dimension(110, 44));
        jbtnTaller.setBackground(Color.WHITE);
        jbtnTaller.setPreferredSize(new java.awt.Dimension(110, 44));

        jbtnLaboratorio1.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnLaboratorio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/laboratorio.png"))); // NOI18N
        jbtnLaboratorio1.setText("Laboratorios");
        jbtnLaboratorio1.setToolTipText("");
        jbtnLaboratorio1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnLaboratorio1.setMaximumSize(new java.awt.Dimension(110, 44));
        jbtnLaboratorio1.setMinimumSize(new java.awt.Dimension(110, 44));
        jbtnLaboratorio1.setPreferredSize(new java.awt.Dimension(110, 44));
        jbtnLaboratorio1.setBackground(Color.WHITE);

        jbtnReservar.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnReservar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lista_reserva.png"))); // NOI18N
        jbtnReservar.setText("Lista Reserva");
        jbtnReservar.setToolTipText("");
        jbtnReservar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnReservar.setMaximumSize(new java.awt.Dimension(110, 44));
        jbtnReservar.setBackground(Color.WHITE);
        jbtnReservar.setMinimumSize(new java.awt.Dimension(110, 44));
        jbtnReservar.setPreferredSize(new java.awt.Dimension(110, 44));

        jbtnAuditorio.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnAuditorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/auditorios.png"))); // NOI18N
        jbtnAuditorio.setText("Auditorios");
        jbtnAuditorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnAuditorio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbtnAuditorio.setMaximumSize(new java.awt.Dimension(110, 44));
        jbtnAuditorio.setMinimumSize(new java.awt.Dimension(110, 44));
        jbtnAuditorio.setBackground(Color.WHITE);
        jbtnAuditorio.setPreferredSize(new java.awt.Dimension(110, 44));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbtnAuditorio, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jbtnReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnTaller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnLaboratorio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnAulas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jbtnAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnLaboratorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnTaller, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnAuditorio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 260, 610));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jdskEscritorio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jdskEscritorioLayout = new javax.swing.GroupLayout(jdskEscritorio);
        jdskEscritorio.setLayout(jdskEscritorioLayout);
        jdskEscritorioLayout.setHorizontalGroup(
            jdskEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );
        jdskEscritorioLayout.setVerticalGroup(
            jdskEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jdskEscritorio)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jdskEscritorio)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 950, 610));

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

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("   X");
        jPnl_salida.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        jPanel3.add(jPnl_salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 50, 30));

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

        jPanel3.add(jPnl_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, 50, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPnl_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnl_salidaMouseClicked
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmación de salida", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Salió del sistema");
            System.exit(0);
        }
        // TODO add your handling code here:
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

    private void jbtnAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAulasActionPerformed
        VisHorario visHorario = new VisHorario();
        this.jdskEscritorio.add(visHorario);
        visHorario.setVisible(true);
    }//GEN-LAST:event_jbtnAulasActionPerformed

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
            java.util.logging.Logger.getLogger(VisPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLbl_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    public javax.swing.JButton jbtnAuditorio;
    public javax.swing.JButton jbtnAulas;
    public javax.swing.JButton jbtnLaboratorio1;
    public javax.swing.JButton jbtnReservar;
    public javax.swing.JButton jbtnTaller;
    public javax.swing.JDesktopPane jdskEscritorio;
    // End of variables declaration//GEN-END:variables
}
