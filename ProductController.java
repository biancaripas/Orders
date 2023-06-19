package controllers;

import bll.ProductBLL;
import models.Product;
import views.ProductView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa in care se realizeaza legatura cu view-ul corespunzator ferestrei Product
 */
public class ProductController {
    private ProductView view;
    private ProductBLL productBLL;

    /**
     * Constructor pentru clasa ProductController
     *
     * @param view       View-ul pentru clienti
     * @param productBLL Bll-ul pentru produse
     */
    public ProductController(ProductView view, ProductBLL productBLL) {
        this.view = view;
        this.productBLL = productBLL;
        this.view.addAfisareListener(new AfisareListener());
        this.view.addAdaugareListener(new AdaugareListener());
        this.view.addStergereListener(new StergereListener());
        this.view.addModificareListener(new ModificareListener());
    }

    /**
     * Metoda pentru creearea capurilor de tabel
     *
     * @param objectList Lista de obiecte din care se va crea capul de tabel
     * @param table      Tabelul obtinut
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
     * Clasa pentru implementarea afisarii datelor din tabela Product in interfata corespunzatoare
     */
    class AfisareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Product> products = productBLL.findAll();
                List<Object> objects = new ArrayList<>();
                objects.addAll(products);
                reflectionTableHeaders(objects, view.getTable());
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Clasa pentru implementarea inserarii datelor in tabela Product in interfata corespunzatoare
     */
    class AdaugareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                String nume = view.getNumeTextField().getText();
                int pret = Integer.parseInt(view.getPretTextField().getText());
                int cantitate = Integer.parseInt(view.getCantitateTextField().getText());
                Product product = new Product(id, nume, pret, cantitate);
                productBLL.insert(product);
                JOptionPane.showMessageDialog(null, "Produs adaugat !");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Id invalid!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    /**
     * Clasa pentru implementarea stergerea datelor din tabela Produs in interfata corespunzatoare
     */
    class StergereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                Product product = new Product(id, null, -1, -1);
                productBLL.delete(product);
                JOptionPane.showMessageDialog(null, "Produs sters!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Id invalid!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    /**
     * Clasa pentru implementarea modificarii datelor din tabela Product in interfata corespunzatoare
     */
    class ModificareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                String nume = view.getNumeTextField().getText();
                int pret = Integer.parseInt(view.getPretTextField().getText());
                int cantitate = Integer.parseInt(view.getCantitateTextField().getText());
                Product product = new Product(id, nume, pret, cantitate);
                productBLL.update(product);
                JOptionPane.showMessageDialog(null, "Produs modificat!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Id invalid!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
