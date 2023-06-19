package models;

/**
 * Clasa care contine atributele tabelei Order.
 */
public class Order {

    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;

    /**
     * Constructor pentru clasa Order
     *
     * @param id        Id-ul comenzii
     * @param idClient  Id-ul clientului
     * @param idProdus  Id-ul produsului
     * @param cantitate cantitatea produsului comandat
     */
    public Order(int id, int idClient, int idProdus, int cantitate) {
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    /**
     * Metoda de get() pentru Id
     *
     * @return Id-ul
     */
    public int getId() {
        return id;
    }

    /**
     * Metoda de set() pentru Id
     *
     * @param id Id-ul initial
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metoda de get() pentru IdClient
     *
     * @return IdClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Metoda de get() pentru IdProdus
     *
     * @return IdProdus
     */
    public int getIdProdus() {
        return idProdus;
    }

    /**
     * Metoda de get() pentru cantitate
     *
     * @return cantitatea
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * Metoda de set() pentru cantitate
     *
     * @param cantitate cantitatea initiala
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
