package dao;

import connection.ConnectionFactory;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru extragerea datelor din tabele Product
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Metoda care returneaza toti clientii din tabela Product
     *
     * @return lista de produse din tabela Product
     */
    public List<Product> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM product";
        List<Product> listaProduse = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                int pret = resultSet.getInt(3);
                int cantitate = resultSet.getInt(4);
                listaProduse.add(new Product(id, nume, pret, cantitate));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return listaProduse; // lista de produse
    }

    /**
     * Metoda care returneaza produsul cu ID-ul corespunzator
     *
     * @param id ID-ul dupa care se cauta clientul in tabela Product
     * @return produsul cu ID-ul cautat
     */
    public Product findById(int id) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM product WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nume = resultSet.getString(2);
                int pret = resultSet.getInt(3);
                int cantitate = resultSet.getInt(4);
                return new Product(id, nume, pret, cantitate);
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


