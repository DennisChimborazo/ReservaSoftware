/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Interfaces;

import Modelos.Docente;
import Modelos.ModeloUsuarios;
import Repositorio.Conexion;
import Validadores.Validadores;
import java.awt.HeadlessException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author edupu
 */
public class CRUDDocentes extends javax.swing.JInternalFrame {

    Docente doc;

    public CRUDDocentes() {
        initComponents();
        textosBlancos();
        cargarTabla("");
        //this.setLocationRelativeTo(null);
    }

    public void textosBlancos() {
        this.jtxtCedula.setText("");
        this.jtxtNombre.setText("");
        this.jtxtTelefono.setText("");
        this.jtxtDireccion.setText("");
        this.jtxtBuscar.setText("");
    }

    private void cargarTabla(String dato) {
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Cedula", "Nombre", "Telefono", "Usuario", "Correo",}, 0);
        this.jTable1.setModel(modeloTabla);
        modeloTabla.setRowCount(0);
        try {
            String sql = "Select personas.id_per, personas.nom_per, personas.ape_per, personas.telf_per,usuarios.usuario,usuarios.correo,usuarios.tipo_usuario "
                    + "from personas,usuarios "
                    + "where personas.id_per=usuarios.id_per";
            Statement st = Conexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] docente = new Object[5];
                docente[0] = rs.getString("personas.id_per");
                docente[1] = ModeloUsuarios.modelomayus(rs.getString("personas.nom_per")) + " " + ModeloUsuarios.modelomayus(rs.getString("personas.ape_per"));
                docente[2] = rs.getString("personas.telf_per");
                docente[3] = rs.getString("usuarios.usuario");
                docente[4] = rs.getString("usuarios.correo");
                if (rs.getString("usuarios.tipo_usuario").equals("docente")) {
                    try {
                        doc = new Docente(docente[0].toString(), rs.getString("personas.nom_per"), rs.getString("personas.ape_per"), docente[2].toString(), docente[3].toString(), docente[4].toString());
                    } catch (SQLException e) {
                        System.out.println(e.toString());
                    }
                    modeloTabla.addRow(docente);
                }
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
                                sql = "INSERT INTO personas"
                                        + "(id_per, nom_per, ape_per, fech_nac, telf_per, dir_per)"
                                        + "VALUES(?, ?, ?, ?, ?, ?);";
                                PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
                                ps.setString(1, this.jtxtCedula.getText());
                                ps.setString(2, nombreCompleto[0].toLowerCase());
                                ps.setString(3, nombreCompleto[1].toLowerCase());
                                ps.setString(4, "2001-05-01");
                                ps.setString(5, this.jtxtTelefono.getText());
                                ps.setString(6, this.jtxtDireccion.getText().toLowerCase());
                                int res = ps.executeUpdate();
                                if (res > 0) {
                                    try {
                                        sql = "INSERT INTO usuarios"
                                                + "(usuario, correo, contrasenia, tipo_usuario, id_per)"
                                                + "VALUES(?, ?, ?, ?, ?);";
                                        ps = Conexion.getConnection().prepareStatement(sql);
                                        ps.setString(1, ModeloUsuarios.modeloUsser(nombreCompleto[0].toLowerCase(), nombreCompleto[1].toLowerCase(), this.jtxtCedula.getText()));
                                        ps.setString(2, ModeloUsuarios.modeloCorreo(nombreCompleto[0].toLowerCase(), nombreCompleto[1].toLowerCase()));
                                        ps.setString(3, "1234");
                                        ps.setString(4, "docente");
                                        ps.setString(5, this.jtxtCedula.getText());
                                        int rs = ps.executeUpdate();
                                        if (rs > 0) {
                                            JOptionPane.showMessageDialog(null, "Se ingreso correctamene");
                                            cargarTabla("");
                                            ps.close();

                                        }
                                    } catch (HeadlessException | SQLException e) {
                                        System.out.println(e.toString());
                                    }
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

    public static void main(String[] args) {
        CRUDDocentes doc = new CRUDDocentes();
        doc.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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
        jbntEditar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/banner.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Lista Docentes");

        jLabel3.setText("Cedula:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Telefono:");

        jLabel7.setText("Direccion:");

        jLabel8.setText("Buscar:");

        jtxtCedula.setText("jTextField1");

        jtxtNombre.setText("jTextField2");

        jtxtTelefono.setText("jTextField3");

        jtxtDireccion.setText("jTextField4");

        jtxtBuscar.setText("jTextField5");

        jbtnNuevo.setText("Nuevo");

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnGuardarMouseClicked(evt);
            }
        });

        jbntEditar.setText("Editar");

        jbtnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxtCedula)
                    .addComponent(jtxtNombre)
                    .addComponent(jtxtTelefono)
                    .addComponent(jtxtDireccion)
                    .addComponent(jtxtBuscar))
                .addGap(135, 135, 135)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbntEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNuevo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGuardar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbntEditar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancelar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnGuardarMouseClicked
        agregarDocente();
    }//GEN-LAST:event_jbtnGuardarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbntEditar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JTextField jtxtBuscar;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
