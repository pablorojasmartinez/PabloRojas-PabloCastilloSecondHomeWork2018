/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Car;
import file.CarFile;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo Rojas Martínez
 */
public class Show extends JInternalFrame {

    JTable jtbPrueba;
    DefaultTableModel dtmModeloPrueba;
    JDesktopPane desktopPane;

    public Show() throws IOException {
        super();
        this.setSize(800, 800);
        init();
        this.setTitle("SHOW");
        this.setVisible(true);
        this.setClosable(true);
    } // constructor

    private void init() throws IOException {
        CarFile archivo = new CarFile();
        ArrayList<Car> archi = archivo.getAllCars();

        this.dtmModeloPrueba = new DefaultTableModel();
        this.dtmModeloPrueba.addColumn("Name");
        this.dtmModeloPrueba.addColumn("Id");
        this.dtmModeloPrueba.addColumn("American");
        this.dtmModeloPrueba.addColumn("Year");
        this.dtmModeloPrueba.addColumn("Mileage");
        String vector2[];

        for (int i = 0; i < archi.size(); i++) {
            if (archi.get(i) != null) {
                this.dtmModeloPrueba.addRow(new Object[]{archi.get(i).getName(), archi.get(i).getId(), archi.get(i).getAmerican(), archi.get(i).getYear(), archi.get(i).getMileage()});
            }
        }

        this.jtbPrueba = new JTable(this.dtmModeloPrueba);
        JScrollPane scrollPane = new JScrollPane(this.jtbPrueba);
        this.getContentPane().add(scrollPane);

    } // init

}
