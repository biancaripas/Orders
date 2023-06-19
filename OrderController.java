package controllers;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import models.Client;
import models.Order;
import models.Product;
import views.OrderView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa in care se realizeaza legatura cu view-ul corespunzator ferestrei Order
 */
public class OrderController {

    private OrderView view;
    private OrderBLL orderBLL;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;

    /**
     * Constructor pentru clasa OrderController
     *
     * @param view       View-ul pentru comenzi
     * @param clientBLL  Bll-ul pentru clienti
     * @param productBLL Bll-ul pentru produse
     * @param orderBLL   Bll-ul pentru comenzi
     */
    public OrderController(OrderView view, ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.view = view;
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.orderBLL = orderBLL;
        this.view.addAfisareListener(new AfisareListener());
        this.view.addAdaugareListener(new AdaugareListener());
    }

    /**
     * Metoda pentru creearea capurilor de tabel
     *
     * @param objectList Lista de obiecte din care se va crea capul de tabel
     * @param table      tabelul obtinut
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
     * Clasa pentru implementarea afisarii datelor din tabela Order in interfata corespunzatoare
     */
    class AfisareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Client> clients = clientBLL.findAll();
                List<Object> objects = new ArrayList<>();
                objects.addAll(clients);
                reflectionTableHeaders(objects, view.getClientTable());
                List<Product> products = productBLL.findAll(); // lista produselor din tabela Product
                objects = new ArrayList<>();
                objects.addAll(products);
                reflectionTableHeaders(objects, view.getProdusTable()); // adaugam in tabel
                List<Order> orders = orderBLL.findAll(); // lista comenzilor din tabela Order
                objects = new ArrayList<>();
                objects.addAll(orders);
                reflectionTableHeaders(objects, view.getOrderTable());
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Clasa pentru implementarea inserarii datelor in tabela Order in interfata corespunzatoare
     */
    class AdaugareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getIdTextField().getText());
                int cantitate = Integer.parseInt(view.getCantitateTextField().getText());
                int randSelectatClienti = view.getClientTable().getSelectedRow();
                int randSelectatProduse = view.getProdusTable().getSelectedRow();
                String idClientString = view.getClientTable().getModel().getValueAt(randSelectatClienti, 0).toString();
                String idProdusString = view.getProdusTable().getModel().getValueAt(randSelectatProduse, 0).toString();
                int idClient = Integer.parseInt(idClientString);
                int idProdus = Integer.parseInt(idProdusString);
                Order order = new Order(id, idClient, idProdus, cantitate);
                orderBLL.insert(order);
                JOptionPane.showMessageDialog(null, "Comanda efectuata!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Id sau Cantitate invalida!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
