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

public class ClienteDAO {

    public boolean cadastrar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes(usuario, senha, cpf, telefone, email) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, cliente.getUsuario());
            ps.setString(2, cliente.getSenha());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getEmail());

            return ps.executeUpdate() == 1;
        }
    }

    public boolean autenticar(String usuario, String senha) throws SQLException {
        String sql = "SELECT 1 FROM clientes WHERE usuario = ? AND senha = ?";

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