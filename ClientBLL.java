package bll;

import bll.validators.Validator;
import dao.ClientDAO;
import models.Client;
import java.util.List;

/**
 * Clasa care implementează logica operațiunilor legate de clienți.
 */
public class ClientBLL {
    private Validator<Client> validator;
    private ClientDAO clientDAO;

    /**
     * Constructor pentru clasa ClientBLL.
     *
     * @param validator Validatorul pentru clienți.
     * @param clientDAO DAO-ul pentru clienți responsabil cu operațiunile asupra tabelului Client.
     */
    public ClientBLL(Validator<Client> validator, ClientDAO clientDAO) {
        this.validator = validator;
        this.clientDAO = clientDAO;
    }

    /**
     * Metoda care returnează toți clienții din tabelul Client.
     *
     * @return Lista de clienți din tabelul Client.
     */
    public List<Client> findAll() {
        return this.clientDAO.findAll();
    }

    /**
     * Metoda care returnează clientul cu ID-ul specificat.
     *
     * @param id ID-ul după care se caută clientul în tabelul Client.
     * @return Clientul cu ID-ul căutat.
     * @throws Exception Dacă nu există un client cu ID-ul dat.
     */
    public Client findById(int id) throws Exception {
        Client client = this.clientDAO.findById(id);
        if (client == null)
            throw new Exception("Nu există niciun client cu acest ID!");

        return client;
    }

    /**
     * Metoda în care se adaugă un client în tabelul Client.
     *
     * @param client Clientul care trebuie adăugat în tabelul Client.
     * @return Clientul care a fost adăugat.
     * @throws Exception Dacă există deja un client cu același ID.
     */
    public Client insert(Client client) throws Exception {
        this.validator.validate(client);

        if (this.clientDAO.findById(client.getId()) != null)
            throw new Exception("Există deja un client cu acest ID!");

        return this.clientDAO.insert(client);
    }

    /**
     * Metoda în care se șterge un client din tabelul Client.
     *
     * @param client Clientul care trebuie șters din tabelul Client.
     * @return Clientul care a fost șters.
     * @throws Exception Dacă clientul respectiv nu există.
     */
    public Client delete(Client client) throws Exception {
        if (this.clientDAO.findById(client.getId()) == null)
            throw new Exception("Nu există acest client!");

        Client rezultat = this.clientDAO.delete(client);

        return rezultat;
    }

    /**
     * Metoda în care se modifică un client în tabelul Client.
     *
     * @param client Clientul care trebuie modificat în tabelul Client.
     * @return Clientul care a fost modificat.
     * @throws Exception Dacă clientul respectiv nu poate fi modificat.
     */
    public Client update(Client client) throws Exception {
        Client rezultat = this.findById(client.getId());
        if (rezultat == null)
            throw new Exception("Nu se poate face actualizarea acestui client!");

        return this.clientDAO.update(client);
    }
}
