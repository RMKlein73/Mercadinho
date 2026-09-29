/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rafael
 */
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Produto> produtos = new ArrayList<>();

    private static final String ADMIN_USUARIO = "admin";
    private static final String ADMIN_SENHA = "admin123";

    static {
        clientes.add(new Cliente(
                "cliente",
                "1234",
                "000.000.000-00",
                "(51) 99999-9999",
                "cliente@email.com"
        ));

        produtos.add(new Produto("Arroz 5kg", "Alimentos", 20, 29.90));
        produtos.add(new Produto("Feijão 1kg", "Alimentos", 30, 8.99));
        produtos.add(new Produto("Leite 1L", "Bebidas", 25, 5.49));
    }

    public static boolean cadastrarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }

        if (cliente.getUsuario() == null
                || cliente.getUsuario().isBlank()
                || cliente.getSenha() == null
                || cliente.getSenha().isBlank()) {
            return false;
        }

        if (buscarCliente(cliente.getUsuario()) != null
                || isAdmin(cliente.getUsuario())) {
            return false;
        }

        clientes.add(cliente);
        return true;
    }

    public static Cliente autenticarCliente(String usuario, String senha) {
        for (Cliente c : clientes) {
            if (c.getUsuario().equals(usuario)
                    && c.getSenha().equals(senha)) {
                return c;
            }
        }

        return null;
    }

    public static boolean autenticarAdmin(String usuario, String senha) {
        return ADMIN_USUARIO.equals(usuario)
                && ADMIN_SENHA.equals(senha);
    }

    public static Cliente buscarCliente(String usuario) {
        if (usuario == null) {
            return null;
        }

        for (Cliente c : clientes) {
            if (c.getUsuario().equalsIgnoreCase(usuario)) {
                return c;
            }
        }

        return null;
    }

    public static boolean isAdmin(String usuario) {
        return ADMIN_USUARIO.equalsIgnoreCase(usuario);
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public static void atualizarProduto(int indice, Produto produto) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.set(indice, produto);
        }
    }

    public static void excluirProduto(int indice) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.remove(indice);
        }
    }

    public static boolean comprarProduto(int indice, int quantidade) {
        if (indice < 0
                || indice >= produtos.size()
                || quantidade <= 0) {
            return false;
        }

        Produto produto = produtos.get(indice);

        if (produto.getQuantidade() < quantidade) {
            return false;
        }

        produto.setQuantidade(
                produto.getQuantidade() - quantidade
        );

        return true;
    }
}