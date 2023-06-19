package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;

/**
 * Clasa pentru extragerea datelor din tabele
 *
 * @param <T> de tipul corespunzator tabelelor respective
 */
public class AbstractDAO<T> {
    private final Class<T> type;

    /**
     * Constructorul clasei AbstractDAO este acesta :
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda care creeaza o lista de obiecte de tipul corespunzator tabelelor respective este aceasta :
     *
     * @param resultSet contine field-urile fiecarei tabele
     * @return o lista de obiecte de tipul corespunzator tabelei corespunzatoare
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] c = type.getDeclaredConstructors();
        Constructor ct = null;
        for (int i = 0; i < c.length; i++) {
            ct = c[i];
            if (ct.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ct.setAccessible(true);
                T instance = (T) ct.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object obj = resultSet.getObject(fieldName);
                    PropertyDescriptor pD = new PropertyDescriptor(fieldName, type);
                    Method method = pD.getWriteMethod();
                    method.invoke(instance, obj);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda care construieste query-ul pentru SELECT
     *
     * @param field contine un field
     * @return query-ul obtinut
     */
    private String createSelectQuery(String field) {
        String query = "SELECT * FROM " + type.getSimpleName() + " WHERE " + field + " =?";
        return query;
    }

    /**
     * Metoda care construieste query-ul pentru INSERT
     *
     * @return query-ul obtinut
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(type.getSimpleName()).append(" VALUES (");
        int nrAtribute = type.getDeclaredFields().length;
        for (int i = 0; i <= nrAtribute - 2; i++)
            sb.append("?, ");
        sb.append("?)");
        return sb.toString();
    }

    /**
     * Metoda care construieste query-ul pentru UPDATE
     *
     * @return query-ul obtinut
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        Field[] fields = type.getDeclaredFields();
        int nrAtribute = fields.length;
        for (int i = 1; i < nrAtribute - 1; i++) {
            sb.append(fields[i].getName()).append(" = ?, ");
        }
        sb.append(fields[nrAtribute - 1].getName()).append(" = ? WHERE id = ?");
        return sb.toString();
    }
    /**
     * Metoda care construieste query-ul pentru DELETE
     *
     * @return query-ul obtinut
     */

    private String createDeleteQuery() {
        String str = "DELETE  FROM " + type.getSimpleName() + " WHERE id = ?";
        return str;
    }

    /**
     * Metoda care afiseaza toate datele dintr-o tabela
     *
     * @return o lista de obiecte de tipul corespunzator tabelei respective
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care afiseaza toate datele dintr-o tabela dupa parametrul dat
     *
     * @param id ID-ul dupa care se cauta datele
     * @return rezultatul corespunzator
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care insereaza date intr-o tabela
     *
     * @param t obiectul care trebuie inserat
     * @return obiectul inserat
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(this.createInsertQuery());
            int index = 1;
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(t) instanceof Integer) {
                    statement.setInt(index, (Integer) f.get(t));
                } else if (f.get(t) instanceof String) {
                    statement.setString(index, (String) f.get(t));
                }
                index++;
            }
            statement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care modifica date dintr-o tabela
     *
     * @param t obiectul care trebuie modificat
     * @return obiectul modificat
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(this.createUpdateQuery());
            int index = 1;
            Field[] fields = type.getDeclaredFields();
            for (int i = 1; i <= fields.length - 1; i++) {
                fields[i].setAccessible(true);
                if (fields[i].get(t) instanceof Integer) {
                    statement.setInt(index, (Integer) fields[i].get(t));
                } else if (fields[i].get(t) instanceof String) {
                    statement.setString(index, (String) fields[i].get(t));
                }
                index++;
            }
            fields[0].setAccessible(true);
            statement.setInt(index, (Integer) fields[0].get(t));
            statement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care sterge date dintr-o tabela
     *
     * @param t obiectul care trebuie sters
     * @return obiectul sters
     */
    public T delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(this.createDeleteQuery());
            Field id = type.getDeclaredFields()[0];
            id.setAccessible(true);
            statement.setInt(1, (Integer) id.get(t));
            statement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
