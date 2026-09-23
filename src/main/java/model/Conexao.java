/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rafael
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

    private static final String NOME_BANCO = "MercadinhoBanco.db";
    private static final String URL = "jdbc:sqlite:" + NOME_BANCO;

    private Conexao() {
    }

    public static Connection conectar() throws SQLException {
        try {
            Connection conexao = DriverManager.getConnection(URL);
            System.out.println("Banco de dados conectado com sucesso!");
            return conexao;

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            System.out.println("Detalhes: " + e.getMessage());
            throw e;
        }
    }

    public static String caminhoBanco() {
        return new File(NOME_BANCO).getAbsolutePath();
    }
}