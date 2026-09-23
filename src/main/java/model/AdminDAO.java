/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rafael
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public boolean autenticar(String usuario, String senha) throws SQLException {
        String sql = "SELECT 1 FROM administradores WHERE usuario = ? AND senha = ?";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, usuario);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}