package start;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import bll.validators.ClientValidator;
import bll.validators.OrderValidator;
import bll.validators.ProductValidator;
import bll.validators.Validator;
import controllers.ClientController;
import controllers.OrderController;
import controllers.ProductController;
import dao.AbstractDAO;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import models.Client;
import models.Order;
import models.Product;
import views.ClientView;
import views.OrderView;
import views.ProductView;

/**
 * Clasa in care se va executa programul.
 */
public class Main {

    /**
     * Metoda in care se va executa programul.
     * @param args Argumentele din linia de comanda.
     */
    public static void main(String[] args) {

        Validator<Client> clientValidator = new ClientValidator();
        ClientDAO clientDAO = new ClientDAO();
        ClientBLL clientBLL = new ClientBLL(clientValidator, clientDAO);
        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(clientView, clientBLL);

        Validator<Product> productValidator = new ProductValidator();
        ProductDAO productDAO = new ProductDAO();
        ProductBLL productBLL = new ProductBLL(productValidator, productDAO);
        ProductView productView = new ProductView();
        ProductController productController = new ProductController(productView, productBLL);

        Validator<Order> orderValidator = new OrderValidator();
        OrderDAO orderDAO = new OrderDAO();
        OrderBLL orderBLL = new OrderBLL(orderValidator, clientDAO, productDAO, orderDAO);
        OrderView orderView = new OrderView();
        OrderController orderController = new OrderController(orderView, clientBLL, productBLL, orderBLL);

    }
}