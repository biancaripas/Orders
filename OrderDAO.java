package dao;

import connection.ConnectionFactory;
import models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru extragerea datelor din tabela Order
 */
public class OrderDAO extends AbstractDAO<Order> {

    /**
     * Metoda care returneaza toate comenzile din tabela Order
     *
     * @return lista de comenzi din tabela Order
     */
    public List<Order> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `order`";
        List<Order> listaComenzi = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idClient = resultSet.getInt(2);
                int idProdus = resultSet.getInt(3);
                int client = resultSet.getInt(4);
                listaComenzi.add(new Order(id, idClient, idProdus, client));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return listaComenzi;
    }

    /**
     * Metoda care returneaza comanda cu ID-ul corespunzator
     *
     * @param id ID-ul dupa care se cauta comanda in tabela Order
     * @return comanda cu ID-ul cautat
     */
    public Order findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `order` WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idClient = resultSet.getInt(2);
                int idProdus = resultSet.getInt(3);
                int client = resultSet.getInt(4);
                return new Order(id, idClient, idProdus, client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
