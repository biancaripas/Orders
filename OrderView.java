package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa in care se defineste interfata corespunzatoare tabelei Order impreuna cu operatiile specifice ei.
 */
public class OrderView extends JFrame {

    private JLabel clientLabel;
    private JLabel produsLabel;
    private JLabel orderLabel;
    private JLabel idLabel;
    private JLabel cantitateLabel;

    private JTextField idTextField;
    private JTextField cantitateTextField;

    private JTable clientTable;
    private JTable produsTable;
    private JTable orderTable;

    private JScrollPane clientScroll;
    private JScrollPane produsScroll;
    private JScrollPane orderScroll;

    private JButton adaugaButton;
    private JButton afisareButton;

    /**
     * Constructor pentr clasa OrderView
     */
    public OrderView() {

        // Marginile ferestrei interfei
        this.setBounds(100, 100, 1100, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        // Etichete
        clientLabel = new JLabel("Client");
        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        clientLabel.setBounds(29, 43, 171, 46);
        clientLabel.setForeground(Color.BLUE);
        this.getContentPane().add(clientLabel);

        produsLabel = new JLabel("Produs");
        produsLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        produsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        produsLabel.setBounds(29, 179, 171, 46);
        produsLabel.setForeground(Color.BLUE);
        this.getContentPane().add(produsLabel);

        idLabel = new JLabel("Id:");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        idLabel.setBounds(326, 403, 119, 36);
        idLabel.setForeground(Color.RED);
        this.getContentPane().add(idLabel);

        cantitateLabel = new JLabel("Cantitate:");
        cantitateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cantitateLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        cantitateLabel.setBounds(326, 444, 119, 36);
        cantitateLabel.setForeground((Color.yellow));
        this.getContentPane().add(cantitateLabel);

        orderLabel = new JLabel("Comenzi");
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        orderLabel.setBounds(29, 305, 171, 46);
        orderLabel.setForeground(Color.BLUE);
        this.getContentPane().add(orderLabel);

        // Casete text
        idTextField = new JTextField();
        idTextField.setFont(new Font("Serif", Font.PLAIN, 15));
        idTextField.setColumns(10);
        idTextField.setBounds(456, 401, 156, 40);
        this.getContentPane().add(idTextField);

        cantitateTextField = new JTextField();
        cantitateTextField.setFont(new Font("Serif", Font.PLAIN, 15));
        cantitateTextField.setColumns(10);
        cantitateTextField.setBounds(456, 442, 156, 40);
        this.getContentPane().add(cantitateTextField);

        // Tabele
        clientTable = new JTable();
        this.clientScroll = new JScrollPane(this.clientTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.clientScroll.setBounds(223, 21, 601, 111);
        this.clientScroll.setViewportView(this.clientTable);
        this.getContentPane().add(this.clientScroll);

        produsTable = new JTable();
        this.produsScroll = new JScrollPane(this.produsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.produsScroll.setBounds(222, 155, 602, 111);
        this.produsScroll.setViewportView(this.produsTable);
        this.getContentPane().add(this.produsScroll);

        orderTable = new JTable();
        this.orderScroll = new JScrollPane(this.orderTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.orderScroll.setBounds(221, 287, 603, 94);
        this.orderScroll.setViewportView(this.orderTable);
        this.getContentPane().add(this.orderScroll);

        // Butoane
        adaugaButton = new JButton("Adauga");
        adaugaButton.setFont(new Font("Serif", Font.PLAIN, 15));
        adaugaButton.setBounds(651, 420, 156, 38);
        this.getContentPane().add(adaugaButton);

        afisareButton = new JButton("Afisare");
        afisareButton.setFont(new Font("Serif", Font.PLAIN, 15));
        afisareButton.setBounds(890, 48, 156, 38);
        this.getContentPane().add(afisareButton);
        this.setVisible(true);
    }

    /**
     * Metoda de get() pentru caseta ID
     *
     * @return caseta ID
     */
    public JTextField getIdTextField() {
        return idTextField;
    }

    /**
     * Metoda de get() pentru caseta cantitate
     *
     * @return caseta cantitate
     */
    public JTextField getCantitateTextField() {
        return cantitateTextField;
    }

    /**
     * Metoda de get() pentru tabel clienti
     *
     * @return tabelul clienti
     */
    public JTable getClientTable() {
        return clientTable;
    }

    /**
     * Metoda de get() pentru tabel produse
     *
     * @return tabelul produselor
     */
    public JTable getProdusTable() {
        return produsTable;
    }

    /**
     * Metoda de get() pentru tabelul comenzilor
     *
     * @return tabelul comenzilor
     */
    public JTable getOrderTable() {
        return orderTable;
    }

    /**
     * Metoda de adaugare a unui listener pe butonul de afisare
     *
     * @param actionListener actionListener
     */
    public void addAfisareListener(ActionListener actionListener) {

        this.afisareButton.addActionListener(actionListener);
    }

    /**
     * Metoda de adaugare a unui listener pe butonul de adaugare
     *
     * @param actionListener actionListener
     */
    public void addAdaugareListener(ActionListener actionListener) {

        this.adaugaButton.addActionListener(actionListener);
    }
}
