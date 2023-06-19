package bll;

import bll.validators.Validator;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import models.Order;
import models.Product;

import java.util.List;

/**
 * Clasa responsabilă de implementarea logicii operațiilor pentru comandă.
 */
public class OrderBLL {
    private Validator<Order> validator;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    /**
     * Constructor pentru clasa OrderBLL.
     *
     * @param validator  Validatorul pentru comandă.
     * @param clientDAO  DAO-ul pentru client responsabil de operațiile pe tabela Client.
     * @param productDAO DAO-ul pentru produs responsabil de operațiile pe tabela Product.
     * @param orderDAO   DAO-ul pentru comandă responsabil de operațiile pe tabela Order.
     */
    public OrderBLL(Validator<Order> validator, ClientDAO clientDAO, ProductDAO productDAO, OrderDAO orderDAO) {
        this.validator = validator;
        this.clientDAO = clientDAO;
        this.productDAO = productDAO;
        this.orderDAO = orderDAO;
    }

    /**
     * Metoda care returnează toate comenzile din tabela Order.
     *
     * @return Lista de comenzi din tabela Order.
     */
    public List<Order> findAll() {
        return this.orderDAO.findAll();
    }

    /**
     * Metoda care returnează comanda cu Id-ul corespunzător.
     *
     * @param id ID-ul după care se caută comanda în tabela Order.
     * @return Comanda cu ID-ul căutat.
     * @throws Exception Dacă nu există o comandă cu Id-ul dat ca parametru.
     */
    public Order findById(int id) throws Exception {
        Order order = this.orderDAO.findById(id);
        if (order == null)
            throw new Exception("Nu există nicio comandă cu acest Id!");
        return order;
    }

    /**
     * Metoda în care se adaugă o comandă în tabela Order.
     *
     * @param order Comanda care trebuie să fie adăugată în tabela Order.
     * @return Comanda care a fost adăugată.
     * @throws Exception Dacă există deja o comandă cu acel Id sau dacă datele comenzii sunt invalide.
     */
    public Order insert(Order order) throws Exception {
        this.validator.validate(order); // Validare comandă

        if (this.orderDAO.findById(order.getId()) != null)
            throw new Exception("Există deja o comandă cu acest Id!");

        if (this.clientDAO.findById(order.getIdClient()) == null)
            throw new Exception("Nu există acest client!");

        if (this.productDAO.findById(order.getIdProdus()) == null)
            throw new Exception("Nu există acest produs!");

        Product product = this.productDAO.findById(order.getIdProdus());

        if (order.getCantitate() > product.getCantitate())
            throw new Exception("Nu există suficiente produse pe stoc pentru a se realiza  comanda!");

        product.setCantitate(product.getCantitate() - order.getCantitate());
        productDAO.update(product);

        return this.orderDAO.insert(order);
    }

     /**
     * Metoda in care se sterge o comanda in tabela Order
     *
     * @param order Comanda care trebuie să fie stearsa în tabela Order.
     * @return Comanda care a fost stearsa.
     * @throws Exception Daca nu exista comanda respectiva.
     */
    public Order delete(Order order) throws Exception {

        Order rezultat = this.orderDAO.delete(order);

        if (rezultat == null)
            throw new Exception("Nu exista aceasta comanda!");

        return rezultat;
    }

    /**
     * Metoda in care se modifica o comanda in tabela Order
     *
     * @param order Comanda care trebuie sa fie modificata in tabela Order.
     * @return Comanda care a fost modificata.
     * @throws Exception Daca acea comanda nu poate fi modificata.
     */
    public Order update(Order order) throws Exception {

        Order rezultat = this.findById(order.getId());

        if (rezultat == null)
            throw new Exception("Nu se poate face update la acest client!");

        if (this.clientDAO.findById(order.getIdClient()) == null)
            throw new Exception("Nu exista acest client!");

        if (this.productDAO.findById(order.getIdProdus()) == null)
            throw new Exception("Nu exista acest produs!");

        return this.orderDAO.update(order);
    }
}
