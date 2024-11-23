package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fiapstore.dao.ConnectionManager.getInstance;

public class OracleCategoriaDao implements CategoriaDao {

    private Connection connection;

    @Override
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getInstance().getConnection();
            stmt = connection.prepareStatement("SELECT * FROM TB_CATEGORIA");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("COD_CATEGORIA");
                String nome = rs.getString("NOME_CATEGORIA");
                Categoria categoria = new Categoria(codigo, nome);
                lista.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
