package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class BancoDados {

    private BancoDados() {
    }

    public static void inicializar() throws SQLException {

        try (
            Connection c = Conexao.conectar();
            Statement st = c.createStatement()
        ) {

            st.executeUpdate("PRAGMA foreign_keys = ON");

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS clientes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario TEXT NOT NULL UNIQUE,
                    senha TEXT NOT NULL,
                    cpf TEXT NOT NULL UNIQUE,
                    telefone TEXT NOT NULL,
                    email TEXT NOT NULL
                )
            """);

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS administradores (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario TEXT NOT NULL UNIQUE,
                    senha TEXT NOT NULL
                )
            """);

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS produtos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL UNIQUE,
                    categoria TEXT NOT NULL,
                    quantidade INTEGER NOT NULL CHECK(quantidade >= 0),
                    valor REAL NOT NULL CHECK(valor >= 0)
                )
            """);

            if (!existeAdmin(c, "admin")) {

                try (
                    PreparedStatement ps = c.prepareStatement(
                        "INSERT INTO administradores(usuario, senha) VALUES(?, ?)"
                    )
                ) {

                    ps.setString(1, "admin");
                    ps.setString(2, "admin123");

                    ps.executeUpdate();

                    System.out.println("Administrador criado.");
                }
            }

            criarProdutosIniciais(c);
        }
    }

    private static boolean existeAdmin(
            Connection c,
            String usuario
    ) throws SQLException {

        try (
            PreparedStatement ps = c.prepareStatement(
                "SELECT 1 FROM administradores WHERE usuario = ?"
            )
        ) {

            ps.setString(1, usuario);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static void criarProdutosIniciais(
            Connection c
    ) throws SQLException {

        String sqlVerificar = "SELECT 1 FROM produtos LIMIT 1";

        try (
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sqlVerificar)
        ) {

            // Se já existir pelo menos um produto,
            // simplesmente não faz nada.
            if (rs.next()) {
                return;
            }
        }

        String sql = """
            INSERT INTO produtos
            (nome, categoria, quantidade, valor)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            adicionarProduto(
                ps,
                "Arroz 5kg",
                "Alimentos",
                30,
                25.90
            );

            adicionarProduto(
                ps,
                "Feijão 1kg",
                "Alimentos",
                40,
                8.50
            );

            adicionarProduto(
                ps,
                "Leite 1L",
                "Laticínios",
                50,
                5.49
            );

            adicionarProduto(
                ps,
                "Coca-Cola 2L",
                "Bebidas",
                25,
                10.99
            );

            adicionarProduto(
                ps,
                "Biscoito",
                "Alimentos",
                35,
                4.99
            );

            adicionarProduto(
                ps,
                "Açúcar 1kg",
                "Alimentos",
                30,
                5.99
            );

            adicionarProduto(
                ps,
                "Café 500g",
                "Alimentos",
                20,
                14.90
            );

            adicionarProduto(
                ps,
                "Água 1,5L",
                "Bebidas",
                50,
                3.49
            );

            adicionarProduto(
                ps,
                "Sabão em pó",
                "Limpeza",
                15,
                12.50
            );

            adicionarProduto(
                ps,
                "Papel higiênico",
                "Higiene",
                25,
                18.90
            );
        }
    }

    private static void adicionarProduto(
            PreparedStatement ps,
            String nome,
            String categoria,
            int quantidade,
            double valor
    ) throws SQLException {

        ps.setString(1, nome);
        ps.setString(2, categoria);
        ps.setInt(3, quantidade);
        ps.setDouble(4, valor);

        ps.executeUpdate();
    }
}