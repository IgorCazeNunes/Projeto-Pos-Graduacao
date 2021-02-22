package com.ecommerce.pos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.postgresql.util.PSQLException;

import com.ecommerce.pos.model.Product;

public class ProductDAO {

    public ObservableList<Product> listAll() throws PSQLException {

        String sql = "SELECT * FROM produto";

        ObservableList<Product> produtosList = FXCollections.observableArrayList();

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.createConnectionToPostgresql();

            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product produto = new Product(
                        resultSet.getString("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("categoria"),
                        resultSet.getString("descricao")
                );

                produtosList.add(produto);
            }
        } catch (PSQLException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return produtosList;
        
    }

    public void save(Product produto) {
        String sql = "INSERT INTO produto(id, nome, valor, categoria, descricao)"
                + " VALUES(?::uuid, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.createConnectionToPostgresql();

            statement = connection.prepareStatement(sql);

            statement.setString(1, produto.getId());
            statement.setString(2, produto.getName());
            statement.setDouble(3, produto.getValue());
            statement.setString(4, produto.getCategory());
            statement.setString(5, produto.getDescription());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Product produto) {
        String sql = "UPDATE produto SET nome = ?, valor = ?, categoria = ?, descricao = ? WHERE id = ?::uuid";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.createConnectionToPostgresql();

            statement = connection.prepareStatement(sql);

            statement.setString(1, produto.getName());
            statement.setDouble(2, produto.getValue());
            statement.setString(3, produto.getCategory());
            statement.setString(4, produto.getDescription());

            statement.setString(5, produto.getId());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {

                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeById(String id) {
        String sql = "DELETE FROM produto WHERE id = ?::uuid";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.createConnectionToPostgresql();

            statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {

                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
