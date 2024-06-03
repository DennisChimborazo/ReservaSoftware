/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Interfaces.VisHorario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Dalex
 */
public class ContReservas implements ActionListener {

    VisHorario vistaHorarios = new VisHorario();

    public ContReservas() {
         
        this.vistaHorarios.jitmReserva.addActionListener(this);
        this.vistaHorarios.jitmEliminarReserva.addActionListener(this);
        this.vistaHorarios.jitmModfificarReserva.addActionListener(this);

    }

    public void inicia() {
        this.vistaHorarios.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaHorarios.jitmReserva) {

            if (this.vistaHorarios.jtblHorarios.getSelectedRow() != 0 && this.vistaHorarios.jtblHorarios.getSelectedColumn() != 0) {
                int filHora = this.vistaHorarios.jtblHorarios.getSelectedRow();
                int columDia = this.vistaHorarios.jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(this.vistaHorarios.jtblHorarios.getValueAt(filHora, columDia));
                if (!valor.contains("reserva")) {
                    JOptionPane.showMessageDialog(null, "OK");

                } else {
                    JOptionPane.showMessageDialog(null, "No puede reversar esta fecha se \n"
                            + " encuentra ya reservada");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido");
            }

        }
        if (e.getSource() == this.vistaHorarios.jitmModfificarReserva) {
            if (this.vistaHorarios.jtblHorarios.getSelectedRow() != 0 && this.vistaHorarios.jtblHorarios.getSelectedColumn() != 0) {
                int filHora = this.vistaHorarios.jtblHorarios.getSelectedRow();
                int columDia = this.vistaHorarios.jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(this.vistaHorarios.jtblHorarios.getValueAt(filHora, columDia));
                if (valor.contains("reserva")) {
                    JOptionPane.showMessageDialog(null, "OK");

                } else {
                    JOptionPane.showMessageDialog(null, "La fecha selecionada \nno se puede modificar");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido");
            }

        }

        if (e.getSource() == this.vistaHorarios.jitmEliminarReserva) {
            if (this.vistaHorarios.jtblHorarios.getSelectedRow() != 0 && this.vistaHorarios.jtblHorarios.getSelectedColumn() != 0) {
                int filHora = this.vistaHorarios.jtblHorarios.getSelectedRow();
                int columDia = this.vistaHorarios.jtblHorarios.getSelectedColumn();
                String valor = String.valueOf(this.vistaHorarios.jtblHorarios.getValueAt(filHora, columDia));
                if (valor.contains("reserva")) {
                    JOptionPane.showMessageDialog(null, "OK");

                } else {
                    JOptionPane.showMessageDialog(null, "La fecha selecionada \nno se puede elinimar");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor selecione un horario valido");
            }

        }

    }
}
