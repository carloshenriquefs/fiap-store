package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.fiapstore.dao.ConnectionManager.getInstance;
import static java.sql.Date.valueOf;

public class OracleProdutoDao implements ProdutoDao {

    private Connection connection;

    @Override
    public void cadastrar(Produto produto) throws DBException {

        PreparedStatement stmt = null;

        connection = getInstance().getConnection();

        String sql = "INSERT INTO TB_PRODUTO " +
                "(COD_PRODUTO, NOME_PRODUTO, QTDE_PRODUTO, " +
                "VALOR_PRODUTO, DATA_FABRICACAO) " +
                "VALUES (SQ_TB_PRODUTO.NEXTVAL, ?, ?, ?, ?)";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValor());
            stmt.setDate(4, valueOf(produto.getDataFabricacao()));

            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar produto.");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Produto produto) throws DBException {
        PreparedStatement stmt = null;

        try {
            connection = getInstance().getConnection();

            String sql = "UPDATE TB_PRODUTO SET" +
                    "NOME_PRODUTO = ?, " +
                    "QTDE_PRODUTO = ?, " +
                    "VALOR_PRODUTO = ?," +
                    "DATA_FABRICACAO = ? " +
                    "WHERE COD_PRODUTO = ? ";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValor());
            stmt.setDate(4, valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getCodigo());

            System.out.println("Produto atualizado com sucesso!");

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar produto.");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(int codigo) throws DBException {

        PreparedStatement stmt = null;

        try {
            connection = getInstance().getConnection();
            String sql = "DELETE FROM TB_PRODUTO WHERE COD_PRODUTO = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            System.out.println("Produto removido.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover produto.");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Produto buscar(int codigo) {
        Produto produto = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getInstance().getConnection();
            String sql = "SELECT * FROM TB_PRODUTO WHERE COD_PRODUTO = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int codigoRetornado = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                int qtd = rs.getInt("QTDE_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                LocalDate data = rs.getDate("DATA_FABRICACAO").toLocalDate();

                produto = new Produto(codigo, nome, valor, qtd, data);
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

        return produto;
    }

    @Override
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getInstance().getConnection();
            String sql = "SELECT * FROM TB_PRODUTO";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                int qtd = rs.getInt("QTDE_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                LocalDate data = rs.getDate("DATA_FABRICACAO").toLocalDate();

                Produto produto = new Produto(codigo, nome, valor, qtd, data);

                lista.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
