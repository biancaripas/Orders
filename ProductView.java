package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa in care se defineste interfata corespunzatoare tabelei Product impreuna cu operatiile specifice ei.
 */
public class ProductView extends JFrame {

    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel numeLabel;
    private JLabel pretLabel;
    private JLabel cantitateLabel;
    private JLabel dateLabel;

    private JTable produsTable;
    private JTextField idTextField;
    private JTextField numeTextField;
    private JTextField pretTextField;
    private JTextField cantitateTextField;

    private JButton adaugaButton;
    private JButton stergeButton;
    private JButton modificaButton;
    private JButton afisareButton;

    private JScrollPane tableScroll;

    /**
     * Constructor pentr clasa ProductView
     */
    public ProductView() {

        // Marginile ferestrei interfetei
        this.setBounds(200, 200, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        // Etichete
        titleLabel = new JLabel("Produs");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(300, 20, 200, 50);
        titleLabel.setForeground(Color.BLUE);
        this.getContentPane().add(titleLabel);

        idLabel = new JLabel("Id:");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        idLabel.setBounds(50, 100, 100, 30);
        idLabel.setForeground(Color.RED);
        this.getContentPane().add(idLabel);

        numeLabel = new JLabel("Nume:");
        numeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        numeLabel.setBounds(50, 150, 100, 30);
        numeLabel.setForeground(Color.GREEN);
        this.getContentPane().add(numeLabel);

        pretLabel = new JLabel("Pret:");
        pretLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pretLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        pretLabel.setBounds(50, 200, 100, 30);
        pretLabel.setForeground(Color.ORANGE);
        this.getContentPane().add(pretLabel);

        cantitateLabel = new JLabel("Cantitate:");
        cantitateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cantitateLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        cantitateLabel.setBounds(50, 250, 100, 30);
        cantitateLabel.setForeground((Color.yellow));
        this.getContentPane().add(cantitateLabel);

        dateLabel = new JLabel("Date:Produs");
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        dateLabel.setBounds(120, 20, 150, 50);
        dateLabel.setForeground(Color.MAGENTA);
        this.getContentPane().add(dateLabel);

        // Casete text
        idTextField = new JTextField();
        idTextField.setBounds(160, 100, 150, 30);
        this.getContentPane().add(idTextField);
        idTextField.setColumns(10);

        numeTextField = new JTextField();
        numeTextField.setColumns(10);
        numeTextField.setBounds(160, 150, 150, 30);
        this.getContentPane().add(numeTextField);

        pretTextField = new JTextField();
        pretTextField.setColumns(10);
        pretTextField.setBounds(160, 200, 150, 30);
        this.getContentPane().add(pretTextField);

        cantitateTextField = new JTextField();
        cantitateTextField.setColumns(10);
        cantitateTextField.setBounds(160, 250, 150, 30);
        this.getContentPane().add(cantitateTextField);

        // Tabel
        produsTable = new JTable();
        this.tableScroll = new JScrollPane(this.produsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.tableScroll.setBounds(350, 100, 400, 250);
        this.tableScroll.setViewportView(this.produsTable);
        this.getContentPane().add(this.tableScroll);

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


    public JTextField getPretTextField() {
        return pretTextField;
    }

    /**
     * Metoda de get() pentru caseta cantitate pe stoc
     *
     * @return caseta cantitate pe stoc
     */
    public JTextField getCantitateTextField() {
        return cantitateTextField;
    }

    /**
     * Metoda de get() pentru tabel
     *
     * @return tabelul
     */
    public JTable getTable() {

        return produsTable;
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
