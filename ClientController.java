package controllers;

import bll.ClientBLL;
import models.Client;
import views.ClientView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa responsabilă de realizarea legăturii cu view-ul corespunzător ferestrei Client.
 */
public class ClientController {

    private ClientView view;

    private ClientBLL clientBLL;

    /**
     * Constructor pentru clasa ClientController.
     *
     * @param view      View-ul pentru clienți.
     * @param clientBLL BLL-ul pentru clienți.
     */
    public ClientController(ClientView view, ClientBLL clientBLL) {
        this.view = view;
        this.clientBLL = clientBLL;
        this.view.addAfisareListener(new AfisareListener());
        this.view.addAdaugareListener(new AdaugareListener());
        this.view.addStergereListener(new StergereListener());
        this.view.addModificareListener(new ModificareListener());
    }

    /**
     * Metoda pentru crearea capului de tabel.
     *
     * @param objectList Lista de obiecte din care se va crea capul de tabel.
     * @param table      Tabelul obținut.
     */
    private void reflectionTableHeaders(List<Object> objectList, JTable table) {
        DefaultTableModel tableModel = new DefaultTableModel();
        if (!objectList.isEmpty()) {
            Object obj = objectList.get(0);
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                tableModel.addColumn(fieldName);
            }
            for (Object o : objectList) {
                List<Object> rowData = new ArrayList<>();

                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(o);
                        rowData.add(value);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
                tableModel.addRow(rowData.toArray());
            }
        }

        table.setModel(tableModel);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
    }



    /**
     * Clasa pentru implementarea afisarii datelor din tabela Client in interfata corespunzatoare
     */
    class AfisareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Client> clients = clientBLL.findAll();
                List<Object> objects = new ArrayList<>();
                objects.addAll(clients);
                reflectionTableHeaders(objects, view.getTable());
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Clasa pentru implementarea inserarii datelor in tabela Client in interfata corespunzatoare
     */
    class AdaugareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                String nume = view.getNumeTextField().getText();
                String prenume = view.getPrenumeTextField().getText();
                Client client = new Client(id, nume, prenume);
                clientBLL.insert(client);
                JOptionPane.showMessageDialog(null, "Client adaugat!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalid!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    /**
     * Clasa pentru implementarea stergerea datelor din tabela Client in interfata corespunzatoare
     */
    class StergereListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                String nume = view.getNumeTextField().getText();
                String prenume = view.getPrenumeTextField().getText();
                Client client = new Client(id, nume, prenume);
                clientBLL.delete(client);
                JOptionPane.showMessageDialog(null, "Client sters!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalid!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    /**
     * Clasa pentru implementarea modificarii datelor din tabela Client in interfata corespunzatoare
     */
    class ModificareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                String nume = view.getNumeTextField().getText();
                String prenume = view.getPrenumeTextField().getText();
                Client client = new Client(id, nume, prenume);
                clientBLL.update(client);
                JOptionPane.showMessageDialog(null, "Client modificat!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalid!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
