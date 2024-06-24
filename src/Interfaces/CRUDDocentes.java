/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Interfaces;

import Modelos.Docente;
import Modelos.ModeloUsuarios;
import Repositorio.Conexiones;
import Validadores.Validadores;
import java.awt.HeadlessException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author edupu
 */
public class CRUDDocentes extends javax.swing.JFrame {

    Docente doc;
    VisPrincipal vsP;

    public CRUDDocentes() {
        initComponents();
        textosBlancos();
        cargarTabla();
        bloquearTextos();
        this.setLocationRelativeTo(null);
        selecionarTabla();
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/Imagenes/banner.png");
    }

    public void consumirDatos(VisPrincipal vsP) {
        this.vsP = vsP;
    }

    private void textosBlancos() {
        this.jtxtCedula.setText("");
        this.jtxtNombre.setText("");
        this.jtxtTelefono.setText("");
        this.jtxtDireccion.setText("");
        this.jtxtBuscar.setText("");
    }

    private void bloquearTextos() {
        this.jbtnGuardar.setEnabled(false);
        this.jbtnEditar.setEnabled(false);
        this.jbtnCancelar.setEnabled(false);
        this.jtxtCedula.setEnabled(false);
        this.jtxtNombre.setEnabled(false);
        this.jtxtTelefono.setEnabled(false);
        this.jtxtDireccion.setEnabled(false);
        this.jbtnNuevo.setEnabled(true);
    }

    private void btnNuevo() {
        this.jtxtCedula.setEnabled(true);
        this.jtxtNombre.setEnabled(true);
        this.jtxtTelefono.setEnabled(true);
        this.jtxtDireccion.setEnabled(true);
        this.jbtnGuardar.setEnabled(true);
        this.jbtnCancelar.setEnabled(true);
        this.jbtnNuevo.setEnabled(false);
        this.jbtnEliminar.setEnabled(true);
    }

