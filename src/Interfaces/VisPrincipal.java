/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Dalex
 */
public class VisPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VisPrincipal
     */
    public VisPrincipal() {
        initComponents();
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
        jbtnTaller1 = new javax.swing.JButton();
        jbtnAuditorio = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/fondo_escritorio.png"));
        Image imagen= icon.getImage();
        jdskEscritorio = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(imagen,0,0,getWidth(), getHeight(),this);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/banner1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1212, -1));

        jPanel1.setBackground(new java.awt.Color(110, 7, 7));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnAulas.setBackground(new java.awt.Color(110, 7, 7));
        jbtnAulas.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnAulas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAulas.setText("Aulas");
        jbtnAulas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnTaller.setBackground(new java.awt.Color(110, 7, 7));
        jbtnTaller.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnTaller.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTaller.setText("Talleres");
        jbtnTaller.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnTaller.setMaximumSize(new java.awt.Dimension(68, 46));
        jbtnTaller.setMinimumSize(new java.awt.Dimension(68, 46));
        jbtnTaller.setPreferredSize(new java.awt.Dimension(68, 46));

        jbtnLaboratorio1.setBackground(new java.awt.Color(110, 7, 7));
        jbtnLaboratorio1.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnLaboratorio1.setForeground(new java.awt.Color(255, 255, 255));
        jbtnLaboratorio1.setText("Laboratorios");
        jbtnLaboratorio1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnLaboratorio1.setMaximumSize(new java.awt.Dimension(68, 46));
        jbtnLaboratorio1.setMinimumSize(new java.awt.Dimension(68, 46));
        jbtnLaboratorio1.setPreferredSize(new java.awt.Dimension(68, 46));

        jbtnTaller1.setBackground(new java.awt.Color(110, 7, 7));
        jbtnTaller1.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnTaller1.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTaller1.setText("Reservar");
        jbtnTaller1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnTaller1.setMaximumSize(new java.awt.Dimension(68, 46));
        jbtnTaller1.setMinimumSize(new java.awt.Dimension(68, 46));
        jbtnTaller1.setPreferredSize(new java.awt.Dimension(68, 46));

        jbtnAuditorio.setBackground(new java.awt.Color(110, 7, 7));
        jbtnAuditorio.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jbtnAuditorio.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAuditorio.setText("Auditorio");
        jbtnAuditorio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnAuditorio.setMaximumSize(new java.awt.Dimension(68, 46));
        jbtnAuditorio.setMinimumSize(new java.awt.Dimension(68, 46));
        jbtnAuditorio.setPreferredSize(new java.awt.Dimension(68, 46));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAuditorio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTaller1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnLaboratorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTaller, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
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
                .addComponent(jbtnTaller1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnAuditorio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 240, 660));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jdskEscritorio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jdskEscritorioLayout = new javax.swing.GroupLayout(jdskEscritorio);
        jdskEscritorio.setLayout(jdskEscritorioLayout);
        jdskEscritorioLayout.setHorizontalGroup(
            jdskEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 958, Short.MAX_VALUE)
        );
        jdskEscritorioLayout.setVerticalGroup(
            jdskEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 970, 630));

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnAuditorio;
    private javax.swing.JButton jbtnAulas;
    private javax.swing.JButton jbtnLaboratorio1;
    private javax.swing.JButton jbtnTaller;
    private javax.swing.JButton jbtnTaller1;
    private javax.swing.JDesktopPane jdskEscritorio;
    // End of variables declaration//GEN-END:variables
}
