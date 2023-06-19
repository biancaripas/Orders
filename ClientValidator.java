package bll.validators;

import models.Client;

/**
 * Clasa responsabilă de validarea datelor primite de pe interfața pentru client.
 */
public class ClientValidator implements Validator<Client> {

    /**
     * Validează atributele specifice unui client.
     *
     * @param client Clientul pentru care se vor efectua validările.
     * @throws Exception Dacă datele clientului sunt invalide.
     */
    @Override
    public void validate(Client client) throws Exception {

        if (client.getNume().equals("")) {
            throw new Exception("Numele clientului este invalid!");
        }

        if (client.getPrenume().equals("")) {
            throw new Exception("Prenumele clientului este invalid!");
        }
    }
}
