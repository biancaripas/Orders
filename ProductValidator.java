package bll.validators;

import models.Product;

/**
 * Clasa responsabilă de validarea datelor primite de pe interfața pentru produs.
 */
public class ProductValidator implements Validator<Product> {

    /**
     * Validează atributele specifice unui produs.
     *
     * @param product Produsul pentru care se vor efectua validările.
     * @throws Exception Dacă datele produsului sunt invalide.
     */
    @Override
    public void validate(Product product) throws Exception {

        if (product.getNume().equals("")) {
            throw new Exception("Numele produsului este invalid!");
        }

        if (product.getPret() < 1) {
            throw new Exception("Prețul produsului este invalid!");
        }

        if (product.getCantitate() < 1) {
            throw new Exception("Cantitatea produsului este invalidă!");
        }
    }
}
