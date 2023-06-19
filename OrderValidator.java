package bll.validators;

import models.Order;

/**
 * Clasa responsabilă de validarea datelor primite de pe interfața pentru comandă.
 */
public class OrderValidator implements Validator<Order> {

    /**
     * Validează atributele specifice unei comenzi.
     *
     * @param order Comanda pentru care se vor efectua validările.
     * @throws Exception Dacă datele comenzii sunt invalide.
     */
    @Override
    public void validate(Order order) throws Exception {

        if (order.getCantitate() < 1) {
            throw new Exception("Cantitatea comenzii este invalidă!");
        }
    }
}
