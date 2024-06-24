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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    String nombreAula;
    String[] nombresModificados;

    /**
     * Creates new form VisReserva
     *
     * @param vistHorario
     * @param nombreAula
     * @param horainico
     * @param horasTotales
     * @param fecha
     * @param id
     * @param datos
     */
    public VisReserva(VisHorario vistHorario, String nombreAula, int horainico, int horasTotales, String fecha, String id, ArrayList<String> datos) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.vistHorario = vistHorario;
        this.datos = datos;
        this.fecha = fecha;
        this.id = id;
        this.horainico = horainico;
        this.horasTotales = horasTotales;
        this.nombreAula = nombreAula;
        verificacionAccion();
        this.jtxtHoraInicio.setEditable(false);
        setButtonIcon(jbtnReservar, "/Imagenes/reserva.png");
        setButtonIcon(jbtnCancelar, "/Imagenes/cancel.png");
    }

    private void setButtonIcon(JButton button, String resourcePath) {
        java.net.URL imgURL = getClass().getResource(resourcePath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            button.setIcon(icon);
        } else {
            System.err.println("No se pudo encontrar la imagen: " + resourcePath);
        }
    }

public void asignarHorasDisponibles(int horainico, int horaFin) {
        if (horainico >= 13) {
            horainico = horainico + 1;
        }
        int cont = 0;
        this.jtxtHoraInicio.setText(String.valueOf(horainico)+":00");
        if (horainico <= 12) {
            cont = horainico;
            for (int i = 0; i < horaFin; i++) {
                if (cont == 13) {
                    horainico = horainico + 1;
                }
                this.jcmbHorasDisponibles.addItem((horainico + i + 1) + ":00");
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

    private String buscarIdAula() {
        String idAula = "";
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String queryBuscarIdAula = "SELECT id_aul FROM aulas WHERE nom_aul = ?";
            PreparedStatement declaración = cn.prepareStatement(queryBuscarIdAula);
            declaración.setString(1, this.nombreAula);
            ResultSet resultado = declaración.executeQuery();
            if (resultado.next()) {
                idAula = resultado.getString("id_aul");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idAula;

    }

    public String buscarIdPersona() {
        String idPersona = "";
        String[] nombres = this.jtxtNombres.getText().split(" ");
        if (nombres.length <= 1) {
            JOptionPane.showMessageDialog(null, "ingrese dos nombres");
        } else {
            try {
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                String consulta = "SELECT id_per,nom_per,ape_per FROM personas WHERE LOWER(nom_per) = ? AND LOWER(ape_per) = ?";
                PreparedStatement declaración = cn.prepareStatement(consulta);
                declaración.setString(1, nombres[0].toLowerCase());
                declaración.setString(2, nombres[1].toLowerCase());
                ResultSet resultado = declaración.executeQuery();
                if (resultado.next()) {
                    idPersona = resultado.getString("id_per");
                }
            } catch (SQLException ex) {
                // System.out.println(ex);
                JOptionPane.showMessageDialog(null, " buscarIdPersona  " + ex);

            }
        }
        return idPersona;
    }

    public String buscarIdPersonaModificada() {
        String idPersona = "";
        if (this.nombresModificados.length <= 1) {
            JOptionPane.showMessageDialog(null, "ingrese dos nombres");
        } else {
            try {
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                String consulta = "SELECT id_per,nom_per,ape_per FROM personas WHERE LOWER(nom_per) = ? AND LOWER(ape_per) = ?";
                PreparedStatement declaración = cn.prepareStatement(consulta);
                declaración.setString(1, this.nombresModificados[0].toLowerCase());
                declaración.setString(2, this.nombresModificados[1].toLowerCase());
                ResultSet resultado = declaración.executeQuery();
                if (resultado.next()) {
                    idPersona = resultado.getString("id_per");
                }
            } catch (SQLException ex) {
                // System.out.println(ex);
                JOptionPane.showMessageDialog(null, " buscarIdPersona  " + ex);

            }
        }
        return idPersona;
    }

    private void guardarReserva() {

        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String idAula = buscarIdAula();
            String idPersona = buscarIdPersona();
            String sql = "insert into reservas (id_per_reserv,id_lab_reser,fecha_reserv,hor_reserv,hora_fin_reserv,desc_reser)values(?,?,?,?,?,?)";
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, idPersona);
            psd.setString(2, idAula);
            psd.setString(3, this.fecha);
            psd.setString(4, this.jtxtHoraInicio.getText());
            psd.setString(5, obtenerHoraFinal());
            psd.setString(6, this.jtxtaDescripcion.getText().toLowerCase());

            int num = psd.executeUpdate();
            if (num != 0) {
                JOptionPane.showMessageDialog(null, "Se guardo la reserva");
                this.vistHorario.actualizarDatos();
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "guardarReserva  " + ex);

            // JOptionPane.showMessageDialog(null, "Verifique los datos que desea guardar");
        }
    }

    private void guardarPersona() {
        String[] nombres = this.jtxtNombres.getText().split(" ");
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String sql = "insert into personas (ced_per,nom_per,ape_per,fech_nac,telf_per,dir_per)values(?,?,?,?,?,?)";
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, "000000");
            psd.setString(2, nombres[0]);
            psd.setString(3, nombres[1]);
            psd.setString(4, "estudiante");
            psd.setString(5, "estudiante");
            psd.setString(6, "estudiante");
            int num = psd.executeUpdate();
            if (num != 0) {
                this.vistHorario.actualizarDatos();
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los datos que desea guardar");
        }

    }

    private String obtenerHoraFinal() {
        String h = this.jcmbHorasDisponibles.getSelectedItem().toString();
        h = h.split(":")[0];
        return String.valueOf(Integer.parseInt(h));
    }

    public void editarReserva() {
        int op = JOptionPane.showConfirmDialog(null, "Desea editar la reserva", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            String[] nombres = this.jtxtNombres.getText().split(" ");
            try {
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                String sql = "update reservas set desc_reser='" + this.jtxtaDescripcion.getText() + "' where id_reser= '" + this.id + "'";
                PreparedStatement psd = cn.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se actualizo la infomacion de la reserva");
                    this.vistHorario.actualizarDatos();
                    this.dispose();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Verifique los datos que desea editar");
            }

        }

    }

    public void editarPersona() {

        String[] nombres = this.jtxtNombres.getText().split(" ");

        String idPersonas = buscarIdPersonaModificada();
        try {
            Conexiones cc = new Conexiones();
            Connection cn = cc.conectar();
            String sql = "update personas set nom_per='" + nombres[0] + "',ape_per='" + nombres[1] + "' where id_per= '" + idPersonas + "'";
            PreparedStatement psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se actualizo la infomacion de la reserva");
                this.vistHorario.actualizarDatos();
                this.dispose();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los datos que desea editar");
        }

    }

    public boolean verificarDatos() {
        String[] nombres = this.jtxtNombres.getText().split(" ");
        if (nombres.length <= 1) {
            JOptionPane.showMessageDialog(null, "ingrese dos nombres");
        } else {
            try {
                Conexiones cc = new Conexiones();
                Connection cn = cc.conectar();
                String consulta = "SELECT nom_per,ape_per FROM personas WHERE LOWER(nom_per) = ? AND LOWER(ape_per) = ?";
                PreparedStatement declaración = cn.prepareStatement(consulta);
                declaración.setString(1, nombres[0].toLowerCase());
                declaración.setString(2, nombres[1].toLowerCase());
                ResultSet resultado = declaración.executeQuery();
                if (resultado.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return false;
    }

    public void verificacionAccion() {
        if (this.id == null) {
            this.jbtnReservar.setText("Reservar");
            asignarHorasDisponibles(this.horainico, this.horasTotales);
        } else {
            this.jtxtNombres.setText(this.datos.get(0) + " " + this.datos.get(1));
            this.jtxtaDescripcion.setText(this.datos.get(2));
            this.jtxtHoraInicio.setText(this.datos.get(3));
            this.jcmbHorasDisponibles.addItem(this.datos.get(4));
            this.jbtnReservar.setText("Editar");
            this.nombresModificados = this.jtxtNombres.getText().split(" ");
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
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jLabel1.setText("Hora de fin:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jtxtaDescripcion.setColumns(20);
        jtxtaDescripcion.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jtxtaDescripcion.setRows(5);
        jtxtaDescripcion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(jtxtaDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 490, 160));

        jLabel2.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jtxtNombres.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jPanel1.add(jtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 310, 40));

        jbtnReservar.setBackground(new java.awt.Color(25, 134, 191));
        jbtnReservar.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnReservar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnReservar.setText("Reservar");
        jbtnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReservarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnReservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 170, 50));

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

        jLabel5.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jLabel5.setText("Descripción:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        jcmbHorasDisponibles.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jcmbHorasDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbHorasDisponiblesActionPerformed(evt);
            }
        });
        jPanel1.add(jcmbHorasDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 160, 40));

        jLabel6.setFont(new java.awt.Font("Microsoft Uighur", 1, 36)); // NOI18N
        jLabel6.setText("Hora de inicio: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jtxtHoraInicio.setFont(new java.awt.Font("Microsoft Uighur", 1, 24)); // NOI18N
        jPanel1.add(jtxtHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 160, 40));

        jbtnCancelar.setBackground(new java.awt.Color(110, 7, 7));
        jbtnCancelar.setFont(new java.awt.Font("Microsoft Uighur", 1, 30)); // NOI18N
        jbtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, 170, 50));

        jPanel4.setBackground(new java.awt.Color(110, 7, 7));

        jLabel3.setFont(new java.awt.Font("Microsoft Uighur", 1, 50)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Reserva");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(253, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(250, 250, 250))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 30, 620, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReservarActionPerformed
        if (this.id == null) {
            if (controlIngresosValidos()) {
                if (verificarDatos()) {
                    guardarReserva();
                } else {
                    int mensaje = JOptionPane.showConfirmDialog(null, "¿El nombre registrado no corresponde a ningun\nregistro previo desea registrarlo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (mensaje == JOptionPane.YES_OPTION) {
                        guardarPersona();
                        guardarReserva();
                    }
                }
            }
        } else {
            if (controlIngresosValidos()) {
                editarReserva();
                editarPersona();

            }
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPnl_min;
    public javax.swing.JPanel jPnl_salida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnReservar;
    private javax.swing.JComboBox<String> jcmbHorasDisponibles;
    private javax.swing.JTextField jtxtHoraInicio;
    private javax.swing.JTextField jtxtNombres;
    public javax.swing.JTextArea jtxtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
