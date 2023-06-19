package bll;

import bll.validators.Validator;
import dao.ProductDAO;
import models.Product;

import java.util.List;

/**
 * Clasa responsabilă de implementarea logicii operațiilor pentru produs.
 */
public class ProductBLL {

    private Validator<Product> validator;

    private ProductDAO productDAO;

    /**
     * Constructor pentru clasa ProductBLL.
     *
     * @param validator  Validatorul pentru produs.
     * @param productDAO DAO-ul pentru produs responsabil de operațiile pe tabela Product.
     */
    public ProductBLL(Validator<Product> validator, ProductDAO productDAO) {
        this.validator = validator;
        this.productDAO = productDAO;
    }

    /**
     * Metoda care returnează toate produsele din tabela Product.
     *
     * @return Lista de produse din tabela Product.
     */
    public List<Product> findAll() {
        return this.productDAO.findAll();
    }

    /**
     * Metoda care returnează produsul cu ID-ul corespunzător.
     *
     * @param id ID-ul după care se caută produsul în tabela Product.
     * @return Produsul cu ID-ul căutat.
     * @throws Exception Dacă nu există un produs cu ID-ul dat ca parametru.
     */
    public Product findById(int id) throws Exception {
        Product product = this.productDAO.findById(id);

        if (product == null)
            throw new Exception("Nu există niciun produs cu acest ID!");

        return product;
    }

    /**
     * Metoda în care se adaugă un produs în tabela Product.
     *
     * @param product Produsul care trebuie să fie adăugat în tabela Product.
     * @return Produsul care a fost adăugat.
     * @throws Exception Dacă există deja un produs cu acel Id sau dacă datele produsului sunt invalide.
     */
    public Product insert(Product product) throws Exception {
        this.validator.validate(product);

        if (this.productDAO.findById(product.getId()) != null)
            throw new Exception("Există deja un produs cu acest Id!");

        return this.productDAO.insert(product);
    }

    /**
     * Metoda în care se șterge un produs din tabela Product.
     *
     * @param product Produsul care trebuie să fie șters din tabela Product.
     * @return Produsul care a fost șters.
     * @throws Exception Dacă nu există produsul respectiv.
     */
    public Product delete(Product product) throws Exception {
        if (this.productDAO.findById(product.getId()) == null)
            throw new Exception("Nu există acest produs!");

        Product rezultat = this.productDAO.delete(product);

        return rezultat;
    }

    /**
     * Metoda în care se modifică un produs în tabela Product.
     *
     * @param product Produsul care trebuie să fie modificat în tabela Product.
     * @return Produsul care a fost modificat.
     * @throws Exception Dacă produsul respectiv nu poate fi modificat.
     */

    public Product update(Product product) throws Exception {

        Product rezultat = this.findById(product.getId());

        if (rezultat == null)
            throw new Exception("Nu se poate face update la acest product!");

        return this.productDAO.update(product);
    }
}
