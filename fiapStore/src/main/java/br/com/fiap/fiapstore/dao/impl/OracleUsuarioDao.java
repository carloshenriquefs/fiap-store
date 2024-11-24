package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.UsuarioDao;
import br.com.fiap.fiapstore.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static br.com.fiap.fiapstore.dao.ConnectionManager.getInstance;

public class OracleUsuarioDao implements UsuarioDao {

    private Connection connection;

    @Override
    public boolean validar(Usuario usuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getInstance().getConnection();

            String sql = "SELECT * FROM TB_USUARIO " +
                    "WHERE DS_EMAAIL = ? AND DS_SENHA = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
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
        return false;
    }
}