    private void cargarTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Cedula", "Nombre", "Telefono", "Direccion"}, 0);
        this.jTable1.setModel(modeloTabla);
        modeloTabla.setRowCount(0);
        try {
            Conexiones cn = new Conexiones();
            Connection cc = cn.conectar();
            String sql = "Select * from personas";
            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                Object[] docente = new Object[5];
                docente[0] = rs.getString("ced_per");
                docente[1] = ModeloUsuarios.modelomayus(rs.getString("nom_per")) + " " + ModeloUsuarios.modelomayus(rs.getString("ape_per"));
                docente[2] = rs.getString("telf_per");
                docente[3] = rs.getString("dir_per");
                modeloTabla.addRow(docente);
            }
            this.jTable1.setModel(modeloTabla);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private void agregarDocente() {
        String sql;
        String[] nombreCompleto = this.jtxtNombre.getText().split(" ");
        if (!this.jtxtCedula.getText().isEmpty() && !this.jtxtNombre.getText().isEmpty() && !this.jtxtTelefono.getText().isEmpty() && !this.jtxtDireccion.getText().isEmpty()) {
            if (Validadores.isValidCedula(this.jtxtCedula.getText())) {
                if (Validadores.existeUsuario(this.jtxtCedula.getText())) {

                    if (Validadores.isValidTelefono(this.jtxtTelefono.getText())) {
                        if (nombreCompleto.length < 2 || nombreCompleto[0].isEmpty() || nombreCompleto[1].isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe ingresar nombre y apellido");
                        } else {
                            try {
                                Conexiones cc = new Conexiones();
                                Connection cn = cc.conectar();
                                sql = "INSERT INTO personas"
                                        + "(ced_per, nom_per, ape_per, fech_nac, telf_per, dir_per)"
                                        + "VALUES(?, ?, ?, ?, ?, ?);";
                                PreparedStatement ps = cn.prepareStatement(sql);
                                ps.setString(1, this.jtxtCedula.getText());
                                ps.setString(2, nombreCompleto[0].toLowerCase());
                                ps.setString(3, nombreCompleto[1].toLowerCase());
                                ps.setString(4, "2001-05-01");
                                ps.setString(5, this.jtxtTelefono.getText());
                                ps.setString(6, this.jtxtDireccion.getText().toLowerCase());
                                int res = ps.executeUpdate();
                                if (res > 0) {
                                    JOptionPane.showMessageDialog(null, "Se ingreso correctamene");
                                    cargarTabla();
                                    textosBlancos();
                                    bloquearTextos();
                                    ps.close();
                                }

                            } catch (SQLException e) {
                                System.out.println(e.toString());
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Numero de telefono ingresado no valido");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La persona a ingresar ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Numero de cedula ingresado no valido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }

    }

    private void editarDocente() {

        if (!this.jtxtCedula.getText().isEmpty() && !this.jtxtNombre.getText().isEmpty() && !this.jtxtTelefono.getText().isEmpty() && !this.jtxtDireccion.getText().isEmpty()) {
            String sql = "UPDATE personas SET  telf_per='" + this.jtxtTelefono.getText() + "', dir_per='" + this.jtxtDireccion.getText() + "' WHERE ced_per='" + this.jtxtCedula.getText() + "'";
            try {
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
                int res = ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Se actualizo correctamene");
                    cargarTabla();
                    textosBlancos();
                    bloquearTextos();
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CRUDDocentes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void eliminarDocente() {
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String sql = "Delete from personas where ced_per='" + this.jtxtCedula.getText() + "'";
            PreparedStatement psd = cn.prepareStatement(sql);
            int res = psd.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Se Elimino correctamene");
                cargarTabla();
                textosBlancos();
                bloquearTextos();
                psd.close();
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private void selecionarTabla() {
        this.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    jtxtCedula.setText(jTable1.getValueAt(row, 0).toString());
                    jtxtNombre.setText(jTable1.getValueAt(row, 1).toString());
                    jtxtTelefono.setText(jTable1.getValueAt(row, 2).toString());
                    jtxtDireccion.setText(jTable1.getValueAt(row, 3).toString());
                    jbtnGuardar.setEnabled(false);
                    jbtnEditar.setEnabled(true);
                    jbtnCancelar.setEnabled(true);
                    jtxtCedula.setEnabled(false);
                    jtxtNombre.setEnabled(false);
                    jtxtTelefono.setEnabled(true);
                    jtxtDireccion.setEnabled(true);
                    jbtnNuevo.setEnabled(false);
                    jbtnEliminar.setEnabled(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        CRUDDocentes doc = new CRUDDocentes();
        doc.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtTelefono = new javax.swing.JTextField();
        jtxtDireccion = new javax.swing.JTextField();
        jtxtBuscar = new javax.swing.JTextField();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Microsoft Uighur", 1, 50)); // NOI18N
        jLabel2.setText("Lista Docentes");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft Uighur", 1, 33)); // NOI18N
        jLabel3.setText("Cedula:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 124, -1));

        jLabel4.setFont(new java.awt.Font("Microsoft Uighur", 1, 33)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 124, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft Uighur", 1, 33)); // NOI18N
        jLabel5.setText("Telefono:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 124, -1));

        jLabel7.setFont(new java.awt.Font("Microsoft Uighur", 1, 33)); // NOI18N
        jLabel7.setText("Dirección:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 124, -1));

        jLabel8.setFont(new java.awt.Font("Microsoft Uighur", 1, 33)); // NOI18N
        jLabel8.setText("Buscar:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 124, -1));

        jtxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCedulaActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 216, 30));
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 216, 30));
        jPanel1.add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 216, 30));
        jPanel1.add(jtxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 216, 30));
        jPanel1.add(jtxtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 216, 30));

        jbtnNuevo.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnNuevoMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 140, 30));

        jbtnGuardar.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 140, 30));

        jbtnEditar.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jbtnEditar.setText("Editar");
        jbtnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnEditarMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 140, 30));

        jbtnCancelar.setBackground(new java.awt.Color(110, 7, 7));
        jbtnCancelar.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jbtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCancelarMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 350, 111, 40));

        jbtnEliminar.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 240, 140, 30));

        jbtnVolver.setBackground(new java.awt.Color(25, 134, 191));
        jbtnVolver.setFont(new java.awt.Font("Microsoft Uighur", 1, 25)); // NOI18N
        jbtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        jbtnVolver.setText("Volver");
        jbtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 350, 110, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/banner.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 128));

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Microsoft Uighur", 0, 26)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 1120, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnGuardarMouseClicked
        agregarDocente();
    }//GEN-LAST:event_jbtnGuardarMouseClicked

    private void jbtnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnNuevoMouseClicked
        btnNuevo();
        textosBlancos();
    }//GEN-LAST:event_jbtnNuevoMouseClicked

    private void jbtnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCancelarMouseClicked
        bloquearTextos();
        textosBlancos();
    }//GEN-LAST:event_jbtnCancelarMouseClicked

    private void jbtnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnEditarMouseClicked
        editarDocente();
    }//GEN-LAST:event_jbtnEditarMouseClicked

    private void jbtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnEliminarMouseClicked
        eliminarDocente();
    }//GEN-LAST:event_jbtnEliminarMouseClicked

    private void jbtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVolverActionPerformed
        this.vsP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbtnVolverActionPerformed

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCedulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JButton jbtnVolver;
    private javax.swing.JTextField jtxtBuscar;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
