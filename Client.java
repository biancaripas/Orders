package models;

/**
 * Clasa care contine atributele tabelei Client.
 */
public class Client {
    private int id;
    private String nume;
    private String prenume;

    /**
     * Constructor pentru clasa Client
     * @param id ID=d-ul clientului
     * @param nume Numele clientului
     * @param prenume Prenumele  clientului
     */
    public Client(int id, String nume, String prenume) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
    }

    /**
     * Metoda de get() pentru Id
     * @return Id-ul
     */
    public int getId() {
        return id;
    }

    /**
     * Metoda de set() pentru Id
     * @param id Id-ul initial
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metoda de get() pentru Nume
     * @return Numele
     */
    public String getNume() {
        return nume;
    }

    /**
     * Metoda de set() pentru Nume
     * @param nume Numele initial
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Metoda de get() pentru Prenume
     * @return Prenume
     */
    public String getPrenume() {
        return prenume;
    }

    /**
     * Metoda de set() pentru Prenume
     * @param prenume Prenumele initial
     */
    public void SetPrenume(String prenume) {
        this.prenume=prenume;
    }

}
