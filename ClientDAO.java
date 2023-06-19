package dao;

import connection.ConnectionFactory;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru extragerea datelor din tabele Client
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Metoda care returneaza toti clientii din tabela Client
     *
     * @return lista de clienti din tabela Client
     */
    public List<Client> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM client";
        List<Client> listaClienti = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                listaClienti.add(new Client(id, nume, prenume));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return listaClienti;
    }

    /**
     *
     * Metoda care returneaza clientul cu ID-ul corespunzator
     *
     * @param id ID-ul dupa care se cauta clientul in tabela Client
     * @return clientul cu ID-ul cautat
     */
    public Client findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM client WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                return new Client(id, nume, prenume);
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