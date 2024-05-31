/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Dalex
 */
public class utcJTable extends JTable {

    DefaultTableModel modelotabla = new DefaultTableModel(new Object[]{"", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"}, 12);
    JPopupMenu jppmMenu = new JPopupMenu();
    JMenuItem jitmReserva = new JMenuItem("Reservar");
    JMenuItem jitmEliminarReserva = new JMenuItem(" Eliminar reserva");
    JMenuItem jitmModfificarReserva = new JMenuItem(" Modificar reserva");

    public utcJTable() {

        Font font = new Font("Lucida fax", Font.PLAIN, 16); // Por ejemplo, Arial, negrita, tamaño 16
        this.jitmReserva.setFont(font);
        this.jitmEliminarReserva.setFont(font);
        this.jitmModfificarReserva.setFont(font);
        this.setModel(modelotabla);
        this.jppmMenu.add(jitmReserva);
        this.jppmMenu.add(jitmModfificarReserva);
        this.jppmMenu.add(jitmEliminarReserva);
        this.setComponentPopupMenu(jppmMenu);
        this.setDefaultEditor(Object.class, null); // Esto deshabilita la edición de celdas
        this.setCellSelectionEnabled(true);
        horasDia();
        cambiarTamanioCeldasAncho();
        cambiarTamanioCeldasLargo();
        aplicarEstilos();

    }
    private void aplicarEstilos() {
    DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            if (value != null) {
                label.setText("<html>" + value.toString().replaceAll("\n", "<br>"));
            } else {
                label.setText(""); 
            }
            
            return label;
        }
    };

    for (int i = 0; i < this.getColumnCount(); i++) {
        this.getColumnModel().getColumn(i).setCellRenderer(renderizador);
    }
}


    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int fila, int columna) {
        Component componente = super.prepareRenderer(renderer, fila, columna);
        Object valorCelda = getValueAt(fila, columna);
        String valor = String.valueOf(valorCelda);
        if (valor != null && valor.contains("reserva")) {
            componente.setBackground(new Color(210, 247, 175));
            componente.setForeground(Color.black);
        } else {
            componente.setBackground(getBackground());
        }
        return componente;
    }

   
    private void horasDia() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setValueAt("7:00 - 8:00", 0, 0);
        this.setValueAt("8:00 - 9:00", 1, 0);
        this.setValueAt("9:00 - 10:00", 2, 0);
        this.setValueAt("10:00 - 11:00", 3, 0);
        this.setValueAt("11:00 - 12:00", 4, 0);
        this.setValueAt("12:00 - 13:00", 5, 0);
        this.setValueAt("14:00 - 15:00", 6, 0);
        this.setValueAt("15:00 - 16:00", 7, 0);
        this.setValueAt("16:00 - 17:00", 8, 0);
        this.setValueAt("17:00 - 18:00", 9, 0);
        this.setValueAt("18:00 - 19:00", 10, 0);
        this.setValueAt("19:00 - 20:00", 11, 0);

    }

    private void cambiarTamanioCeldasLargo() {
        for (int i = 0; i < this.getColumnCount() - 1; i++) {
            this.getColumnModel().getColumn(i + 1).setPreferredWidth(160);

        }
        this.getColumnModel().getColumn(0).setPreferredWidth(100);
    }

    private void cambiarTamanioCeldasAncho() {
        for (int i = 0; i < this.getRowCount(); i++) {
            this.setRowHeight(i + 1, 85);

        }
        this.setRowHeight(0, 70);

    }

}
