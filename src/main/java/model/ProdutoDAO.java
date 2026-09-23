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
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean cadastrar(Produto p) throws SQLException {
        String sql = "INSERT INTO produtos(nome, categoria, quantidade, valor) "
                + "VALUES (?, ?, ?, ?)";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCategoria());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getValor());

            return ps.executeUpdate() == 1;
        }
    }

    public boolean atualizar(Produto p) throws SQLException {
        String sql = "UPDATE produtos SET nome=?, categoria=?, quantidade=?, valor=? "
                + "WHERE id=?";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCategoria());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getValor());
            ps.setInt(5, p.getId());

            return ps.executeUpdate() == 1;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id=?";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT id, nome, categoria, quantidade, valor "
                + "FROM produtos ORDER BY nome";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {

                Produto p = new Produto(
                    rs.getString("nome"),
                    rs.getString("categoria"),
                    rs.getInt("quantidade"),
                    rs.getDouble("valor")
                );

                p.setId(rs.getInt("id"));
                lista.add(p);
            }
        }

        return lista;
    }

    public boolean comprar(String nome, int quantidade) throws SQLException {

        if (quantidade <= 0) {
            return false;
        }

        String sql = "UPDATE produtos SET quantidade = quantidade - ? "
                + "WHERE nome = ? AND quantidade >= ?";

        try (
            Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, quantidade);
            ps.setString(2, nome);
            ps.setInt(3, quantidade);

            return ps.executeUpdate() == 1;
        }
    }
}