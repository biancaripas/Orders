package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa in care se defineste interfata corespunzatoare tabelei Client impreuna cu operatiile specifice ei.
 */
public class ClientView extends JFrame {
    private JTextField idTextField;
    private JTextField numeTextField;
    private JTextField prenumeTextField;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel numeLabel;
    private JLabel prenumeLabel;
    private JLabel dateLabel;

    private JTable clientTable;

    private JScrollPane tableScroll;
    private JButton adaugaButton;
    private JButton stergeButton;
    private JButton modificaButton;
    private JButton afisareButton;


    /**
     * Constructor pentru clasa ClientView.
     */
    public ClientView() {

        // Marginile ferestrei interfetei
        this.setBounds(200, 200, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

// Etichete
        titleLabel = new JLabel("Client");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(300, 20, 200, 50);
        titleLabel.setForeground(Color.BLUE);
        this.getContentPane().add(titleLabel);

        idLabel = new JLabel("Id:");
        idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        idLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        idLabel.setBounds(50, 100, 100, 30);
        idLabel.setForeground(Color.RED);
        this.getContentPane().add(idLabel);

        numeLabel = new JLabel("Nume:");
        numeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        numeLabel.setBounds(50, 150, 100, 30);
        numeLabel.setForeground(Color.GREEN);
        this.getContentPane().add(numeLabel);

        prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        prenumeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        prenumeLabel.setBounds(50, 200, 100, 30);
        prenumeLabel.setForeground(Color.ORANGE);
        this.getContentPane().add(prenumeLabel);

        dateLabel = new JLabel("Date: Client");
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        dateLabel.setBounds(120, 20, 150, 50);
        dateLabel.setForeground(Color.MAGENTA);
        this.getContentPane().add(dateLabel);

// Tabel
        clientTable = new JTable();
        this.tableScroll = new JScrollPane(this.clientTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.tableScroll.setBounds(350, 100, 400, 250);
        this.tableScroll.setViewportView(this.clientTable);
        this.getContentPane().add(this.tableScroll);

// Casete text
        idTextField = new JTextField();
        idTextField.setBounds(160, 100, 150, 30);
        this.getContentPane().add(idTextField);
        idTextField.setColumns(10);

        numeTextField = new JTextField();
        numeTextField.setColumns(10);
        numeTextField.setBounds(160, 150, 150, 30);
        this.getContentPane().add(numeTextField);

        prenumeTextField = new JTextField();
        prenumeTextField.setColumns(10);
        prenumeTextField.setBounds(160, 200, 150, 30);
        this.getContentPane().add(prenumeTextField);

// Butoane
        adaugaButton = new JButton("Adauga");
        adaugaButton.setFont(new Font("Serif", Font.PLAIN, 15));
        adaugaButton.setBounds(50, 300, 120, 40);
        this.getContentPane().add(adaugaButton);

        stergeButton = new JButton("Sterge");
        stergeButton.setFont(new Font("Serif", Font.PLAIN, 15));
        stergeButton.setBounds(180, 300, 120, 40);
        this.getContentPane().add(stergeButton);

        modificaButton = new JButton("Modifica");
        modificaButton.setFont(new Font("Serif", Font.PLAIN, 15));
        modificaButton.setBounds(50, 350, 120, 40);
        this.getContentPane().add(modificaButton);

        afisareButton = new JButton("Afisare");
        afisareButton.setFont(new Font("Serif", Font.PLAIN, 15));
        afisareButton.setBounds(180, 350, 120, 40);
        this.getContentPane().add(afisareButton);

        this.setVisible(true);
    }

    /**
     * Metoda de get() pentru tabel
     *
     * @return tabelul
     */
    public JTable getTable() {
        return clientTable;
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
     * Metoda de get() pentru caseta nume
     *
     * @return caseta nume
     */
    public JTextField getNumeTextField() {
        return numeTextField;
    }

    /**
     * Metoda de get() pentru caseta prenume
     *
     * @return caseta prenume
     */
    public JTextField getPrenumeTextField() {
        return prenumeTextField;
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

    /**
     * Metoda de adaugare a unui listener pe butonul de stergere
     *
     * @param actionListener actionListener
     */
    public void addStergereListener(ActionListener actionListener) {

        this.stergeButton.addActionListener(actionListener);
    }

    /**
     * Metoda de adaugare a unui listener pe butonul de modificare
     *
     * @param actionListener actionListener
     */
    public void addModificareListener(ActionListener actionListener) {

        this.modificaButton.addActionListener(actionListener);
    }
}
